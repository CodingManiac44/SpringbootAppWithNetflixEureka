package phoenix.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
