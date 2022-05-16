package com.petkit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petkit.classification.ImageClassification;
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
import com.petkit.handler.FileHandler;
import com.petkit.mapper.UserMapper;
import com.petkit.service.SecurityService;


@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityService securityService;
	
	private String toke="";
	
	@RequestMapping(value="/")		//
	public String main() {
		String token = securityService.createToken("sub");
		System.out.println(token);
		toke=token;
		return token;		// View의 폴더, 파일 이름을 문자열로 리턴해준다.
	}
	
	@RequestMapping(value="/check")		//
	public boolean main2() {
		return securityService.validateToken(toke);				// View의 폴더, 파일 이름을 문자열로 리턴해준다.
	}
	
	
	// /user 관련
	
	/** /user
	 * 유저 로그인
	 * @param	String	u_email
	 * @param	String	passwd
	 * @return	유저 정보 조회
	 * @author 이동혁
	 * */
	
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public UserDTO getUser(@RequestParam(value="u_email",defaultValue="") String email, @RequestParam(value="passwd",defaultValue="") String passwd) throws Exception{
		
		final UserDTO user = userMapper.getUser(email, passwd);
		System.out.print(user);
		return user;
	}
	
	/** /user
	 * 유저 로그인
	 * @param	UserDTO
	 * @return	유저 추가 요청 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public UserDTO addUser(@RequestBody HashMap<String, Object> param) throws Exception{
		
		final UserDTO user = new UserDTO(param);
		
		userMapper.addUser(user);
				
		return user;
	}

	/** /user
	 * 유저 정보 수정
	 * @param	UserDTO
	 * @return	유저 수정 요청 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public UserDTO setUser(@RequestBody HashMap<String, Object> param) throws Exception{		
		
		final UserDTO user = new UserDTO(param);
		
		userMapper.setUser(user);
				
		return user;
	}
	
	/** /user
	 * 유저 삭제
	 * @param	int		u_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteUser(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");
		
		userMapper.deleteUser(u_id);
	}
	
	// /user/pet 관련
	
	/** /user/pet
	 * 팻 정보 조회
	 * @param	int		u_id
	 * @param	int		p_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/pet", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public PetDTO getUserPet(
			@RequestParam(value="u_id",defaultValue="-1") int u_id, 
			@RequestParam(value="p_id",defaultValue="-1") int p_id) throws Exception{
		if (u_id == -1 || p_id == -1)
			return null;
		PetDTO pet;
		try {
			pet = userMapper.getUserPet(u_id, p_id);
			
			return pet;
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	/** /user/pet
	 * 팻 추가
	 * @param	PetDTO
	 * @return	추가 요청 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/pet", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public PetDTO addUserPet(@RequestBody HashMap<String, Object> param) throws Exception{	
		final PetDTO pet = new PetDTO(param);
		
		if(param.get("is_main") != null && (boolean) param.get("is_main")) {
			int id = Integer.parseInt(param.get("u_id").toString());
			userMapper.petReset(id);
		}
		
		userMapper.addUserPet(pet);
				
		return pet;
	}
	
	/** /user/pet
	 * 팻 정보 수정
	 * @param	PetDTO
	 * @return	수정 요청 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/pet", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public PetDTO setUserPet(@RequestBody HashMap<String, Object> param) throws Exception{
		if(param.get("is_main") != null && (boolean) param.get("is_main")) {
			int id = Integer.parseInt(param.get("u_id").toString());
			userMapper.petReset(id);
		}
			
		final PetDTO pet = new PetDTO(param);
		
		userMapper.setUserPet(pet);
				
		return pet;
	}

	/** /user/pet
	 * 팻 삭제
	 * @param	int		u_id
	 * @param	int		p_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/pet", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteUserPet(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int p_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("p_id") != null)
			p_id = (int) param.get("p_id");

		int cnt = userMapper.getPetCnt(u_id);
		System.out.print(cnt);
		
		if (cnt > 1) {
			int temp = userMapper.getIsMainPet(u_id, p_id);
			System.out.print(temp);
			userMapper.deleteUserPet(u_id, p_id);
			if(temp > 0) {
				userMapper.setNewMainPet(u_id);
				int mainpet = userMapper.getMainPet(u_id);
				
				userMapper.changeUserMainPet(u_id, mainpet);
			}
			return ;
		}
		userMapper.deleteUserPet(u_id, p_id);
		userMapper.changeUserMainPetNULL(u_id);
	}
	
	/** 
	 * /user/pet/list
	 * 팻 리스트 조회
	 * @param	int		u_id
	 * @return	팻 리스트 조회
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/pet/list", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public List<HashMap<String, Object>> getUserPetList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		List<HashMap<String, Object>> petList;
		
		try {
			petList= userMapper.getUserPetList(u_id);
			
			return petList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
				
		return null;
	}
	
	/** 
	 * /user/pet/name
	 * 팻 이름 조회
	 * @param	int		u_id
	 * @return	팻 이름 조회
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/pet/name", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getPetName(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			String name = userMapper.getPetName(u_id);	
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("name", name);
			
			return resultMap;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Coupon 관련
	
	/** 
	 * /user/coupon/used
	 * 사용한 쿠폰 리스트 조회
	 * @param	int		u_id
	 * @return	사용한 쿠폰 리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/coupon/used", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getCouponUsed(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> resultMap = userMapper.getUsedCoupon(u_id);

			return resultMap;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/coupon/unuse
	 * 사용안한 쿠폰 리스트 조회
	 * @param	int		u_id
	 * @return	아직 사용안한 쿠폰 리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/coupon/unuse", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getUnuseCoupon(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> resultMap = userMapper.getUnuseCoupon(u_id);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** 
	 * /user/coupon
	 * 쿠폰 조회
	 * @param	int		u_id
	 * @param	int		c_id
	 * @return	쿠폰 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/coupon", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public UserCouponDTO getUserCoupon(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="c_id", defaultValue="-1") int c_id) throws Exception{	
		if(u_id == -1 || c_id == -1)
			return null;
		
		try {
			final UserCouponDTO coupon = userMapper.getUserCoupon(u_id, c_id);
			
			return coupon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/coupon
	 * 쿠폰 추가
	 * @param	CouponDTO
	 * @return	추가한 쿠폰 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/coupon", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public UserCouponDTO addUserCoupon(@RequestBody HashMap<String, Object> param) throws Exception{	
		final UserCouponDTO coupon = new UserCouponDTO(param);
		
		userMapper.addUserCoupon(coupon);
				
		return coupon;
	}
	
	/** 
	 * /user/coupon
	 * 쿠폰 수정
	 * @param	CouponDTO
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/coupon", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public void useCoupon(@RequestBody HashMap<String, Object> param) throws Exception{		
		int u_id = -1;
		int c_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("c_id") != null)
			c_id = (int) param.get("c_id");
		
		userMapper.useCoupon(u_id,c_id);
	}
	
	/** 
	 * /user/coupon
	 * 쿠폰 삭제
	 * @param	int		u_id
	 * @param	int		c_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/coupon", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteCoupon(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int c_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("c_id") != null)
			c_id = (int) param.get("c_id");
		
		userMapper.deleteUserCoupon(u_id, c_id);
	}
	
	/* 
	 *  /user/like 관련
	 * */
	
	/** 
	 * /user/like/list
	 * 좋아요 리스트 조회
	 * @param	int		u_id
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/like/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getLikeList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> resultMap = userMapper.getLikeList(u_id);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/like
	 * 좋아요 여부 조회
	 * @param	int		u_id
	 * @param	int		r_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/like", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public Boolean getIsLike(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="r_id", defaultValue="-1") int r_id) throws Exception{	
		if (u_id == -1 || r_id == -1)
			return null;
		
		Boolean result = null;
		
		try {
			int isLike = userMapper.isLike(u_id, r_id);
			
			result = isLike > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 
	 * /user/like
	 * 좋아요
	 * @param	int		u_id
	 * @param	int		r_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/like", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public void addUserLike(@RequestBody HashMap<String, Object> param) throws Exception{	
		int u_id = -1;
		int r_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("r_id") != null)
			r_id = (int) param.get("r_id");
		
		userMapper.addUserLike(u_id, r_id);				
	}
	
	/** 
	 * /user/like
	 * 좋아요 삭제
	 * @param	int		u_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/like", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteLike(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int r_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("r_id") != null)
			r_id = (int) param.get("r_id");
		
		userMapper.deleteUserLike(u_id, r_id);
	}
	
	/** 
	 * /user/basket/list
	 * 장바구니 리스트 조회
	 * @param	int		u_id
	 * @return	리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/basket/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getBasketList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> resultMap = userMapper.getBasketList(u_id);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/basket/count
	 * 장바구니 개수
	 * @param	int		u_id
	 * @return	count
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/basket/count", method=RequestMethod.GET)
	@ResponseBody()
	public Integer getBasketCount(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			int count = userMapper.getBasketCnt(u_id);

			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/basket
	 * 장바구니 추가
	 * @param	BasketDTO
	 * @return	요청 보낸 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/basket", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public BasketDTO addUserBasket(@RequestBody HashMap<String, Object> param) throws Exception{	
		final BasketDTO basket = new BasketDTO(param);
		
		userMapper.addBasket(basket);
				
		return basket;
	}
	
	/** 
	 * /user/basket
	 * 장바구니 수정
	 * @param	BasketDTO
	 * @return	요청 보낸 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/basket", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public BasketDTO setUserBasket(@RequestBody HashMap<String, Object> param) throws Exception{		
		BasketDTO basket = new BasketDTO(param);
		
		userMapper.setBasket(basket);
		
		return basket;
	}
	
	/** 
	 * /user/basket
	 * 장바구니 삭제
	 * @param	int		u_id
	 * @param	int		b_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/basket", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteBasket(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int b_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("b_id") != null)
			b_id = (int) param.get("b_id");
		
		userMapper.deleteBasket(u_id, b_id);
	}
	
	/** 
	 * /user/favorite/list
	 * 좋아하는 음식 리스트 조회
	 * @param	int		u_id
	 * @return 	음식 리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value= "/user/favorite/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getFavortieList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> resultMap = userMapper.getFavoriteList(u_id);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/favorite
	 * 특정 주문 조회
	 * @param	FavoriteDTO
	 * @return 	추가된 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/favorite", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public FavoriteDTO addUserFavorite(@RequestBody HashMap<String, Object> param) throws Exception{	
		final FavoriteDTO favor = new FavoriteDTO(param);
		
		if (userMapper.getFavoriteExist(favor) == 0) {
			userMapper.addFavorite(favor);
		}

		return favor;
	}
	
	/** 
	 * /user/favorite
	 * 좋아하는 음식 삭제
	 * @param	int		u_id
	 * @param	int		f_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/favorite", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteFavorite(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int f_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("f_id") != null)
			f_id = (int) param.get("f_id");
		
		userMapper.deleteFavorite(u_id, f_id);
	}
	
	/** 
	 * /user/order/list
	 * 특정 사용자 주문 리스트 조회
	 * @param	int		u_id
	 * @return 	주문 조회 리스트 조회
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/order/list", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public List<HashMap<String, Object>> getUserOrderList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			final List<HashMap<String, Object>> orderList = userMapper.getOrderList(u_id);
			
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/order
	 * 특정 주문 조회
	 * @param	int		u_id
	 * @param	int		o_id
	 * @return 	주문 조회 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/order", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public OrderDTO getUserOrder(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="o_id", defaultValue="-1") int o_id) throws Exception{
		if (u_id == -1 || o_id == -1)
			return null;
		
		OrderDTO order = null;
		try {
			order = userMapper.getUserOrder(u_id, o_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	/** 
	 * /user/order
	 * 주문
	 * @param	OrderDTO
	 * @return 	추가한 주문 내용
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/order", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public OrderDTO addUserOrder(@RequestBody HashMap<String, Object> param) throws Exception{	
		final OrderDTO order = new OrderDTO(param);
		
		userMapper.addUserOrder(order);
				
		return order;
	}
	
	/** 
	 * /user/order
	 * 주문 삭제
	 * @param	int		u_id
	 * @param	int		o_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/order", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteUserOrder(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int o_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("o_id") != null)
			o_id = (int) param.get("o_id");

		userMapper.deleteUserOrder(u_id, o_id);
	}
	
//	/** 
//	 * /user/review
//	 * User 리뷰 상세 정보 조회
//	 * @param	ReviewDTO
//	 * @author 이동혁
//	 * */
//	@RequestMapping(value = "/user/review", method=RequestMethod.POST)
//	@ResponseBody() // JSON
//	public ReviewDTO addUserReview(@RequestBody HashMap<String, Object> param) throws Exception{
//		
//		final ReviewDTO review = new ReviewDTO(param);
//		userMapper.addUserReview(review);
//				
//		return review;
//	}
	/** /user/review
	 * 내 리뷰 추가 //form 형식 사용
	 * @param 	int		u_id		유저 id
	 * @param	int		r_id		레시피 id
	 * @param	file	files		사진 파일 리스트
	 * @param	String	review		리뷰 내용
	 * @return 			review list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/user/review", method=RequestMethod.POST)
	@ResponseBody()
	public ReviewDTO addReview(
			@RequestParam("u_id") int u_id,
			@RequestParam("r_id") Integer r_id,
			@RequestParam("star") Integer star,
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("review") String review) throws Exception{

		FileHandler fh = new FileHandler();

		List<String> paths = fh.uploadImageFiles(files, ImageClassification.REVIEW, Integer.toString(u_id), Integer.toString(r_id));
		String pathList = "";
		
		for(String path: paths) {
			pathList += path;
			pathList += "&";
		}
		
		System.out.println(pathList);
		
		Date temp = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
		java.sql.Date now = java.sql.Date.valueOf(sdf.format(temp));
		
		ReviewDTO reviewDTO = new ReviewDTO(null, u_id, r_id, star, review, pathList, now);
		
		userMapper.addUserReview(reviewDTO);
		
		return reviewDTO;
	}
	
	/** /user/review
	 * 내 리뷰 수정 //form 형식 사용
	 * @param 	int		u_id		유저 id
	 * @param	int		r_id		레시피 id
	 * @param	file	files		사진 파일 리스트
	 * @param	String	review		리뷰 내용
	 * @return 			review list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/user/review", method=RequestMethod.PUT)
	@ResponseBody()
	public ReviewDTO setReview(
			@RequestParam("u_id") int u_id,
			@RequestParam("r_id") Integer r_id,
			@RequestParam("star") Integer star,
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("review") String review) throws Exception{
				
		FileHandler fh = new FileHandler();

		List<String> paths = fh.uploadImageFiles(files, ImageClassification.REVIEW, Integer.toString(u_id), Integer.toString(r_id));
		String pathList = "";
		
		for(String path: paths) {
			pathList += path;
			pathList += "&";
		}
		
		System.out.println(pathList);
		
		Date temp = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date now = java.sql.Date.valueOf(sdf.format(temp));
		
		ReviewDTO reviewDTO = new ReviewDTO(null, u_id, r_id, star, review, pathList, now);
		
		userMapper.setUserReview(reviewDTO);
		
		return reviewDTO;
	}
	
	/** /user/review
	 * 리뷰 삭제 하기
	 * @param 	int		u_id		유저 id
	 * @param 	int		r_id		질문 id
	 * @return 			main notice list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/user/review", method=RequestMethod.DELETE)
	@ResponseBody()
	public boolean deleteReview(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");
		else return false;
		
		int r_id = -1;
		if (param.get("r_id") != null)
			r_id = (int) param.get("r_id");
		else return false;
		
		FileHandler fh = new FileHandler();
		
		userMapper.deleteUserReview(u_id, r_id);
		return fh.deleteFile(ImageClassification.REVIEW, Integer.toString(u_id), Integer.toString(r_id));
	}
	
	/** 
	 * /user/question
	 * Q&A 추가
	 * @param	QuestionDTO
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/question", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public QuestionDTO addUserQuestion(@RequestBody HashMap<String, Object> param) throws Exception{
		
		final QuestionDTO q = new QuestionDTO(param);
		
		userMapper.addUserQuestion(q);
				
		return q;
	}
	
	/** 
	 * /user/detail
	 * User 상세 정보 조회
	 * @param	int		u_id
	 * @return 	상세 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/detail", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public UserDTO getUserByUID(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		UserDTO user = null;
		
		try {
			user = userMapper.getUserByUID(u_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return user;
	}
	
	
	/** 
	 * /user/addr/list
	 * Address List 조회
	 * @param	int		u_id
	 * @return 	Address
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/addr/list", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public List<HashMap<String, Object>> getUserAddrList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			final List<HashMap<String, Object>> addrList = userMapper.getAddrList(u_id);
			
			return addrList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/addr
	 * Address 조회
	 * @param	int		u_id
	 * @param	int		a_id
	 * @return 	Address
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/addr", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public AddressDTO getUserAddr(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="a_id", defaultValue="-1") int a_id) throws Exception{
		if (u_id == -1 || a_id == -1)
			return null;
		
		AddressDTO addr = null;
		
		try {
			addr = userMapper.getUserAddr(u_id, a_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
						
		return addr;
	}
	
	/** 
	 * /user/addr
	 * Address 추가
	 * @param	AddressDTO
	 * @author 이동혁	
	 * */
	@RequestMapping(value = "/user/addr", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public AddressDTO addUserAddr(@RequestBody HashMap<String, Object> param) throws Exception{	
		final AddressDTO addr = new AddressDTO(param);
		
		userMapper.addUserAddr(addr);
				
		return addr;
	}
	
	/** 
	 * /user/addr
	 * Address 수정
	 * @param	AddressDTO
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/addr", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public AddressDTO setUserAddr(@RequestBody HashMap<String, Object> param) throws Exception{	
		final AddressDTO addr = new AddressDTO(param);
		
		userMapper.setUserAddr(addr);
				
		return addr;
	}
	
	/** 
	 * /user/addr
	 * Address 삭제
	 * @param	int		u_id
	 * @param	int		a_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/addr", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteUserAddr(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int a_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("a_id") != null)
			a_id = (int) param.get("a_id");

		userMapper.deleteUserAddr(u_id, a_id);
	}
	
	/** 
	 * /user/payment/list
	 * Payment List 조회
	 * @param	int		u_id
	 * @return	Payment
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/payment/list", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public List<HashMap<String, Object>> getUserPayList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			final List<HashMap<String, Object>> paymentList = userMapper.getPayList(u_id);
			
			return paymentList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * /user/payment
	 * Payment 조회
	 * @param	int		u_id
	 * @param	int		pay_id
	 * @return	Payment
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/payment", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public PaymentDTO getUserPay(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="pay_id", defaultValue="-1") int pay_id) throws Exception{
		if (u_id == -1 || pay_id == -1)
			return null;
		
		PaymentDTO addr = null;
		
		try {
			addr = userMapper.getUserPay(u_id, pay_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
						
		return addr;
	}
	
	/** 
	 * /user/payment
	 * Payment 추가
	 * @param	PaymentDTO
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/payment", method=RequestMethod.POST)
	@ResponseBody() // JSON
	public PaymentDTO addUserPay(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");
		else 
			return null;
		
		if(param.get("is_main") != null && (boolean) param.get("is_main"))
			userMapper.payReset(u_id);
		
		final PaymentDTO payment = new PaymentDTO(param);
		
		userMapper.addUserPay(payment);
				
		return payment;
	}
	
	/** 
	 * /user/payment
	 * Payment 수정
	 * @param	PaymentDTO
	 * @author 이동혁	
	 * */
	@RequestMapping(value = "/user/payment", method=RequestMethod.PUT)
	@ResponseBody() // JSON
	public PaymentDTO setUserPay(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");
		else 
			return null;
		
		if(param.get("is_main") != null && (boolean) param.get("is_main"))
			userMapper.payReset(u_id);
		
		final PaymentDTO payment = new PaymentDTO(param);
		
		userMapper.setUserPay(payment);
				
		return payment;
	}
	
	/** 
	 * /user/payment
	 * Payment 삭제
	 * @param	int		u_id
	 * @param	int		pay_id
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/payment", method=RequestMethod.DELETE)
	@ResponseBody() // JSON
	public void deleteUserPay(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		int pay_id = -1;
		
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");		
		if (param.get("pay_id") != null)
			pay_id = (int) param.get("pay_id");
		
		
		int cnt = userMapper.getPayCnt(u_id);
		System.out.print(cnt);
		
		if (cnt > 1) {
			int temp = userMapper.getIsMainPay(u_id, pay_id);
			System.out.print(temp);
			userMapper.deleteUserPay(u_id, pay_id);
			if(temp > 0) {
				userMapper.setNewMainPay(u_id);
			}
			return ;
		}
		userMapper.deleteUserPay(u_id, pay_id);
	}
	
	/** 
	 * /user/search
	 * Search 결과
	 * @param 	String 	content
	 * @param	int		u_id
	 * @return	List	검색 후 필터링 결과
	 * @author 이동혁
	 * */
	@RequestMapping(value = "/user/search", method=RequestMethod.GET)
	@ResponseBody() // JSON
	public List<HashMap<String, Object>> getSearchResult(
			@RequestParam(value="content", defaultValue="") String content,
			@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		try {
			List<HashMap<String, Object>> result = userMapper.getSearchResult(null);
			
			//TODO: 필터링 필요
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
