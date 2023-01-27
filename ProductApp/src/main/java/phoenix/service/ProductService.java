package phoenix.service;

import phoenix.dto.ProductRequestDto;
import phoenix.dto.ProductResponseDto;

import java.util.List;
import java.util.Map;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
public interface ProductService {
	ProductResponseDto save(ProductRequestDto productRequestDto);
	
	List<ProductResponseDto> getAllProducts();
	ProductResponseDto update(ProductRequestDto productRequestDto);

	void delete(Long id);

	List<ProductResponseDto> updateProductsQuantity(Map<Long, Integer> productRequests, String operation);
}
