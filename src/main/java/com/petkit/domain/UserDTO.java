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
public class UserDTO {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@JsonProperty("idx")
	private int idx;
	@JsonProperty("u_id")
	private int u_id;
	@JsonProperty("u_email")
	private String u_email;
	@JsonProperty("passwd")
	private String passwd;
	@JsonProperty("u_name")
	private String u_name;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("alarm")
	private Boolean alarm;
	@JsonProperty("u_gender")
	private int u_gender;
	@JsonProperty("u_birth")
	private String u_birth;
	@JsonProperty("main_pet")
	private Integer main_pet;
	
	public UserDTO(Object idx, Object u_id, Object u_email, Object passwd, Object u_name, Object phone, Object alarm, Object u_gender, Object u_birth, Object main_pet) throws ParseException {
		if (idx != null)
			this.idx = Integer.parseInt(idx.toString());
		if (u_id != null)
			this.u_id = Integer.parseInt(u_id.toString());
		if (u_email != null)
			this.u_email = u_email.toString();
		if (passwd != null)
			this.passwd = passwd.toString();
		if (u_name != null)
			this.u_name = u_name.toString();
		if (phone != null)
			this.phone = phone.toString();
		if (alarm != null)
			this.alarm = Integer.parseInt(alarm.toString()) != 0;
		if (u_gender != null)
			this.u_gender = Integer.parseInt(u_gender.toString());
		if (u_birth != null)
			this.u_birth = u_birth.toString();
		if (main_pet != null)
			this.main_pet = Integer.parseInt(main_pet.toString());
	}
	
	public UserDTO(Object u_email, Object passwd) {
		if (u_email != null)
			this.u_email = u_email.toString();
		if (passwd != null)
			this.passwd = passwd.toString();
		
	}
	
	public UserDTO(HashMap<String, Object> map) {
		if (map.get("idx") != null)
			this.idx = (int) map.get("idx");
		if (map.get("u_id") != null)
			this.u_id = (int) map.get("u_id");
		if (map.get("u_email") != null)
			this.u_email = map.get("u_email").toString();
		if (map.get("passwd") != null)
			this.passwd = map.get("passwd").toString();
		if (map.get("u_name") != null)
			this.u_name = map.get("u_name").toString();
		if (map.get("phone") != null)
			this.phone = map.get("phone").toString();
		if (map.get("alarm") != null)
			this.alarm = (Boolean) map.get("alarm");
		if (map.get("u_gender") != null)
			this.u_gender = (int) map.get("u_gender");
		if (map.get("u_birth") != null)
			this.u_birth = map.get("u_birth").toString();
		if (map.get("main_pet") != null)
			this.main_pet = (Integer) map.get("main_pet");
		
	}
}
