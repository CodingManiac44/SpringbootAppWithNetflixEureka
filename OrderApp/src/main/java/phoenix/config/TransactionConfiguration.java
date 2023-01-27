package phoenix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("order-app")
public class TransactionConfiguration {
	
	private Boolean applytransaction;

	public Boolean getApplytransaction() {
		return applytransaction;
	}

	public void setApplytransaction(Boolean applytransaction) {
		this.applytransaction = applytransaction;
	}
}
