package com.example.venue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.Order;
import com.example.venue.service.OrderService;
import com.example.venue.service.VerifiService;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("VerifiService")
public class VerifiServiceImpl implements VerifiService {

    final BASE64Decoder decoder = new BASE64Decoder();

    @Autowired
    private OrderService orderService;

    @Override
    public JSONObject getResultbyOCR(String requestdata) {

        JSONObject res = new JSONObject();
        String QRCode = null;

        BufferedImage image;
        String imgStr = requestdata.substring(requestdata.indexOf(",") + 1);
        BASE64Decoder decoder = new BASE64Decoder();
        //    Base64解码,对字节数组字符串进行Base64解码并生成图片
        byte[] b = new byte[0];
        try {
            b = decoder.decodeBuffer(imgStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        // 生成jpeg图片
        Result result = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\George\\Documents\\QRCodeDir\\QRCode.jpg"));
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
            image = ImageIO.read(new File("C:\\Users\\George\\Documents\\QRCodeDir\\QRCode.jpg"));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hintTypeMap = new HashMap();
            hintTypeMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            result = new MultiFormatReader().decode(binaryBitmap, hintTypeMap);
            QRCode = result.getText();
            System.out.println(result);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }

        if(null == result){
            res.put("code", 3);
            res.put("msg", "二维码识别失败，请重新扫描！");
            return res;
        }
        Order currentOrder = orderService.getOrderByUUID(QRCode);
        if(currentOrder == null){
            res.put("code", 0);
            res.put("msg", "核销失败，查询不到该预约信息！");
        }else {
            res.put("code", 1);
            res.put("msg", "核销成功！");
            currentOrder.setChecked(1);
            orderService.updateOrder(currentOrder);
            switch (currentOrder.getuPeriod()){
                case 1:
                    currentOrder.setRemark("10:00-12:00");
                    break;
                case 2:
                    currentOrder.setRemark("12:00-14:00");
                    break;
                case 3:
                    currentOrder.setRemark("14:00-16:00");
                    break;
                case 4:
                    currentOrder.setRemark("16:00-18:00");
                    break;
                case 5:
                    currentOrder.setRemark("18:00-20:30");
                    break;
            }
            String uName = currentOrder.getuName();
            String uPhone = currentOrder.getuPhone();
            currentOrder.setuName("*" + uName.substring(uName.length() -1));
            currentOrder.setuPhone("*** ****" + uPhone.substring(uPhone.length()-4));
            res.put("order", currentOrder);
        }
        return res;
    }
}
