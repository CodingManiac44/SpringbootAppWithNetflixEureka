package phoenix.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Data
public class ProductRequestDto {
	//@JsonProperty("product_id")
	private long productId;
	@NotBlank(message = "provide valid value")
	private String productName;
	@NotBlank(message = "provide valid value")
	private String productSKU;
	private String productDescription;
	@Min(value = 1, message = "provide valid value")
	private double productPrice;
	@Min(value = 1, message = "provide valid value")
	private int qty;
	private double productWeight;
	@NotBlank(message = "provide valid value")
	private String productCategory;
}
