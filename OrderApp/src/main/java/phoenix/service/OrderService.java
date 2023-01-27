package phoenix.service;

import phoenix.dto.OrderRequestDto;
import phoenix.dto.OrderResponseDto;
import java.util.List;


/**
 * User Khan, C M Abdullah
 * Ref:
 */
public interface OrderService {
	//@Transactional
	OrderResponseDto save(String userEmail, OrderRequestDto orderRequestDto);

	List<OrderResponseDto> getOrderList();
}
