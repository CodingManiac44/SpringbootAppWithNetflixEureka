package phoenix.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@ToString
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Categories extends BaseEntity {
	private String categoryName;
}
