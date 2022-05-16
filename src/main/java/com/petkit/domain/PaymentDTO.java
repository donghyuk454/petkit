package com.petkit.domain;

import java.text.ParseException;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter // Getter 생성
@Setter // Setter 생성
public class PaymentDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("pay_id")
	private int pay_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("is_main")
	private Boolean is_main;
	@JsonProperty("is_person")
	private Boolean is_person;
	@JsonProperty("b_name")
	private String b_name;
	@JsonProperty("c_number")
	private String c_number;
	@JsonProperty("c_term")
	private String c_term;
	@JsonProperty("c_passwd")
	private String c_passwd;
	@JsonProperty("c_detail")
	private String c_detail;
	
	public PaymentDTO(Object idx, Object pay_id, Object u_id, Object is_main, Object is_person, Object b_name, Object c_number, Object c_term, Object c_passwd, Object c_detail) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (pay_id != null)
			this.pay_id = Integer.parseInt(pay_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (is_main != null)
			this.is_main =  Integer.parseInt(is_main.toString()) != 0;
		if (is_person != null)
			this.is_person = Integer.parseInt(is_person.toString()) != 0;
		if (b_name != null)
			this.b_name = b_name.toString();
		if (c_number != null)
			this.c_number = c_number.toString();
		if (c_term != null)
			this.c_term = c_term.toString();
		if (c_passwd != null)
			this.c_passwd = c_passwd.toString();
		if (c_detail != null)
			this.c_detail = c_detail.toString();
	}
	
	public PaymentDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("pay_id") != null)
			this.pay_id = Integer.parseInt(map.get("pay_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("is_main") != null)
			this.is_main = (Boolean) map.get("is_main");
		if (map.get("is_person") != null)
			this.is_person = (Boolean) map.get("is_person");
		if (map.get("b_name") != null)
			this.b_name = map.get("b_name").toString();
		if (map.get("c_number") != null)
			this.c_number = map.get("c_number").toString();
		if (map.get("c_term") != null)
			this.c_term = map.get("c_term").toString();
		if (map.get("c_passwd") != null)
			this.c_passwd = map.get("c_passwd").toString();
		if (map.get("c_detail") != null)
			this.c_detail = map.get("c_detail").toString();
	}
}
