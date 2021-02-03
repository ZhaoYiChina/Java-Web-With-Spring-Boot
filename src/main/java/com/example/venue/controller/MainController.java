package com.example.venue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.Order;
import com.example.venue.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private DoLoginService doLoginService;

    @Autowired
    private ImageUtilService imageUtilService;

    @Autowired
    private VerifiService verifiService;

     //登录注册页
    @RequestMapping(value={"/login.html","/login"})
    public String login(Map<String,Object> map){
        return "login";
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public JSONObject getCode(String uPhone){
        return msgService.SendSms(uPhone);
    }

    // 登录点击事件
    @RequestMapping("/loginSubmit")
    @ResponseBody
    public JSONObject loginSubmit(String uPhone, String uMsg, HttpSession session, HttpServletRequest request,
                                  HttpServletResponse response){
        return doLoginService.Login(uPhone, uMsg, session, request, response);
}

    // 预约页面
    @RequestMapping(value={"/home.html","/home"})
    public String home(Map<String,Object> map){
        return "home";
    }

    @RequestMapping("/submitOrder")
    @ResponseBody
    public JSONObject submitOrder(HttpServletRequest request){
        return orderService.submitOrder(request);
    }

    @RequestMapping(value={"/order.html","/order"})
    public String order(HttpServletRequest request, Map<String,Object> map){

        Cookie[] cookies = request.getCookies();
        String uPhone = null;
        for(Cookie item : cookies){
            if("cookie_uPhone".equals(item.getName())){
                uPhone = item.getValue();
                break;
            }
        }
        List<Order> userOrders = orderService.getUserOrdersUnChecked(uPhone);
        if(userOrders.size() != 0){
            Order currentOrder = userOrders.get(0);
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
            String orderCode = imageUtilService.generateOrderCode(currentOrder.getUuid());
            if(null != orderCode){
                currentOrder.setUuid(orderCode);
            }
            map.put("userOrder",currentOrder);
            return "order";
        }else {
            return "nullOrder";
        }
    }

    // 管理员页面
    @RequestMapping(value={"/admin.html","/admin"})
    public String admin(Map<String,Object> map){
        return "admin";
    }


    // 预约列表
//    @RequestMapping(value={"/orderList.html","/orderList"})
//    public String orderList(Map<String,Object> map){
//        return "orderList";
//    }


    // 预约页面
    @RequestMapping(value={"/orderVerifi.html","/orderVerifi"})
    public String orderVerifi(Map<String,Object> map){
        return "orderVerification";
    }


    // 核销预约二维码
    @RequestMapping("/getResultbyOCR")
    @ResponseBody
    public JSONObject getResultbyOCR( @RequestBody String  requestData, HttpServletResponse response){
        return verifiService.getResultbyOCR(requestData);
    }

    // 超权页面
    @RequestMapping(value={"/limit.html","/limit"})
    public String limit(Map<String,Object> map){
        return "limit";
    }

    // 全部日期预约查询页面
    @RequestMapping(value={"/orderDateQuery.html","/orderDateQuery"})
    public String orderDateQuery(HttpServletRequest request, Map<String,Object> map){
        map.put("dataList", orderService.getAllOrders());
//        map.put("name","zhaoyi");
        return "orderDateQuery";
    }

    // 单日预约查询页面
    @RequestMapping(value={"/orderPeriodQuery.html","/orderPeriodQuery"})
    public String orderPeriodQuery(Map<String,Object> map, String uDate){
        map.put("uDate",uDate);
        map.put("dataList", orderService.getOrdersByDate(uDate));
        return "orderPeriodQuery";
    }

//    // 单日单阶段预约详情查询页面
//    @RequestMapping(value={"/orderMsgQuery.html","/orderMsgQuery"})
//    public String orderMsgQuery(Map<String,Object> map, String uDate, String uPeriod){
//        Map<String,Object> request = new HashMap<>();
//        request.put("uDate", uDate);
//        request.put("uPeriod", uPeriod);
//        map.put("dataList", orderService.getOrdersByDateAndPeriod(request));
//        return "orderMsgQuery";
//    }


    @RequestMapping(value={"/update.html","/update"})
    public String update(Map<String,Object> map, String orderId){
        map.put("orderId",orderId);
        return "update";
    }

}
