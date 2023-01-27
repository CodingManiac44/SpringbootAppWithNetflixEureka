package phoenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.domain.Categories;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
	Categories findByCategoryName(String productCategory);
}
