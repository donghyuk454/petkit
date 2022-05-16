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
public class UserCouponDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("c_id")
	private int c_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("is_avail")
	private boolean is_avail;
	@JsonProperty("term_start")
	private Date term_start;
	@JsonProperty("term_end")
	private Date term_end;
	
	public UserCouponDTO(Object idx, Object c_id, Object u_id, Object is_avail, Object term_start, Object term_end) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString()) ;
		if (c_id != null)
			this.c_id = Integer.parseInt(c_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (is_avail != null)
			this.is_avail = Integer.parseInt(is_avail.toString()) != 0;
		if (term_start != null)
			this.term_start = (Date) term_start;
		if (term_end != null)
			this.term_end = (Date) term_end;
	}
	
	public UserCouponDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("c_id") != null)
			this.c_id = Integer.parseInt(map.get("c_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("is_avail") != null)
			this.is_avail = (boolean) map.get("is_avail");
		if (map.get("term_start") != null)
			this.term_start = java.sql.Date.valueOf(map.get("term_start").toString());
		if (map.get("term_end") != null)
			this.term_end = java.sql.Date.valueOf(map.get("term_end").toString());
	}
}
