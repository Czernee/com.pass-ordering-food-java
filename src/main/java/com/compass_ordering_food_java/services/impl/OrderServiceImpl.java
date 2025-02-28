package com.compass_ordering_food_java.services.impl;

import com.compass_ordering_food_java.dto.OrderDto;
import com.compass_ordering_food_java.dto.OrderResponse;
import com.compass_ordering_food_java.exceptions.DishNotFoundException;
import com.compass_ordering_food_java.exceptions.OrderNotFoundException;
import com.compass_ordering_food_java.models.Dish;
import com.compass_ordering_food_java.models.Order;
import com.compass_ordering_food_java.repository.OrderRepository;
import com.compass_ordering_food_java.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setClientId(orderDto.getClientId());
        order.setDishes(orderDto.getDishes());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setDateOrder(orderDto.getDateOrder());
        order.setDateDeliver(orderDto.getDateDeliver());
        order.setCompleted(order.isCompleted());

        Order newOrder = orderRepository.save(order);

        OrderDto orderResponse = new OrderDto();

        orderResponse.setId(newOrder.getId());
        orderResponse.setClientId(newOrder.getClientId());
        orderResponse.setDishes(newOrder.getDishes());
        orderResponse.setTotalPrice(newOrder.getTotalPrice());
        orderResponse.setDateOrder(newOrder.getDateOrder());
        orderResponse.setDateDeliver(newOrder.getDateDeliver());
        orderResponse.setCompleted(newOrder.isCompleted());

        return orderResponse;
    }

    @Override
    public OrderResponse getAllOrders(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Order> orders = orderRepository.findAll(pageable);
        List<Order> listOfOrders = orders.getContent();
        List<OrderDto> content = listOfOrders.stream().map(this::mapToDto).toList();

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setContent(content);
        orderResponse.setPageNo(orders.getNumber());
        orderResponse.setPageSize(orders.getSize());
        orderResponse.setTotalElements(orders.getTotalElements());
        orderResponse.setTotalPages(orderResponse.getTotalPages());
        orderResponse.setLast(orderResponse.isLast());

        return orderResponse;
    }

    @Override
    public OrderDto getOrderById(int orderId) {
        Order order = orderRepository.findById((long) orderId).orElseThrow(() -> new OrderNotFoundException("Order with this id can't be found"));
        return mapToDto(order);
    }

    @Override
    public OrderDto updateOrder(int orderId, OrderDto orderDto) {
        Order order = orderRepository.findById((long) orderId).orElseThrow(() -> new OrderNotFoundException("Order with this id can't be found"));

        order.setClientId(orderDto.getClientId());
        order.setDishes(orderDto.getDishes());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setDateOrder(orderDto.getDateOrder());
        order.setDateDeliver(orderDto.getDateDeliver());
        order.setCompleted(orderDto.isCompleted());

        Order updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    @Override
    public void deleteOrderById(int orderId) {
        Order order = orderRepository.findById((long) orderId).orElseThrow(() -> new OrderNotFoundException("Order with this id can't be found"));
        orderRepository.delete(order);
    }

    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setClientId(order.getClientId());
        orderDto.setDishes(order.getDishes());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDateOrder(order.getDateOrder());
        orderDto.setDateDeliver(order.getDateDeliver());
        orderDto.setCompleted(order.isCompleted());
        return orderDto;
    }
}
