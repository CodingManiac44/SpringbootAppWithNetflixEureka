package phoenix.networkmanager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import phoenix.dto.ProductOrderRequestDto;
import phoenix.dto.ProductResponseDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
//@FeignClient(name="productApp", url="localhost:8082/")
@FeignClient(name="product-app")
public interface ProductFeignClient {
	@PostMapping("/api/product/updateQuantity")
	ResponseEntity<List<ProductResponseDto>> updateProductsQuantity(@Valid @RequestBody ProductOrderRequestDto productOrderRequestDto);
	@PostMapping("/api/product/revokeQuantity")
	ResponseEntity<List<ProductResponseDto>> revertProductsQuantity(@Valid @RequestBody ProductOrderRequestDto productOrderRequestDto);
}
