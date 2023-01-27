package phoenix.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
@Data
@Builder
public class ProductInfo {

	@Min(value = 1, message = "provide valid value")
	private Long productId;
	@Min(value = 1, message = "provide valid value")
	private Integer qty;
}
