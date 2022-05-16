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
public class OrderHistoryDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("h_id")
	private int h_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("pro_id")
	private int pro_id;
	@JsonProperty("cnt")
	private int cnt;
	@JsonProperty("discount")
	private float discount;
	@JsonProperty("discount_rate")
	private float discount_rate;
	@JsonProperty("price")
	private int price;
	@JsonProperty("pay_id")
	private int pay_id;
	@JsonProperty("time")
	private Date time;
	@JsonProperty("addr")
	private String addr;
	@JsonProperty("addr_detail")
	private String addr_detail;
	
	
	public OrderHistoryDTO(Object idx, Object h_id, Object u_id, Object pro_id, Object cnt, Object discount, Object discount_rate, Object price, Object pay_id, Object time, Object addr, Object addr_detail) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (h_id != null)
			this.h_id = (int) h_id;
		if (u_id != null)
			this.u_id = (int) u_id;
		if (pro_id != null)
			this.pro_id = (int) pro_id;
		if (cnt != null)
			this.cnt = (int) cnt;
		if (discount != null)
			this.discount = (float) discount;
		if (discount_rate != null)
			this.discount_rate = (float) discount_rate;
		if (price != null)
			this.price = (int) price;
		if (pay_id != null)
			this.pay_id = (int) pay_id;
		if (time != null)
			this.time = (Date) time;
		if (addr != null)
			this.addr = addr.toString();
		if (addr_detail != null)
			this.addr_detail = addr_detail.toString();
		
	}
	
	public OrderHistoryDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("h_id") != null)
			this.h_id = (int) map.get("h_id");
		if (map.get("u_id") != null)
			this.u_id = (int) map.get("u_id");
		if (map.get("pro_id") != null)
			this.pro_id = (int) map.get("pro_id");
		if (map.get("cnt") != null)
			this.cnt = (int) map.get("cnt");
		if (map.get("discount") != null)
			this.discount = (float) map.get("discount");
		if (map.get("discount_rate") != null)
			this.discount_rate = (float) map.get("discount_rate");
		if (map.get("price") != null)
			this.price = (int) map.get("price");
		if (map.get("pay_id") != null)
			this.pay_id = (int) map.get("pay_id");
		if (map.get("time") != null)
			this.time = (Date) map.get("time");
		if (map.get("addr") != null)
			this.addr = map.get("addr").toString();
		if (map.get("addr_detail") != null)
			this.addr_detail = map.get("addr_detail").toString();
		


	}
}
