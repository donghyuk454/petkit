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
public class PetDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("p_id")
	private int p_id;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("is_main")
	private Boolean is_main;
	@JsonProperty("p_name")
	private String p_name;
	@JsonProperty("breed")
	private String breed;
	@JsonProperty("breed_detail")
	private String breed_detail;
	@JsonProperty("p_gender")
	private int p_gender;
	@JsonProperty("p_birth")
	private Date p_birth;
	@JsonProperty("is_preg")
	private Boolean is_preg;
	@JsonProperty("is_neut")
	private Boolean is_neut;
	@JsonProperty("sur1")
	private String sur1;
	@JsonProperty("sur2")
	private String sur2;
	@JsonProperty("sur3")
	private String sur3;
	
	public PetDTO(Object idx, Object p_id, Object u_id, Object is_main, Object p_name, Object breed, Object breed_detail, Object p_gender, Object p_birth, Object is_preg, Object is_neut, Object sur1, Object sur2, Object sur3) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (p_id != null)
			this.p_id = Integer.parseInt(p_id.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (is_main != null)
			this.is_main = Integer.parseInt(is_main.toString()) != 0;
		if (p_name != null)
			this.p_name = p_name.toString();
		if (breed != null)
			this.breed = breed.toString();
		if (breed_detail != null)
			this.breed_detail = breed_detail.toString();
		if (p_gender != null)
			this.p_gender = Integer.parseInt(p_gender.toString());
		if (p_birth != null)
			this.p_birth = java.sql.Date.valueOf(p_birth.toString());
		if (is_preg != null)
			this.is_preg = Integer.parseInt(is_preg.toString()) != 0;
		if (is_neut != null)
			this.is_neut = Integer.parseInt(is_neut.toString()) != 0;
		if (sur1 != null)
			this.sur1 = sur1.toString();
		if (sur2 != null)
			this.sur2 = sur2.toString();
		if (sur3 != null)
			this.sur3 = sur3.toString();
	}
	
	public PetDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = Integer.parseInt(map.get("idx").toString());
		if (map.get("p_id") != null)
			this.p_id = Integer.parseInt(map.get("p_id").toString());
		if (map.get("u_id") != null)
			this.u_id = Integer.parseInt(map.get("u_id").toString());
		if (map.get("is_main") != null)
			this.is_main = (Boolean) map.get("is_main");
		if (map.get("p_name") != null)
			this.p_name = map.get("p_name").toString();
		if (map.get("breed") != null)
			this.breed = map.get("breed").toString();
		if (map.get("breed_detail") != null)
			this.breed_detail = map.get("breed_detail").toString();
		if (map.get("p_gender") != null)
			this.p_gender = Integer.parseInt(map.get("p_gender").toString());
		if (map.get("p_birth") != null)
			this.p_birth = java.sql.Date.valueOf(map.get("p_birth").toString());
		if (map.get("is_preg") != null)
			this.is_preg = (Boolean) map.get("is_preg");
		if (map.get("is_neut") != null)
			this.is_neut = (Boolean) map.get("is_neut");
		if (map.get("sur1") != null)
			this.sur1 = map.get("sur1").toString();
		if (map.get("sur2") != null)
			this.sur2 = map.get("sur2").toString();
		if (map.get("sur3") != null)
			this.sur3 = map.get("sur3").toString();
	}
}
