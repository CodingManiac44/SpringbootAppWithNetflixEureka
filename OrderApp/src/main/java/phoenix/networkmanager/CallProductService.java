package phoenix.networkmanager;

import phoenix.dto.ProductOrderRequestDto;
import phoenix.dto.ProductResponseDto;

import java.util.List;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
public interface CallProductService {
	List<ProductResponseDto> callProductService(ProductOrderRequestDto productOrderRequestDto, String url);
}
