package phoenix.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
	private String productName;
	private String productSKU;
	private String productDescription;
	private Double productPrice;
	private Double productWeight;
	private int productQuantity;

	@ToString.Exclude
	@OneToOne
	private Categories categories;
}
