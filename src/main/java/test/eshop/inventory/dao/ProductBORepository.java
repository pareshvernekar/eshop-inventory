package test.eshop.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import test.eshop.inventory.api.bo.ProductBO;

public interface ProductBORepository extends JpaRepository<ProductBO, Long>{
	
	public ProductBO findByProductId(String productId);
}
