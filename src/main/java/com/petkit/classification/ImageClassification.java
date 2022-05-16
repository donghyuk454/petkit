package com.petkit.classification;
/**
 * Image file 종류 분류
 * @author 	이동혁
 * */
public enum ImageClassification {
	
	NONE, QUESTION, REVIEW, RECIPE_DETAIL, RECIPE;
	
	public static String getName(ImageClassification category) {
		
		switch(category) {
		case NONE: return "";
		case QUESTION: return "question";
		case REVIEW: return "review";
		case RECIPE_DETAIL: return "recipe_detail";
		case RECIPE: return "recipe";
		default:
			return null;
		}
	}
	
}
