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
public class FavoriteDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("f_id")
	private int f_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("pro_name")
	private String pro_name;


	
	public FavoriteDTO(Object idx, Object f_id, Object u_id, Object pro_name) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (f_id != null)
			this.f_id = (int) f_id;
		if (u_id != null)
			this.u_id = (int) u_id;
		if (pro_name != null)
			this.pro_name = pro_name.toString();
	}
	
	public FavoriteDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("f_id") != null)
			this.f_id = (int) map.get("f_id");
		if (map.get("u_id") != null)
			this.u_id = (int) map.get("u_id");
		if (map.get("pro_name") != null)
			this.pro_name = map.get("pro_name").toString();
	}
}
