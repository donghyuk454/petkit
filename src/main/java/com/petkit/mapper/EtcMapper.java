package com.petkit.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petkit.domain.QuestionDTO;

@Mapper
public interface EtcMapper {
	@Select("SELECT * FROM main_notice;")
	List<HashMap<String, Object>> getAllMainNotice() throws Exception;
	List<HashMap<String, Object>> getNotice(int cnt) throws Exception;
	
	List<HashMap<String, Object>> myQuestionList(int u_id) throws Exception;
	HashMap<String, Object> myQuestion(int u_id, int q_id) throws Exception;
	void addQuestion(QuestionDTO qDTO) throws Exception;
	void setQuestion(QuestionDTO qDTO) throws Exception;
	void deleteQuestion(int u_id, int q_id) throws Exception;
}
