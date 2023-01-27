package phoenix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("product-app")
public class MigrationConfiguration {
	
	private Boolean migration;

	public Boolean getMigration() {
		return migration;
	}

	public void setMigration(Boolean migration) {
		this.migration = migration;
	}
}
