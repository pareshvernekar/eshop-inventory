package test.eshop.inventory.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.eshop.inventory.api.bo.ProductBO;
import test.eshop.inventory.api.dto.ProductDTO;
import test.eshop.inventory.dao.ProductBORepository;

@Service
public class ProductBOServiceImpl implements ProductBOService {

	@Autowired
	ProductBORepository productBORep;

	@Override
	public ProductDTO saveProduct(@NotNull @Valid ProductDTO productDTO) {

		ProductBO productBO = ProductBO.builder().productId(productDTO.getProductId())
							.price(productDTO.getPrice())
							.productName(productDTO.getProductName())
							.shortDesc(productDTO.getShortDesc())
							.build();
		productBORep.save(productBO);
		return productDTO;
	}

	@Override
	public List<ProductDTO> getList() {

		List<ProductBO> productBOList = productBORep.findAll();
		if (productBOList == null || productBOList.isEmpty())
			throw new RuntimeException("No products available");

		List<ProductDTO> productDTOList = new ArrayList<>(productBOList.size());
		for (ProductBO bo : productBOList) {
			productDTOList.
				add(ProductDTO.builder().
						price(bo.getPrice()).
						productName(bo.getProductName()).
						productId(bo.getProductId()).
						shortDesc(bo.getShortDesc()).
						build());
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProduct(Long id) {
		// TODO Auto-generated method stub

		ProductBO bo = productBORep.getOne(id);
		if (bo == null) {
			throw new RuntimeException("Product not found for id: " + id);
		}

		ProductDTO dto = ProductDTO.builder().
				price(bo.getPrice()).
				productName(bo.getProductName()).
				productId(bo.getProductId()).
				shortDesc(bo.getShortDesc()).
				build();
		return dto;
	}

	@Override
	public ProductDTO getProduct(String productId) {
		// TODO Auto-generated method stub
		ProductBO bo = productBORep.findByProductId(productId);
		if (bo == null) {
			throw new RuntimeException("Product not found for id: " + productId);
		}
		ProductDTO dto = ProductDTO.builder().
				price(bo.getPrice()).
				productName(bo.getProductName()).
				productId(bo.getProductId()).
				shortDesc(bo.getShortDesc()).
				build();
		return dto;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		ProductBO bo = productBORep.getOne(id);
		if (bo == null) {
			throw new RuntimeException("Product not found for id: " + id);
		}
		bo.setActive(Boolean.FALSE);
		productBORep.save(bo);
	}

	@Override
	public void deleteProduct(String productId) {
		
		// TODO Auto-generated method stub
		ProductBO bo = productBORep.findByProductId(productId);
		if (bo == null) {
			throw new RuntimeException("Product not found for id: " + productId);
		}
		bo.setActive(Boolean.FALSE);
		productBORep.save(bo);
	}

}
