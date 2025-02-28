package com.compass_ordering_food_java.services;

import com.compass_ordering_food_java.dto.OrderDto;
import com.compass_ordering_food_java.dto.OrderResponse;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(int orderId);
    OrderResponse getAllOrders(int pageNo, int pageSize);
    OrderDto updateOrder(int orderId, OrderDto orderDto);
    void deleteOrderById(int orderId);
}
