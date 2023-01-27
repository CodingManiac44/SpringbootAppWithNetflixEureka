package phoenix.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
	@NotBlank(message = "shipping.method.error")
	private String shippingMethod;
	@Min(value = 0, message = "total.price.required.error")
	private double totalPrice;

	@Valid
	@NotNull(message = "product.list.error")
	private List<ProductRequestDto> productList;
}
