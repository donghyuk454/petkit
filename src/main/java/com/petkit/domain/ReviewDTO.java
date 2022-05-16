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
public class ReviewDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("r_id")
	private int r_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("star")
	private int star;
	@JsonProperty("review")
	private String review;
	@JsonProperty("img")
	private String img;
	@JsonProperty("date")
	private Date date;
	
	public ReviewDTO(Object idx, Object r_id, Object u_id, Object star, Object review, Object img, Object date) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (r_id != null)
			this.r_id = Integer.parseInt(r_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (star != null)
			this.star = Integer.parseInt(star.toString());
		if (review != null)
			this.review = review.toString();
		if (img != null)
			this.img = img.toString();
		if (date != null)
			this.date = java.sql.Date.valueOf(date.toString());
	}
	
	public ReviewDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("r_id") != null)
			this.r_id = Integer.parseInt(map.get("r_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("star") != null)
			this.star = Integer.parseInt(map.get("star").toString());
		if (map.get("review") != null)
			this.review = map.get("review").toString();
		if (map.get("img") != null)
			this.img = map.get("img").toString();
		if (map.get("date") != null)
			this.date = java.sql.Date.valueOf(map.get("date").toString());
	}
}
