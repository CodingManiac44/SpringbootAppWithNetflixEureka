package phoenix.dto;

import lombok.Builder;
import lombok.Data;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Builder
@Data
public class ProductResponseDto {
	private Long productId;
	private String productName;
	private String productSKU;
	private String productDescription;
	
	private Double productPrice;
	private Double productWeight;
	private String productCategory;
	private int qty;
}
