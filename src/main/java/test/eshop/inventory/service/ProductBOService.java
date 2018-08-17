package test.eshop.inventory.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import test.eshop.inventory.api.dto.ProductDTO;

public interface ProductBOService {
	
	ProductDTO saveProduct(@NotNull @Valid final ProductDTO productDTO); 
    List<ProductDTO> getList(); 
    ProductDTO getProduct(Long id); 
    ProductDTO getProduct(String productId); 
    void deleteProduct(final Long id);
    void deleteProduct(final String productId);
}
