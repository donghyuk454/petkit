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
public class RecipeDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("r_id")
	private int r_id;
	@JsonProperty("pro_ids")
	private int[] pro_ids;
	@JsonProperty("r_name")
	private String r_name;
	@JsonProperty("contents")
	private String[] contents;
	@JsonProperty("imgs")
	private String[] imgs;
	@JsonProperty("time")
	private String time;
	@JsonProperty("s_cnt")
	private int s_cnt;

	
	@SuppressWarnings("unchecked")
	public RecipeDTO(Object idx, Object r_id, Object pro_ids, Object r_name, Object contents, Object imgs, Object time, Object s_cnt) throws ParseException {
		if (idx != null)
			this.idx = (int) idx;
		if (r_id != null)
			this.r_id = (int) r_id;
		if (pro_ids != null) {
			HashMap<String, Object> temp = (HashMap<String, Object>) pro_ids;
			int list[] = new int[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				list[i] = (int) temp.get(i);
			}
			this.pro_ids = list; 
		}
		if (r_name != null)
			this.r_name = r_name.toString();
		if (contents != null) {
			HashMap<String, Object> temp = (HashMap<String, Object>) contents;
			String list[] = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				list[i] = (String) temp.get(i);
			}
			this.contents = list; 
		}
		if (imgs != null) {
			HashMap<String, Object> temp = (HashMap<String, Object>) imgs;
			String list[] = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				list[i] = (String) temp.get(i);
			}
			this.imgs = list; 
		}
		if (time != null)
			this.time = time.toString();
		if (s_cnt != null)
			this.s_cnt = (int) s_cnt;
	}
	
	public RecipeDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("r_id") != null)
			this.r_id = (int) map.get("r_id");
		
		if (map.get("r_name") != null)
			this.r_name =  map.get("r_name").toString();
		
		if (map.get("time") != null)
			this.time =  map.get("time").toString();
		if (map.get("s_cnt") != null)
			this.s_cnt = (int) map.get("s_cnt");
		
	}
}
