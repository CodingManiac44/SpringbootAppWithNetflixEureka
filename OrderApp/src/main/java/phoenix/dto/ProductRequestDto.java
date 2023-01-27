package phoenix.dto;

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
	@Min(value = 1, message = "product id must be provided")
	private Long productId;
	private String productName;
	private String productSKU;
	private String productDescription;
	private double productPrice;
	@Min(value = 1, message = "quantity must be provided")
	private int qty;
	private double productWeight;
	private String productCategory;
}
