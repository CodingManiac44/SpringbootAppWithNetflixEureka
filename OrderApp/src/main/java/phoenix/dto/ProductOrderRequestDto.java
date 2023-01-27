package phoenix.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Data
public class ProductOrderRequestDto {
	@NotNull(message = "product list cannot be null")
	@Valid
	protected List<ProductInfo> productRequests;
}
