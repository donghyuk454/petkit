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
public class AddressDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("a_id")
	private int a_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("addr")
	private String addr;
	@JsonProperty("addr_detail")
	private String addr_detail;


	
	public AddressDTO(Object idx, Object a_id, Object u_id, Object addr, Object addr_detail) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (a_id != null)
			this.a_id = Integer.parseInt(a_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (addr != null)
			this.addr = addr.toString();
		if (addr_detail != null)
			this.addr_detail = addr_detail.toString();
	}
	
	public AddressDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("a_id") != null)
			this.a_id = Integer.parseInt(map.get("a_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("addr") != null)
			this.addr =  map.get("addr").toString();
		if (map.get("addr_detail") != null)
			this.addr_detail =  map.get("addr_detail").toString();
	}
}
