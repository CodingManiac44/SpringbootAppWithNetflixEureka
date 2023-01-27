package phoenix.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import phoenix.domain.Categories;
import phoenix.domain.Product;
import phoenix.dto.ProductRequestDto;
import phoenix.dto.ProductResponseDto;
import phoenix.repository.CategoriesRepository;
import phoenix.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BookWormV2
 * Created on 22/8/22 - Monday
 * User Khan, C M Abdullah
 * Ref:
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoriesRepository categoriesRepository;

	@Autowired
	ProductServiceImpl(ProductRepository productRepository, CategoriesRepository categoriesRepository) {
		this.productRepository = productRepository;
		this.categoriesRepository = categoriesRepository;
	}

	@Override
	public ProductResponseDto save(ProductRequestDto productRequestDto) {

		Categories ctg = categoriesRepository.findByCategoryName(productRequestDto.getProductCategory());
		log.info("ctg" + ctg);
		Product product = productRequestDtoToProduct(productRequestDto);
		product.setCategories(ctg);

		Product savedProduct = productRepository.save(product);

		return getProductToProductResponseDto(savedProduct);
	}


	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().filter(Objects::nonNull).map(this::getProductToProductResponseDto)
				.collect(Collectors.toList());
	}

	private ProductResponseDto getProductToProductResponseDto(Product savedProduct) {
		return ProductResponseDto.builder().productId(savedProduct.getId()).productName(savedProduct.getProductName())
				.productDescription(savedProduct.getProductDescription()).productPrice(savedProduct.getProductPrice())
				.productSKU(savedProduct.getProductSKU()).productWeight(savedProduct.getProductWeight())
				.qty(savedProduct.getProductQuantity()).productCategory(savedProduct.getCategories().getCategoryName())
				.build();
	}

	private Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
		return Product.builder().productName(productRequestDto.getProductName())
				.productDescription(productRequestDto.getProductDescription())
				.productPrice(productRequestDto.getProductPrice()).productSKU(productRequestDto.getProductSKU())
				.productWeight(productRequestDto.getProductWeight()).productQuantity(productRequestDto.getQty())
//					   .categories(ctg)
				.build();
	}


	@Override
	public ProductResponseDto update(ProductRequestDto productRequestDto) {
		Product product = productRepository.findById(productRequestDto.getProductId())
				.orElseThrow(() -> new RuntimeException("product not found"));
		updateProduct(productRequestDto, product);
		return getProductToProductResponseDto(productRepository.save(product));
	}


	private ProductResponseDto productToProductResponseDto(Product product) {
		return ProductResponseDto.builder().productId(product.getId()).productName(product.getProductName())
				.productDescription(product.getProductDescription()).productPrice(product.getProductPrice())
				.productSKU(product.getProductSKU()).productWeight(product.getProductWeight())
				.productCategory(product.getCategories().getCategoryName()).build();
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductResponseDto> updateProductsQuantity(Map<Long, Integer> productRequests, String operation) {
		var productIdList = new ArrayList<>(productRequests.keySet());
		List<Product> products = productRepository.findByIdIn(productIdList);

		List<Product> updatedProductsList = products.stream().filter(Objects::nonNull).map(product -> {
			int quantity = productRequests.get(product.getId());
			if (quantity <= product.getProductQuantity() && operation.equals("update")) {
				product.setProductQuantity(product.getProductQuantity() - quantity);
			} else if (operation.equals("revoke")) {
				product.setProductQuantity(product.getProductQuantity() + quantity);
			} else {
				//exception should be thrown
			}
			return product;
		}).collect(Collectors.toList());

		List<Product> updatedList = productRepository.saveAll(updatedProductsList);

		return updatedList.stream().filter(Objects::nonNull).map(this::getProductToProductResponseDto)
				.collect(Collectors.toList());
	}

	private void updateProduct(ProductRequestDto productRequestDto, Product product) {
		if (!product.getProductName().equals(productRequestDto.getProductName())) {
			product.setProductName(productRequestDto.getProductName());
		}

		if (!product.getProductDescription().equals(productRequestDto.getProductDescription())) {
			product.setProductDescription(productRequestDto.getProductDescription());
		}

		if (!product.getProductPrice().equals(productRequestDto.getProductPrice())) {
			product.setProductPrice(productRequestDto.getProductPrice());
		}

		if (!product.getProductWeight().equals(productRequestDto.getProductWeight())) {
			product.setProductWeight(productRequestDto.getProductWeight());
		}

		if (!(product.getProductQuantity() == productRequestDto.getQty())) {
			product.setProductQuantity(productRequestDto.getQty());
		}
	}
}
