package phoenix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import phoenix.util.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
//@Builder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;
	
	@JsonIgnore
	@Column(name = "updated_at")
	private Instant updatedAt;
	
	@JsonIgnore
	@Column(name = "created_by")
	private String createdBy;
	
	@JsonIgnore
	@Column(name = "updated_by")
	private String updatedBy;
	
	@JsonIgnore
	@Column(name = "uuid_str", nullable = false, unique = true)
	private String uuid;
	
	@JsonIgnore
	@Builder.Default
	@Column(name = "is_active", columnDefinition = "boolean default true", nullable = false)
	private final boolean active = true;
	
	@PrePersist
	private void onBasePersist() {
		this.createdAt = Instant.now();
		this.updatedAt = createdAt;
		this.uuid = Utils.getUniqueUUID();
	}
	
	@PreUpdate
	private void onBaseUpdate() {
		this.updatedAt = Instant.now();
	}
	
	@JsonIgnore
	public boolean isNew() {
		return Objects.isNull(getId());
	}
	
	public BaseEntity() {
	}
	
	public BaseEntity(Long id, Instant createdAt, Instant updatedAt, String createdBy, String updatedBy, String uuid) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	
	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
