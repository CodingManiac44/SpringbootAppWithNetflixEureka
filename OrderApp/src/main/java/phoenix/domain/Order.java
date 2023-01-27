package phoenix.domain;

import lombok.*;

import javax.persistence.*;

/**
 * BookWormV2
 * Created on 21/8/22 - Sunday
 * User Khan, C M Abdullah
 * Ref:
 */
@Data
@Entity
@Table(name = "product_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {
	
	private String shippingMethod;
	private String orderStatus;
	private String userEmail;
	private Double totalPrice;
	private String productPriceWithQuantityAndPrice;
}
