package com.compass_ordering_food_java.controllers;

import com.compass_ordering_food_java.dto.OrderDto;
import com.compass_ordering_food_java.dto.OrderResponse;
import com.compass_ordering_food_java.models.Order;
import com.compass_ordering_food_java.repository.OrderRepository;
import com.compass_ordering_food_java.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("orders")
    public ResponseEntity<OrderResponse> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(orderService.getAllOrders(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PutMapping("orders/{id}/update")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable(value = "id") int id, @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.updateOrder(id, orderDto), HttpStatus.OK);
    }

    @DeleteMapping("orders/{id}/delete")
    public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") int id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }
}
