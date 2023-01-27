package phoenix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import phoenix.config.MigrationConfiguration;
import phoenix.domain.Categories;
import phoenix.service.CategoriesService;

import java.util.List;

@Slf4j
@SpringBootApplication
public class ProductAppApplication implements CommandLineRunner  {

	@Autowired
	CategoriesService categoriesService;

	@Autowired
	MigrationConfiguration migrationConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(ProductAppApplication.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("migrationSwitch is : {} ", (migrationConfiguration.getMigration()));

		if (migrationConfiguration.getMigration()) {
			List<Categories> list = List.of(Categories.builder().categoryName("phone").build(),
					Categories.builder().categoryName("bike").build());
			categoriesService.saveAll(list);
		}

	}

}
