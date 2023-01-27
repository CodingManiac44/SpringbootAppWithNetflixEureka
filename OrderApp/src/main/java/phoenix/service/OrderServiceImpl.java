package phoenix.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import phoenix.config.TransactionConfiguration;
import phoenix.domain.Order;
import phoenix.dto.OrderRequestDto;
import phoenix.dto.OrderResponseDto;
import phoenix.dto.ProductInfo;
import phoenix.dto.ProductOrderRequestDto;
import phoenix.dto.ProductRequestDto;
import phoenix.dto.ProductResponseDto;
import phoenix.networkmanager.CallProductService;
import phoenix.networkmanager.ProductFeignClient;
import phoenix.repository.OrderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final CallProductService callProductService;
	private final ProductFeignClient productFeignClient;
	private final TransactionConfiguration transactionConfiguration;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, CallProductService callProductService,
			ProductFeignClient productFeignClient,
			TransactionConfiguration transactionConfiguration) {
		this.orderRepository = orderRepository;
		this.callProductService = callProductService;
		this.productFeignClient = productFeignClient;
		this.transactionConfiguration = transactionConfiguration;
	}

	@Override
	public OrderResponseDto save(String userEmail, OrderRequestDto orderRequestDto) {

		List<ProductRequestDto> productRequestDtos = orderRequestDto.getProductList();

		Map<Long, Integer> productInfo = productRequestDtos.stream()
				.collect(Collectors.toMap(ProductRequestDto::getProductId, ProductRequestDto::getQty));
		List<ProductInfo> productRequests = productRequestDtos.stream()
				.map(n -> ProductInfo.builder().productId(n.getProductId()).qty(n.getQty()).build())
				.collect(Collectors.toList());

		ProductOrderRequestDto productOrderRequestDto = new ProductOrderRequestDto();
		productOrderRequestDto.setProductRequests(productRequests);

		//call product service
		ResponseEntity<List<ProductResponseDto>> apiResponse =
				productFeignClient.updateProductsQuantity(productOrderRequestDto);
		List<ProductResponseDto> requestedProductList =
				apiResponse.getBody() != null ? apiResponse.getBody() : Collections.emptyList();
//				callProductService.callProductService(productOrderRequestDto,
//						"http://localhost:8082/api/product/updateQuantity");


		// call payment app
		// if success then return
		//else revert as failure
		if (transaction()) {
			//transaction success

			String productPriceWithQuantityAndPrice =
					requestedProductList.stream().map(n -> n.getProductId().toString())
							.collect(Collectors.joining(",")) + "|" + requestedProductList.stream()
							.map(n -> String.valueOf(productInfo.get(n.getProductId())))
							.collect(Collectors.joining(",")) + "|" + requestedProductList.stream()
							.map(n -> String.valueOf(n.getProductPrice())).collect(Collectors.joining(","));

			double totalPrice = requestedProductList.stream().mapToDouble(ProductResponseDto::getProductPrice).sum();
			Order newOrder = Order.builder().totalPrice(totalPrice).shippingMethod(orderRequestDto.getShippingMethod())
					.totalPrice(totalPrice).userEmail(userEmail)
					.productPriceWithQuantityAndPrice(productPriceWithQuantityAndPrice).orderStatus("success").build();
			return orderToOrderResponseDto(orderRepository.save(newOrder));
		} else {
			//transaction failure
			ResponseEntity<List<ProductResponseDto>> apiRevertResponse =
					productFeignClient.revertProductsQuantity(productOrderRequestDto);
			List<ProductResponseDto> revokedProductList =
					apiResponse.getBody() != null ? apiResponse.getBody() : Collections.emptyList();
//					callProductService.callProductService(productOrderRequestDto,
//							"http://localhost:8082/api/product/revokeQuantity");
			throw new RuntimeException("order fauiled");
		}
	}

	@Override
	public List<OrderResponseDto> getOrderList() {
		List<Order> orderList = orderRepository.findAll();
		return orderList.stream().filter(Objects::nonNull).map(this::orderToOrderResponseDto)
				.collect(Collectors.toList());
	}


	private boolean transaction() {
		return transactionConfiguration.getApplytransaction();
	}

	private OrderResponseDto orderToOrderResponseDto(Order order) {
		return OrderResponseDto.builder().orderId(order.getId()).orderStatus(order.getOrderStatus())
				.shippingMethod(order.getShippingMethod())
				.productPriceWithQuantityAndPrice(order.getProductPriceWithQuantityAndPrice())
				.totalPrice(order.getTotalPrice()).build();
	}

}
