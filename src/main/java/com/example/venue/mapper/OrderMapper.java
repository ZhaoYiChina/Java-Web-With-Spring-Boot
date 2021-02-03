package com.example.venue.mapper;

import com.example.venue.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OrderMapper {

    // 获取该手机号用户有效期内的未核销预约
    public List<Order> getUserOrdersUnChecked(String uPhone);

    // 获取指定日期、时间段的预约数量
    public Integer getOrderNum(HashMap<String, Object> map);

    public void insertOrder(Order order);

    public void updateOrder(Order order);

    public Order getOrderByUUID(String uuid);

    public List<Order> getAllOrders();

    public List<Order> getAllCheckedOrders();

    public List<Order> getOrdersByDate(String uDate);

    public List<Order> getCheckedOrdersByDate(String uDate);

    public List<Order> getOrdersByDateAndPeriod(Map<String, Object> map);

}
