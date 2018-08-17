package test.eshop.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class EshopInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopInventoryApplication.class, args);
	}
}
