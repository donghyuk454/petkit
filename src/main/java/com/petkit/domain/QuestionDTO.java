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
public class QuestionDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("q_id")
	private int q_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("r_id")
	private Integer r_id;
	@JsonProperty("question")
	private String question;
	@JsonProperty("answer")
	private String answer;
	@JsonProperty("imgs")
	private String imgs;

	
	public QuestionDTO(Object idx, Object q_id, Object u_id, Object r_id, Object question, Object answer, Object imgs) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (q_id != null)
			this.q_id = Integer.parseInt(q_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (r_id != null)
			this.r_id = Integer.parseInt(r_id.toString());
		if (question != null)
			this.question = question.toString();
		if (answer != null)
			this.answer = answer.toString();
		if (imgs != null)
			this.imgs = imgs.toString();
	}
	
	public QuestionDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("q_id") != null)
			this.q_id = Integer.parseInt(map.get("q_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("r_id") != null)
			this.r_id = Integer.parseInt(map.get("r_id").toString());
		if (map.get("question") != null)
			this.question =  map.get("question").toString();
		if (map.get("answer") != null)
			this.answer =  map.get("answer").toString();
	}
}
