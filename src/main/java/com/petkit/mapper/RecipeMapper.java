package com.petkit.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecipeMapper {
	
	@Select("SELECT r.r_id, r.pro_id, r_name, r.imgs, r.time, b.cnt AS r_cnt, b.star, t.price FROM recipe AS r INNER JOIN (SELECT COUNT(*) AS cnt, AVG(star) AS star FROM reviews GROUP BY r_id) AS b left JOIN (SELECT pro_id, price from product) AS t ON r.pro_id=t.pro_id;")
	List<HashMap<String, Object>> getAllRecipeList() throws Exception;
	
	List<HashMap<String, Object>> getRecipeList() throws Exception;
	HashMap<String, Object> getRecipe(int r_id) throws Exception;
	HashMap<String, Object> getRecipeKit(int r_id) throws Exception;
	List<HashMap<String, Object>> getRecipeReview(int r_id) throws Exception;
	List<HashMap<String, Object>> getRecipeQNA(int r_id) throws Exception;
}
