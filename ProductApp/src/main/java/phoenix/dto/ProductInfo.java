package phoenix.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
@Data
public class ProductInfo {

	@Min(value = 1, message = "provide valid value")
	private Long productId;
	@Min(value = 1, message = "provide valid value")
	private Integer qty;
}
