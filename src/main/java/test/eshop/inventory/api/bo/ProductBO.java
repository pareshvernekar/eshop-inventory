package test.eshop.inventory.api.bo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBO {
	
	@Id
	@NotNull
	@GeneratedValue
	@Column(name="id", nullable=false, updatable=false)
	Long id;
	
	@NotNull
	@Size(max=100)
	@Column(name="productName",nullable=false)
	String productName;
	
	@NotNull
	@Size(max=100)
	@Column(name="productId",nullable=false)
	String productId;
	
	@NotNull
	@Column(name="price",nullable=false)
	BigDecimal price;
	
	@NotNull
	@Column(name="shortDesc",nullable=false)
	String shortDesc;
	
	@NotNull
	@Column(name="active",nullable=false)
	boolean active;
}



