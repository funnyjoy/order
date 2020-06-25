package com.jpetstore.order.app.order;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpetstore.order.domain.model.Order;
import com.jpetstore.order.domain.service.order.OrderService;

@RestController
public class OrderController {

	@Inject
	protected OrderService orderService;

	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable int orderId) {

		return orderService.getOrder(orderId);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<Order> getOrdersByUsername(@RequestParam String username) {

		return orderService.getOrdersByUsername(username);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.PUT)
	public void insertOrder(@RequestBody Order order) {

		orderService.insertOrder(order);
	}
}
