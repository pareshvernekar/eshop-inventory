package test.eshop.inventory.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.eshop.inventory.api.dto.ErrorDTO;
import test.eshop.inventory.api.dto.ProductDTO;
import test.eshop.inventory.api.dto.ResponseDTO;
import test.eshop.inventory.service.ProductBOService;

@RestController
@RequestMapping(value="/v1/inventory")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductBOService service;
	
	@RequestMapping(value="/products",method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAllProducts() {
		
		LOGGER.debug("Received request to retrieve all products");
		try {
			List<ProductDTO> productList = service.getList();
			if (productList==null || productList.isEmpty()) {
				ErrorDTO error = new ErrorDTO("No Products available",HttpStatus.NO_CONTENT.value());
				ResponseDTO<List<ProductDTO>> responseDTO = new ResponseDTO<List<ProductDTO>>(null, error);
				return new ResponseEntity<ResponseDTO<List<ProductDTO>>>(responseDTO, HttpStatus.NO_CONTENT);
			}
			ResponseDTO<List<ProductDTO>> responseDTO = new ResponseDTO<List<ProductDTO>>(productList);
			return new ResponseEntity<ResponseDTO<List<ProductDTO>>>(responseDTO, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error when getting all products:"+ex.getMessage());
			ErrorDTO error = new ErrorDTO("Error retrieving product list",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<List<ProductDTO>> responseDTO = new ResponseDTO<List<ProductDTO>>(null, error);
			return new ResponseEntity<ResponseDTO<List<ProductDTO>>>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/products/{productId}",method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<ProductDTO>> getProduct(@PathVariable("productId") String productId) {
		
		LOGGER.info("Received request to retrieve product "+productId);
		try {
			ProductDTO productDTO = service.getProduct(productId); 
			if (productDTO==null) {
				ErrorDTO error = new ErrorDTO("No Product available for id "+productId,HttpStatus.NO_CONTENT.value());
				ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(null, error);
				return new ResponseEntity<ResponseDTO<ProductDTO>>(responseDTO, HttpStatus.NO_CONTENT);
			}
			ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(productDTO);
			return new ResponseEntity<ResponseDTO<ProductDTO>>(responseDTO, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error when getting product :"+productId+":"+ex.getMessage());
			ErrorDTO error = new ErrorDTO("Error retrieving product list",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(null, error);
			return new ResponseEntity<ResponseDTO<ProductDTO>>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/products",method = RequestMethod.POST)
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
		
		LOGGER.debug("Received request to create new product");
		try {
			service.saveProduct(productDTO);
			return new ResponseEntity<ProductDTO>(HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error("Error when saving a product:"+ex.getMessage());
			ErrorDTO error = new ErrorDTO("Error creating new product",HttpStatus.INTERNAL_SERVER_ERROR.value());
			ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(null, error);
			return new ResponseEntity<ProductDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
