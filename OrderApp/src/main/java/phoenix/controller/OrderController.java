package phoenix.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phoenix.dto.OrderRequestDto;
import phoenix.dto.OrderResponseDto;
import phoenix.service.OrderService;

import javax.validation.Valid;
import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@RestController
@Slf4j
@RequestMapping("/api/")
public class OrderController {

	private final OrderService orderService;


	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/order/submit")
	public OrderResponseDto addProducts(@Valid @RequestBody OrderRequestDto orderRequestDto) {
		String userEmail = "abdullah21@gmail.com";
		log.info(orderRequestDto.toString());
		return orderService.save(userEmail, orderRequestDto);
	}

	@GetMapping("/order/list")
	public List<OrderResponseDto> getAllOrder() {
		return orderService.getOrderList();
	}
}
