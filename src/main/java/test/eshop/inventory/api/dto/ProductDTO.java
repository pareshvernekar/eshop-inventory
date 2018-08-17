package test.eshop.inventory.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ProductDTO.ProductDTOBuilder.class)
public class ProductDTO{

	String productName;
	
	String productId;
	
	String shortDesc;
	
	BigDecimal price;
	
	@JsonPOJOBuilder(withPrefix = "")
    public static class ProductDTOBuilder {

    }
}
