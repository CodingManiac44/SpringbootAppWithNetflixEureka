package phoenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.domain.Order;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
