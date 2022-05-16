package com.petkit.domain;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter // Getter 생성
@Setter // Setter 생성
public class ProductDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("pro_id")
	private int pro_id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private int price;


	
	public ProductDTO(Object idx, Object pro_id, Object name, Object price) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (pro_id != null)
			this.pro_id = (int) pro_id;
		if (name != null)
			this.name = name.toString();
		if (price != null)
			this.price = (int) price;
	}
	
	public ProductDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("pro_id") != null)
			this.pro_id = (int) map.get("pro_id");
		if (map.get("name") != null)
			this.name = map.get("name").toString();
		if (map.get("price") != null)
			this.price = (int) map.get("price");
	}
}
