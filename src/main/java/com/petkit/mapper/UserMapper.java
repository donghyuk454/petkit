package com.petkit.mapper;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petkit.domain.AddressDTO;
import com.petkit.domain.BasketDTO;
import com.petkit.domain.FavoriteDTO;
import com.petkit.domain.OrderDTO;
import com.petkit.domain.PaymentDTO;
import com.petkit.domain.PetDTO;
import com.petkit.domain.QuestionDTO;
import com.petkit.domain.ReviewDTO;
import com.petkit.domain.UserCouponDTO;
import com.petkit.domain.UserDTO;

@Mapper
public interface UserMapper {
	List<UserDTO> selectUsers(UserDTO userDTO) throws Exception;
	
	@Select("SELECT * FROM USERS")
	List<UserDTO> selectAllUsers();
	/**
	 * /user 관련
	 */
	UserDTO getUser(String email, String passwd) throws Exception;
	void addUser(UserDTO userDTO) throws Exception;
	void setUser(UserDTO userDTO) throws Exception;
	void deleteUser(int u_id) throws Exception;
	
	/**
	 * /user/pet 관련
	 */
	void petReset(int u_id) throws Exception; // 모든 펫 대표값=0
	
	PetDTO getUserPet(int u_id, int p_id) throws Exception;
	void addUserPet(PetDTO petDTO) throws Exception;
	void setUserPet(PetDTO userDTO) throws Exception;
	void deleteUserPet(int u_id, int p_id) throws Exception;
	
	int getPetCnt(int u_id) throws Exception;
	int getIsMainPet(int u_id, int p_id) throws Exception;
	void setNewMainPet(int u_id) throws Exception; // 대표 펫 삭제된 경우
	int getMainPet(int u_id) throws Exception;
	void changeUserMainPet(int u_id, int p_id) throws Exception;
	void changeUserMainPetNULL(int u_id) throws Exception;
	String getPetName(int u_id) throws Exception;
	
	List<HashMap<String, Object>> getUserPetList(int u_id) throws Exception;
	
	/**
	 * /user/coupon 관련
	 */
	
	List<HashMap<String, Object>> getUsedCoupon(int u_id) throws Exception;
	List<HashMap<String, Object>> getUnuseCoupon(int u_id) throws Exception;
	
	UserCouponDTO getUserCoupon(int u_id, int c_id) throws Exception;
	void addUserCoupon(UserCouponDTO c) throws Exception;
	void useCoupon(int u_id, int c_id) throws Exception;
	void deleteUserCoupon(int u_id, int c_id) throws Exception;
	
	/**
	 *  /user/like 관련
	 * */
	
	List<HashMap<String, Object>> getLikeList(int u_id) throws Exception;
	int isLike(int u_id, int r_id) throws Exception;
	void addUserLike(int u_id, int r_id) throws Exception;
	void deleteUserLike(int u_id, int r_id) throws Exception;
	
	/**
	 *  /user/basket 관련
	 * */
	
	List<HashMap<String, Object>> getBasketList(int u_id) throws Exception;
	int getBasketCnt(int u_id) throws Exception;
	void addBasket(BasketDTO basketDTO) throws Exception;
	void setBasket(BasketDTO basketDTO) throws Exception;
	void deleteBasket(int u_id, int b_id) throws Exception;

	/**
	 *  /user/favorite 관련
	 * */
	
	List<HashMap<String, Object>> getFavoriteList(int u_id) throws Exception;
	int getFavoriteExist(FavoriteDTO favoritetDTO) throws Exception;
	void addFavorite(FavoriteDTO favoritetDTO) throws Exception;
	void deleteFavorite(int u_id, int f_id) throws Exception;
	
	/**
	 *  /user/order 관련
	 * */
	
	List<HashMap<String, Object>> getOrderList(int u_id) throws Exception;
	OrderDTO getUserOrder(int u_id, int o_id) throws Exception;
	void addUserOrder(OrderDTO orderDTO) throws Exception;
	void deleteUserOrder(int u_id, int o_id) throws Exception;
	
	
	/**
	 * /user/review
	 * 
	 * */
	
	void addUserReview(ReviewDTO review) throws Exception;
	void setUserReview(ReviewDTO review) throws Exception;
	void deleteUserReview(int u_id, int r_id) throws Exception;
	
	/**
	 *  /user/question
	 * */
	void addUserQuestion(QuestionDTO q) throws Exception;
	
	/**
	 *  /user/detail
	 * */
	UserDTO getUserByUID(int u_id) throws Exception;
	
	/**
	 *  /user/addr 관련
	 * */
	
	List<HashMap<String, Object>> getAddrList(int u_id) throws Exception;
	AddressDTO getUserAddr(int u_id, int a_id) throws Exception;
	void addUserAddr(AddressDTO addrDTO) throws Exception;
	void setUserAddr(AddressDTO addrDTO) throws Exception;
	void deleteUserAddr(int u_id, int a_id) throws Exception;
	
	/**
	 *  /user/payment 관련
	 * */
	
	List<HashMap<String, Object>> getPayList(int u_id) throws Exception;
	PaymentDTO getUserPay(int u_id, int pay_id) throws Exception;
	void addUserPay(PaymentDTO paymentDTO) throws Exception;
	void setUserPay(PaymentDTO paymentDTO) throws Exception;
	void deleteUserPay(int u_id, int pay_id) throws Exception;
	
	void payReset(int u_id) throws Exception;
	int getPayCnt(int u_id) throws Exception;
	int getIsMainPay(int u_id, int pay_id) throws Exception;
	void setNewMainPay(int u_id) throws Exception; // 대표 펫 삭제된 경우
	int getMainPay(int u_id) throws Exception;
	void changeUserMainPay(int u_id, int pay_id) throws Exception;
	void changeUserMainPayNULL(int u_id) throws Exception;
	String getPayName(int u_id) throws Exception;
	
	/**
	 *  /user/search 관련 + /search/ranking
	 * */
	
	List<HashMap<String, Object>> getSearchLogList(int u_id) throws Exception;
	void addSearchLog() throws Exception;
	List<HashMap<String, Object>> getSearchResult(String key) throws Exception;
	List<HashMap<String, Object>> getKeywordRecipe(String keyword) throws Exception;
	List<HashMap<String, Object>> getWordRecipe(String word) throws Exception;
	List<HashMap<String, Object>> getIngredRecipe(String ingred) throws Exception;
	List<HashMap<String, Object>> getSearchRanking() throws Exception;
}
