package com.jpetstore.order.domain.service.order;

import java.util.List;

import com.jpetstore.order.domain.model.Order;

public interface OrderService {

    int insertOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

}
