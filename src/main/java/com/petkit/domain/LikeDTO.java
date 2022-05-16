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
public class LikeDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("l_id")
	private int l_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("r_id")
	private int r_id;


	
	public LikeDTO(Object idx, Object l_id, Object u_id, Object r_id) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (l_id != null)
			this.l_id = (int) l_id;
		if (u_id != null)
			this.u_id = (int) u_id;
		if (r_id != null)
			this.r_id = (int) r_id;
	}
	
	public LikeDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("l_id") != null)
			this.l_id = (int) map.get("l_id");
		if (map.get("u_id") != null)
			this.u_id = (int) map.get("u_id");
		if (map.get("r_id") != null)
			this.r_id = (int) map.get("r_id");
	}
}
