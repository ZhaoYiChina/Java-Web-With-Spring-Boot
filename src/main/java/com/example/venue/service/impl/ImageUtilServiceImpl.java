package com.example.venue.service.impl;

import com.example.venue.service.ImageUtilService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Service("ImageUtilService")
public class ImageUtilServiceImpl implements ImageUtilService {

    @Override
    public String generateOrderCode(String uuid) {

        String orderCode = null;
        int width = 600;
        int height = 600;
        String format = "png"; //图像类型
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //内容编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //指定纠错定级
        hints.put(EncodeHintType.MARGIN, 1);


        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(uuid, BarcodeFormat.QR_CODE, width,height);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
            Base64.Encoder encoder = Base64.getEncoder();
            orderCode = encoder.encodeToString(outputStream.toByteArray());
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderCode;
    }
}
