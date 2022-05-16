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

import com.petkit.domain.CouponDTO;
import com.petkit.domain.NoticeDTO;
import com.petkit.domain.OrderDTO;
import com.petkit.domain.UserCouponDTO;
import com.petkit.mapper.AdminMapper;


@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	@Autowired
	private AdminMapper adminMapper;
	
	/** /admin
	 * 관리자 로그인
	 * @param	String		id
	 * @param	String		passwd
	 * @return	boolean		isLogin
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	@ResponseBody()
	public Boolean adminLogin(
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="passwd", defaultValue="") String passwd) throws Exception{
		System.out.print(id);
		System.out.print(passwd);
		
		if (id.equals("") || passwd.equals(""))
			return null;
		
		try {
			if (adminMapper.getAdminUserCnt(id, passwd) > 0) {
				return true;
			}
			
			return false;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /admin
	 * 관리자 추가
	 * @param	String		id
	 * @param	String		passwd
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	@ResponseBody()
	public void addAdmin(@RequestBody HashMap<String, Object> param) throws Exception{
		String id = "";
		String passwd = "";
		
		if (param.get("id") != null)
			id = param.get("id").toString();		
		if (param.get("passwd") != null)
			passwd = param.get("passwd").toString();
		
		adminMapper.addAdmin(id, passwd);
	}
	
	/** /admin
	 * 관리자 비밀번호 수정
	 * @param	String		id
	 * @param	String		passwd
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin", method=RequestMethod.PUT)
	@ResponseBody()
	public void setAdmin(@RequestBody HashMap<String, Object> param) throws Exception{
		String id = "";
		String passwd = "";
		
		if (param.get("id") != null)
			id = param.get("id").toString();		
		if (param.get("passwd") != null)
			passwd = param.get("passwd").toString();
		
		adminMapper.setAdmin(id, passwd);
	}
	
	/** /admin
	 * 관리자 삭제
	 * @param	String		id
	 * @param	String		passwd
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin", method=RequestMethod.DELETE)
	@ResponseBody()
	public void deleteAdmin(@RequestBody HashMap<String, Object> param) throws Exception{
		String id = "";
		
		if (param.get("id") != null)
			id = param.get("id").toString();		
		
		adminMapper.deleteAdmin(id);
	}
	
	/** /admin/order/list
	 * 주문 내역 조회
	 * @return	List		주문내역
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/order/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getOrderList() throws Exception{
		try {
			List<HashMap<String, Object>> result = adminMapper.getAdminOrderList();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /admin/order
	 * 주문 정보 조회
	 * @param	int			o_id
	 * @return	HashMap		주문 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/order", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getOrder(@RequestParam(value="o_id", defaultValue="-1") int o_id) throws Exception{
		if (o_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = adminMapper.getAdminOrder(o_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;		
	}
	
	/** /admin/order
	 * 주문 정보 수정
	 * @param	HashMap		DB 중 수정할 정보
	 * @return	boolean		수정 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/order", method=RequestMethod.PUT)
	@ResponseBody()
	public boolean setOrder(@RequestBody OrderDTO orderDTO) throws Exception{
		
		try {
			//OrderDTO orderDTO = new OrderDTO(param);
			adminMapper.setAdminOrder(orderDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/order
	 * 주문 정보 삭제
	 * @param	int		삭제할 주문 o_id
	 * @return	boolean		삭제 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/order", method=RequestMethod.DELETE)
	@ResponseBody()
	public boolean deleteOrder(@RequestBody HashMap<String, Object> param) throws Exception{
		int o_id;
		if (param.get("o_id") != null) {
			o_id = (int) param.get("o_id");
		} else return false;
		
		try {
			adminMapper.deleteAdminOrder(o_id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/** /admin/question/list
	 * 문의 내역 조회
	 * @return	List		주문내역
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/question/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getQuestionList() throws Exception{
		try {
			List<HashMap<String, Object>> result = adminMapper.getAdminQuestionList();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /admin/question
	 * 특정 문의 정보 조회
	 * @param	int			q_id
	 * @return	HashMap		문의 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/question", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getQuestion(@RequestParam(value="q_id", defaultValue="-1") int q_id) throws Exception{
		if (q_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = adminMapper.getAdminQuestion(q_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** /admin/question
	 * 문의 답변 추가 및 수정
	 * @param	int			q_id
	 * @param	String		answer
	 * @return	boolean		수정 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/question", method=RequestMethod.PUT)
	@ResponseBody()
	public boolean setQuestion(@RequestBody HashMap<String, Object> param) throws Exception{
		int q_id;
		String answer;
		
		if (param.get("q_id") != null) {
			q_id = (int) param.get("q_id");
		} else return false;
		if (param.get("answer") != null) {
			answer = param.get("answer").toString();
		} else return false;
		
		try {
			adminMapper.setAdminQuestion(q_id, answer);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/question
	 * 주문 정보 삭제
	 * @param	int			q_id
	 * @return	boolean		삭제 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/question", method=RequestMethod.DELETE)
	@ResponseBody()
	public boolean deleteQuestion(@RequestBody HashMap<String, Object> param) throws Exception{
		int q_id;
		if (param.get("q_id") != null) {
			q_id = (int) param.get("q_id");
		} else return false;
		
		try {
			adminMapper.deleteAdminQuestion(q_id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice/list
	 * 일반 공지 내역 조회
	 * @return	List		공지리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getNoticeList() throws Exception{
		try {
			List<HashMap<String, Object>> result = adminMapper.getAdminNoticeList();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /admin/notice/main/list
	 * 중요 공지 내역 조회
	 * @return	List		중요 공지리스트
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/main/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getMainNoticeList() throws Exception{
		try {
			List<HashMap<String, Object>> result = adminMapper.getAdminMainNoticeList();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /admin/notice
	 * 특정 공지사항 정보 조회
	 * @param	int			n_id
	 * @return	HashMap		공지 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getNotice(@RequestParam(value="n_id", defaultValue="-1") int n_id) throws Exception{
		if (n_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = adminMapper.getAdminNotice(n_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** /admin/notice
	 * 공지사항 추가
	 * @param	HashMap		공지 정보
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice", method=RequestMethod.POST)
	@ResponseBody()
	public Boolean addNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			NoticeDTO noticeDTO = new NoticeDTO(param);
			adminMapper.addAdminNotice(noticeDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice
	 * 공지사항 수정
	 * @param	HashMap		공지 정보
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice", method=RequestMethod.PUT)
	@ResponseBody()
	public Boolean setNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			NoticeDTO noticeDTO = new NoticeDTO(param);
			adminMapper.setAdminNotice(noticeDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice
	 * 공지사항 삭제
	 * @param	int			n_id
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice", method=RequestMethod.DELETE)
	@ResponseBody()
	public Boolean deleteNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		int n_id;
		if (param.get("n_id") != null) {
			n_id = (int) param.get("n_id");
		} else return null;
		
		
		try {
			adminMapper.deleteAdminNotice(n_id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice/main
	 * 특정 중요 공지사항 정보 조회
	 * @param	int			n_id
	 * @return	HashMap		중요 공지 정보
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/main", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getMainNotice(@RequestParam(value="n_id", defaultValue="-1") int n_id) throws Exception{
		if (n_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = adminMapper.getAdminMainNotice(n_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** /admin/notice/main
	 * 중요 공지사항 추가
	 * @param	HashMap		공지 정보
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/main", method=RequestMethod.POST)
	@ResponseBody()
	public Boolean addMainNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			NoticeDTO noticeDTO = new NoticeDTO(param);
			adminMapper.addAdminMainNotice(noticeDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice/main
	 * 중요 공지사항 수정
	 * @param	HashMap		공지 정보
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/main", method=RequestMethod.PUT)
	@ResponseBody()
	public Boolean setMainNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			NoticeDTO noticeDTO = new NoticeDTO(param);
			adminMapper.setAdminMainNotice(noticeDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/notice/main
	 * 중요 공지사항 삭제
	 * @param	int			n_id
	 * @return	Boolean		성공 여부
	 * @author 이동혁
	 * */
	@RequestMapping(value="/admin/notice/main", method=RequestMethod.DELETE)
	@ResponseBody()
	public Boolean deleteMainNotice(@RequestBody HashMap<String, Object> param) throws Exception{
		int n_id;
		if (param.get("n_id") != null) {
			n_id = (int) param.get("n_id");
		} else return null;
		
		
		try {
			adminMapper.deleteAdminMainNotice(n_id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/** /admin/coupon
	 * 특정 쿠폰 내용 조회
	 * @param	int			c_id
	 * @return	HashMap		쿠폰 내용
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/admin/coupon", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getAdminCoupon(@RequestParam(value="c_id", defaultValue="-1") int c_id) throws Exception{
		if (c_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = adminMapper.getAdminCoupon(c_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/** /admin/coupon
	 * 쿠폰 추가
	 * @param	HashMap		쿠폰 정보
	 * @return	Boolean		성공 여부
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/admin/coupon", method=RequestMethod.POST)
	@ResponseBody()
	public Boolean addAdminCoupon(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			CouponDTO couponDTO = new CouponDTO(param);
			adminMapper.addAdminCoupon(couponDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/coupon
	 * 쿠폰 내용 수정
	 * @param	HashMap		쿠폰 정보
	 * @return	Boolean		성공 여부
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/admin/coupon", method=RequestMethod.PUT)
	@ResponseBody()
	public Boolean setAdminCoupon(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			CouponDTO couponDTO = new CouponDTO(param);
			adminMapper.setAdminCoupon(couponDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** /admin/coupon
	 * 쿠폰 삭제
	 * @param	int			c_id
	 * @return	Boolean		성공 여부
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/admin/coupon", method=RequestMethod.DELETE)
	@ResponseBody()
	public Boolean deleteAdminCoupon(@RequestBody HashMap<String, Object> param) throws Exception{
		int c_id;
		if (param.get("c_id") != null) {
			c_id = (int) param.get("c_id");
		} else return null;
		
		
		try {
			adminMapper.deleteAdminCoupon(c_id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/** /admin/coupon/user
	 * 사용자 쿠폰 추가
	 * @param	HashMap		쿠폰 정보
	 * @return	Boolean		성공 여부
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/admin/coupon/user", method=RequestMethod.POST)
	@ResponseBody()
	public Boolean addUserCoupon(@RequestBody HashMap<String, Object> param) throws Exception{
		
		try {
			UserCouponDTO couponDTO = new UserCouponDTO(param);
			adminMapper.addUserCoupon(couponDTO);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
