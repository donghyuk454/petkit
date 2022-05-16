package com.petkit.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petkit.domain.CouponDTO;
import com.petkit.domain.NoticeDTO;
import com.petkit.domain.OrderDTO;
import com.petkit.domain.QuestionDTO;
import com.petkit.domain.UserCouponDTO;

@Mapper
public interface AdminMapper {
	
	// / admin
	int getAdminUserCnt(String id, String passwd) throws Exception;
	void addAdmin(String id, String passwd) throws Exception;
	void setAdmin(String id, String passwd) throws Exception;
	void deleteAdmin(String id) throws Exception;
	
	// /admin/order
	@Select("SELECT A.u_id, A.o_id, A.pro_id, A.cnt, B.u_name, C.name, A.discount, A.discount_rate, A.price, A.time, A.addr, A.addr_detail, A.status, A.code, A.invoice FROM orders A JOIN user B ON A.u_id=B.u_id JOIN product C ON A.pro_id=C.pro_id WHERE A.status < 4;")
	List<HashMap<String, Object>> getAdminOrderList() throws Exception;
	HashMap<String, Object> getAdminOrder(int o_id) throws Exception;
	void setAdminOrder(OrderDTO orderDTO) throws Exception;
	void deleteAdminOrder(int o_id) throws Exception;
	
	
	// /admin/question
	@Select("SELECT A.q_id, A.u_id, A.r_id, B.u_name, A.question, A.answer, A.imgs FROM question A JOIN user B ON A.u_id = B.u_id WHERE A.answer IS NULL;")
	List<HashMap<String, Object>> getAdminQuestionList() throws Exception;
	HashMap<String, Object> getAdminQuestion(int q_id) throws Exception;
	void setAdminQuestion(int q_id, String answer) throws Exception;
	void deleteAdminQuestion(int q_id) throws Exception;
	
	// /admin/notice , /admin/notice/main
	@Select("SELECT * FROM notice;")
	List<HashMap<String, Object>> getAdminNoticeList() throws Exception;
	@Select("SELECT * FROM main_notice;")
	List<HashMap<String, Object>> getAdminMainNoticeList() throws Exception;
	HashMap<String, Object> getAdminNotice(int n_id) throws Exception;
	HashMap<String, Object> getAdminMainNotice(int n_id) throws Exception;
	void addAdminNotice(NoticeDTO noticeDTO) throws Exception;
	void addAdminMainNotice(NoticeDTO noticeDTO) throws Exception;
	void setAdminNotice(NoticeDTO noticeDTO) throws Exception;
	void setAdminMainNotice(NoticeDTO noticeDTO) throws Exception;
	void deleteAdminNotice(int n_id) throws Exception;
	void deleteAdminMainNotice(int n_id) throws Exception;
	
	// /admin/coupon
	HashMap<String, Object> getAdminCoupon(int c_id) throws Exception;
	void addAdminCoupon(CouponDTO couponDTO) throws Exception;
	void setAdminCoupon(CouponDTO couponDTO) throws Exception;
	void deleteAdminCoupon(int c_id) throws Exception;
	void addUserCoupon(UserCouponDTO userCouponDTO) throws Exception;
}
