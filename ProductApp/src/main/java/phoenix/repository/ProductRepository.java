package phoenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.domain.Product;

import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByIdIn( List<Long> ids);
}
