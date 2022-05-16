package com.petkit.domain;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter // Getter 생성
@Setter // Setter 생성
public class CouponDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("c_id")
	private int c_id;
	@JsonProperty("pro_id")
	private int pro_id;
	@JsonProperty("discount")
	private Float discount;
	@JsonProperty("discount_rate")
	private Float discount_rate;
	
	public CouponDTO(Object idx, Object c_id, Object pro_id, Object discount, Object discount_rate) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString()) ;
		if (c_id != null)
			this.c_id = Integer.parseInt(c_id.toString());
		if (pro_id != null)
			this.pro_id = Integer.parseInt(pro_id.toString());
		if (discount != null)
			this.discount = Float.parseFloat(discount.toString());
		if (discount_rate != null)
			this.discount_rate = Float.parseFloat(discount_rate.toString());
	}
	
	public CouponDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("c_id") != null)
			this.c_id = Integer.parseInt(map.get("c_id").toString());
		if (map.get("pro_id") != null)
			this.pro_id = Integer.parseInt(map.get("pro_id").toString());

		if (map.get("discount") != null)
			this.discount = Float.parseFloat(map.get("discount").toString());
		if (map.get("discount_rate") != null)
			this.discount_rate = Float.parseFloat(map.get("discount_rate").toString());
	}
}
