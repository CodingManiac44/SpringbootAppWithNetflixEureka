package phoenix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
	private long orderId;
	private String orderStatus;
	private String shippingMethod;
	private double totalPrice;
	private String productPriceWithQuantityAndPrice;
}
