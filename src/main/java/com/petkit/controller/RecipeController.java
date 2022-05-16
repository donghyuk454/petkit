package com.petkit.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petkit.mapper.RecipeMapper;

@CrossOrigin(origins = "*")
@RestController
public class RecipeController {
	@Autowired
	private RecipeMapper recipeMapper;
	
	/** /recipe/list
	 * 레시피 리스트 확인
	 * @param 	int 		u_id		유저 아아디
	 * @param 	Boolean 	isFilter	필터 유무
	 * @return 	recipe list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/recipe/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getRecipeList(
			@RequestParam(value="u_id", defaultValue="-1") int u_id, 
			@RequestParam(value="isFilter") Boolean isFilter) throws Exception{
		
		if (u_id == -1) { // 비회원
			try {
				List<HashMap<String, Object>> result = recipeMapper.getAllRecipeList();
				
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		if (isFilter == null)
			return null;
		
		try {
			List<HashMap<String, Object>> result;
			
			if (isFilter) {
				result = recipeMapper.getAllRecipeList();// 수정 필요
			} else {
				result = recipeMapper.getAllRecipeList();
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** /recipe
	 * 레시피 정보
	 * @param 	int 		r_id		 레시피 아아디
	 * @return 	recipe info
	 * @author 이동혁
	 * */
	@RequestMapping(value="/recipe", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getRecipe(@RequestParam(value="r_id", defaultValue="-1") int r_id) throws Exception{
		if (r_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = recipeMapper.getRecipe(r_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /recipe/kit
	 * 레시피 키트 정보
	 * @param 	int 		r_id 		레시피 아아디
	 * @return 	recipe kit info
	 * @author 	이동혁
	 * */ //TODO: 개발 보류
	@RequestMapping(value="/recipe/kit", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getRecipeKit(@RequestParam(value="r_id", defaultValue="-1") int r_id) throws Exception{
		if (r_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = recipeMapper.getRecipeKit(r_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /recipe/review
	 * 레시피 리뷰 리스트 확인
	 * @param 	int 		r_id 		레시피 아아디
	 * @return 	recipe review list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/recipe/review", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getRecipeReview(@RequestParam(value="r_id", defaultValue="-1") int r_id) throws Exception{
		if (r_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> result = recipeMapper.getRecipeReview(r_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /recipe/question
	 * 레시피 Q&A 리스트 확인
	 * @param 	int 		r_id 		 레시피 아아디
	 * @return 	recipe question list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/recipe/question", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getRecipeQNA(@RequestParam(value="r_id", defaultValue="-1") int r_id) throws Exception{
		if (r_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> result = recipeMapper.getRecipeQNA(r_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
