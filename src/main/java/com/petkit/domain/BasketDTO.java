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
public class BasketDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("b_id")
	private int b_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("pro_id")
	private int pro_id;
	@JsonProperty("cnt")
	private int cnt;


	
	public BasketDTO(Object idx, Object b_id, Object u_id, Object pro_id, Object cnt) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (b_id != null)
			this.b_id = (int) b_id;
		if (u_id != null)
			this.u_id = (int) u_id;
		if (pro_id != null)
			this.pro_id = (int) pro_id;
		if (cnt != null)
			this.cnt = (int) cnt;
	}
	
	public BasketDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("b_id") != null)
			this.b_id = (int) map.get("b_id");
		if (map.get("u_id") != null)
			this.u_id = (int) map.get("u_id");
		if (map.get("pro_id") != null)
			this.pro_id = (int) map.get("pro_id");
		if (map.get("cnt") != null)
			this.cnt = (int) map.get("cnt");
	}
}
