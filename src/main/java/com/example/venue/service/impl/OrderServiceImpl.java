package com.example.venue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.Order;
import com.example.venue.mapper.OrderMapper;
import com.example.venue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getUserOrdersUnChecked(String phone) {
        return orderMapper.getUserOrdersUnChecked(phone);
    }

    @Override
    public Integer getOrderNum(HashMap<String, Object> map){
        return orderMapper.getOrderNum(map);
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public JSONObject submitOrder(HttpServletRequest request) {

        JSONObject res = new JSONObject();
        String thisDateString = request.getParameter("uDate").toString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("uDate", thisDateString);
        map.put("uPeriod", Integer.valueOf(request.getParameter("uPeriod")));
        Integer thisOrderNum = getOrderNum(map);
        if(thisOrderNum > 500){
            res.put("code", 0);
            res.put("msg", "预约已满，请预约其他时间！");
            return  res;
        }

        Cookie[] cookies = request.getCookies();
        String uPhone = null;
        for(Cookie item : cookies){
            if("cookie_uPhone".equals(item.getName())){
                uPhone = item.getValue();
                break;
            }
        }

        String orderId = request.getParameter("orderId");
        if(null == orderId || orderId.equals("")){
            List<Order> orders = getUserOrdersUnChecked(uPhone);
            if(orders.size() == 0){
                Order order = new Order();
                order.setuDate(request.getParameter("uDate"));
                order.setuPeriod(Integer.valueOf(request.getParameter("uPeriod")));
                order.setuPhone(uPhone);
                order.setuName(request.getParameter("uName"));
                order.setuIdNumber(request.getParameter("uIdNumber"));
                order.setuChildNum(Integer.valueOf(request.getParameter("uChildNum")));
                order.setuCreateTime(new Date());
                order.setuUpdateTime(null);
                order.setChecked(0);
                UUID uuid = UUID.randomUUID();
                order.setUuid(uuid.toString());
                System.out.println(uPhone);
                System.out.println(request.getParameter("uName"));
                System.out.println(request.getParameter("uIdNumber"));
                insertOrder(order);
                res.put("code", 1);
                res.put("msg", "预约成功！");
                return  res;
            }else {
                res.put("code", 2);
                res.put("msg", "已有预约未核销，请至“我的预约”页面修改预约时间，或者核销后再预约！");
            }
        }else{
            Order order = new Order();
            order.setId(Integer.valueOf(orderId));
            order.setuDate(request.getParameter("uDate"));
            order.setuPeriod(Integer.valueOf(request.getParameter("uPeriod")));
            order.setuPhone(uPhone);
            order.setuName(request.getParameter("uName"));
            order.setuIdNumber(request.getParameter("uIdNumber"));
            order.setuChildNum(Integer.valueOf(request.getParameter("uChildNum")));
            order.setuCreateTime(new Date());
            order.setuUpdateTime(null);
            order.setChecked(0);
            UUID uuid = UUID.randomUUID();
            order.setUuid(uuid.toString());
            System.out.println(uPhone);
            System.out.println(request.getParameter("uName"));
            System.out.println(request.getParameter("uIdNumber"));
            updateOrder(order);
            res.put("code", 1);
            res.put("msg", "预约修改成功！");
            return  res;
        }

        return  res;

    }

    @Override
    public Order getOrderByUUID(String uuid) {
        return orderMapper.getOrderByUUID(uuid);
    }

    @Override
    public List<Order> getAllOrders(){

        List<Order> allOrders = orderMapper.getAllOrders();
        List<Order> checkedOrders = orderMapper.getAllCheckedOrders();
        if(null != allOrders && null != checkedOrders){
            for(Order Totalorder: allOrders){
                for(Order checkOrder: checkedOrders){
                    if(Totalorder.getuDate().equals(checkOrder.getuDate())){
                        Totalorder.setRemark(checkOrder.getuChildNum().toString());
                    }
                }
            }
            return allOrders;
        }else {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByDate(String uDate){
        List<Order> dayOrders = orderMapper.getOrdersByDate(uDate);
        List<Order> dayCheckedOrders = orderMapper.getCheckedOrdersByDate(uDate);
        if(null != dayOrders && null != dayCheckedOrders){
            for(Order Totalorder: dayOrders){
                for(Order checkOrder: dayCheckedOrders){
                    if(Totalorder.getuPeriod().equals(checkOrder.getuPeriod())){
                        Totalorder.setRemark(checkOrder.getuChildNum().toString());
                    }
                }
                switch (Totalorder.getuPeriod()){
                    case 1:
                        Totalorder.setuDate("10:00-12:00");
                        break;
                    case 2:
                        Totalorder.setuDate("12:00-14:00");
                        break;
                    case 3:
                        Totalorder.setuDate("14:00-16:00");
                        break;
                    case 4:
                        Totalorder.setuDate("16:00-18:00");
                        break;
                    case 5:
                        Totalorder.setuDate("18:00-20:30");
                        break;
                    }
            }
            return dayOrders;
        }else {
            return null;
        }
    }

//    @Override
//    public List<Order> getOrdersByDateAndPeriod(Map<String, Object> map){
//        List<Order> periodOrders = orderMapper.getOrdersByDateAndPeriod(map);
//        if(null != periodOrders){
//            return periodOrders;
//        }else {
//            return null;
//        }
//    }


}
