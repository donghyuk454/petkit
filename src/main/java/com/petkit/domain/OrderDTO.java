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
public class OrderDTO {
	/**
	 * 
	 */
	
	@JsonProperty("idx")
	private Integer idx;
	@JsonProperty("o_id")
	private Integer o_id;
	@JsonProperty("u_id")
	private Integer u_id;
	@JsonProperty("pro_id")
	private Integer pro_id;
	@JsonProperty("cnt")
	private Integer cnt;
	@JsonProperty("discount")
	private Float discount;
	@JsonProperty("discount_rate")
	private Float discount_rate;
	@JsonProperty("price")
	private Integer price;
	@JsonProperty("pay_id")
	private Integer pay_id;
	@JsonProperty("time")
	private Date time;
	@JsonProperty("addr")
	private String addr;
	@JsonProperty("addr_detail")
	private String addr_detail;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("code")
	private String code;
	@JsonProperty("invoice")
	private String invoice;
	
	
	public OrderDTO(Object idx, Object o_id, Object u_id, Object pro_id, Object cnt, Object discount, Object discount_rate, Object price, Object pay_id, Object time, Object addr, Object addr_detail, Object status, Object code, Object invoice) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (o_id != null)
			this.o_id = Integer.parseInt(o_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (pro_id != null)
			this.pro_id = Integer.parseInt(pro_id.toString());
		if (cnt != null)
			this.cnt = Integer.parseInt(cnt.toString());
		if (discount != null)
			this.discount = Float.parseFloat(discount.toString());
		if (discount_rate != null)
			this.discount_rate = Float.parseFloat(discount_rate.toString());
		if (price != null)
			this.price = Integer.parseInt(price.toString());
		if (pay_id != null)
			this.pay_id = Integer.parseInt(pay_id.toString());
		if (time != null)
			this.time =  java.sql.Date.valueOf(time.toString());
		if (addr != null)
			this.addr = addr.toString();
		if (addr_detail != null)
			this.addr_detail = addr_detail.toString();
		if (status != null)
			this.status = Integer.parseInt(status.toString());
		if (code != null)
			this.code = code.toString();
		if (invoice != null)
			this.invoice = invoice.toString();
	}
	
	public OrderDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("o_id") != null)
			this.o_id = Integer.parseInt(map.get("o_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("pro_id") != null)
			this.pro_id = Integer.parseInt(map.get("pro_id").toString());
		if (map.get("cnt") != null)
			this.cnt = Integer.parseInt(map.get("cnt").toString());
		if (map.get("discount") != null)
			this.discount = Float.parseFloat(map.get("discount").toString());
		if (map.get("discount_rate") != null)
			this.discount_rate = Float.parseFloat(map.get("discount_rate").toString());
		if (map.get("price") != null)
			this.price = Integer.parseInt(map.get("price").toString());
		if (map.get("pay_id") != null)
			this.pay_id = Integer.parseInt(map.get("pay_id").toString());
		if (map.get("time") != null)
			this.time = java.sql.Date.valueOf(map.get("time").toString());
		if (map.get("addr") != null)
			this.addr = map.get("addr").toString();
		if (map.get("addr_detail") != null)
			this.addr_detail = map.get("addr_detail").toString();
		if (map.get("status") != null)
			this.status = Integer.parseInt(map.get("status").toString());
		if (map.get("code") != null)
			this.code = map.get("code").toString();
		if (map.get("invoice") != null)
			this.invoice = map.get("invoice").toString();
	}
}
