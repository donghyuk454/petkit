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
public class NoticeDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("n_id")
	private int n_id;
	@JsonProperty("n_title")
	private String n_title;
	@JsonProperty("n_context")
	private String n_context;


	
	public NoticeDTO(Object idx, Object n_id, Object n_title, Object n_context) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (n_id != null)
			this.n_id = Integer.parseInt(n_id.toString());
		if (n_title != null)
			this.n_title = n_title.toString();
		if (n_context != null)
			this.n_context = n_context.toString();
	}
	
	public NoticeDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("n_id") != null)
			this.n_id = Integer.parseInt(map.get("n_id").toString());
		if (map.get("n_title") != null)
			this.n_title =  map.get("n_title").toString();
		if (map.get("n_context") != null)
			this.n_context =  map.get("n_context").toString();
	}
}
