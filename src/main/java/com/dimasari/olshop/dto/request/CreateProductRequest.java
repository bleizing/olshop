package com.dimasari.olshop.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateProductRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5864201132405063095L;

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String image;
	
	@NotNull(message = "gaboleh kosong ya")
	@Min(value = 100)
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
