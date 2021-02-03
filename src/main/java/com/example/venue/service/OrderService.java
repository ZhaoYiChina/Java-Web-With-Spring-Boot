package com.example.venue.service;

import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface OrderService {

    public List<Order> getUserOrdersUnChecked(String phone);

    public Integer getOrderNum(HashMap<String, Object> map);

    public void insertOrder(Order order);

    public void updateOrder(Order order);

    public JSONObject submitOrder (HttpServletRequest request);

    public Order getOrderByUUID(String uuid);

    public List<Order> getAllOrders();

    public List<Order> getOrdersByDate(String uDate);

//    public List<Order> getOrdersByDateAndPeriod(Map<String, Object> map);

}
