//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import phoenix.config.MigrationConfiguration;
import phoenix.domain.Categories;
import phoenix.service.CategoriesService;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class TransactionService {

	private final EurekaClient eurekaClient;

	public TransactionService(EurekaClient eurekaClient) {
		this.eurekaClient = eurekaClient;
	}

	public boolean processTransaction(String product1, String product2) {
		Application product1App = eurekaClient.getApplication(product1);
		Application product2App = eurekaClient.getApplication(product2);

		if (product1App == null || product2App == null) {
			// one of the products is not registered on the Eureka server
			return false;
		}

		InstanceInfo product1Instance = product1App.getInstances().get(0);
		InstanceInfo product2Instance = product2App.getInstances().get(0);

		if (product1Instance.getStatus() != InstanceInfo.InstanceStatus.UP
				|| product2Instance.getStatus() != InstanceInfo.InstanceStatus.UP) {
			// one of the products is not in a healthy state
			return false;
		}

		// perform the transaction logic here

		return true;
	}

	public void registerTransactionService() {
		eurekaClient.register(new EurekaInstanceConfig());
	}
}
