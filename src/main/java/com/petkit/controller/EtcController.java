package com.petkit.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.petkit.classification.ImageClassification;
import com.petkit.domain.QuestionDTO;
import com.petkit.handler.FileHandler;
import com.petkit.mapper.EtcMapper;


@CrossOrigin(origins = "*")
@RestController
public class EtcController {
	@Autowired
	private EtcMapper etcMapper;
	
	/** /notice/main
	 * 주요 공지 확인
	 * @return main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/notice/main", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getMainNotice() throws Exception{
		return etcMapper.getAllMainNotice();
	}
	
	/** /notice/list
	 * 일반 공지 확인
	 * @param int	cnt		공지 개수
	 * @return 				main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getNotice(@RequestParam(value="cnt", defaultValue="-1") int cnt) throws Exception{
		if (cnt == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> result = etcMapper.getNotice(cnt);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	/** /question/list
	 * 내 문의 리스트 확인
	 * @param int	u_id	유저 id
	 * @return 				main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/question/list", method=RequestMethod.GET)
	@ResponseBody()
	public List<HashMap<String, Object>> getQuestionList(@RequestParam(value="u_id", defaultValue="-1") int u_id) throws Exception{
		if (u_id == -1)
			return null;
		
		try {
			List<HashMap<String, Object>> result = etcMapper.myQuestionList(u_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	/** /question
	 * 내 문의 리스트 확인
	 * @param int	u_id	유저 id
	 * @param int	q_id	질문 id
	 * @return 				main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/question", method=RequestMethod.GET)
	@ResponseBody()
	public HashMap<String, Object> getQuestion(
			@RequestParam(value="u_id", defaultValue="-1") int u_id,
			@RequestParam(value="q_id", defaultValue="-1") int q_id) throws Exception{
		if (u_id == -1 || q_id == -1)
			return null;
		
		try {
			HashMap<String, Object> result = etcMapper.myQuestion(u_id, q_id);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** /question
	 * 내 문의 추가 //form 형식 사용
	 * @param 	int		u_id	유저 id
	 * @param 	int		q_id	질문 id
	 * @param	int		r_id	레시피 id
	 * @param	file	files	사진 파일 리스트
	 * @param	String	question질문 내용
	 * @return 			main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/question", method=RequestMethod.POST)
	@ResponseBody()
	public QuestionDTO addQuestion(
			@RequestParam("q_id") int q_id,
			@RequestParam("u_id") int u_id,
			@RequestParam("r_id") Integer r_id,
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("question") String question) throws Exception{

		FileHandler fh = new FileHandler();

		List<String> paths = fh.uploadImageFiles(files, ImageClassification.QUESTION, Integer.toString(u_id), Integer.toString(q_id));
		String pathList = "";
		
		for(String path: paths) {
			pathList += path;
			pathList += "&";
		}
		
		System.out.println(pathList);
		
		QuestionDTO qDTO = new QuestionDTO(null, q_id, u_id, r_id, question, null, pathList);
		
		etcMapper.addQuestion(qDTO);
		
		return qDTO;
	}
	
	/** /question
	 * 내 문의 수정 //form 형식 사용
	 * @param 	int		u_id	유저 id
	 * @param 	int		q_id	질문 id
	 * @param	int		r_id	레시피 id
	 * @param	file	files	사진 파일 리스트
	 * @param	String	question질문 내용
	 * @return 			main notice list
	 * @author 이동혁
	 * */
	@RequestMapping(value="/question", method=RequestMethod.PUT)
	@ResponseBody()
	public QuestionDTO setQuestion(
			@RequestParam("q_id") int q_id,
			@RequestParam("u_id") int u_id,
			@RequestParam("r_id") Integer r_id,
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("question") String question) throws Exception{
				
		FileHandler fh = new FileHandler();

		List<String> paths = fh.uploadImageFiles(files, ImageClassification.QUESTION, Integer.toString(u_id), Integer.toString(q_id));
		String pathList = "";
		
		for(String path: paths) {
			pathList += path;
			pathList += "&";
		}
		
		System.out.println(pathList);
		
		QuestionDTO qDTO = new QuestionDTO(null, q_id, u_id, r_id, question, null, pathList);
		
		etcMapper.addQuestion(qDTO);
		
		return qDTO;
	}
	
	/** /question
	 * 문의 삭제 하기
	 * @param int	u_id	유저 id
	 * @param int	q_id	질문 id
	 * @return 				main notice list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/question", method=RequestMethod.DELETE)
	@ResponseBody()
	public boolean deleteQuestion(@RequestBody HashMap<String, Object> param) throws Exception{
		int u_id = -1;
		if (param.get("u_id") != null)
			u_id = (int) param.get("u_id");
		else return false;
		
		int q_id = -1;
		if (param.get("q_id") != null)
			q_id = (int) param.get("q_id");
		else return false;
		
		FileHandler fh = new FileHandler();
		
		etcMapper.deleteQuestion(u_id, q_id);
		return fh.deleteFile(ImageClassification.QUESTION, Integer.toString(u_id), Integer.toString(q_id));
	}
	
	/** /image/question/{path}
	 * 문의 사진 조회
	 * @return 				main notice list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/image/question/{path1}/{path2}", method=RequestMethod.GET)
	@ResponseBody()
	public ResponseEntity<byte[]> getQuestionImages(@PathVariable String path1, @PathVariable String path2) throws Exception{
		String filePath = new File("").getAbsolutePath() + File.separator + File.separator + "images" + File.separator + ImageClassification.getName(ImageClassification.QUESTION) + File.separator + path1 + File.separator + path2;
		File file = new File(filePath);
		
		System.out.println(filePath);
		InputStream imageStream = new FileInputStream(file.getAbsolutePath());
		byte[] imageByteArray = imageStream.readAllBytes();
		imageStream.close();
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
	
	/** /image/review/{path}
	 * 리뷰 사진 조회
	 * @return 				main notice list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/image/review/{path1}/{path2}", method=RequestMethod.GET)
	@ResponseBody()
	public ResponseEntity<byte[]> getReviewImages(@PathVariable String path1, @PathVariable String path2) throws Exception{
		String filePath = new File("").getAbsolutePath() + File.separator + File.separator + "images" + File.separator + ImageClassification.getName(ImageClassification.REVIEW) + File.separator + path1 + File.separator + path2;
		File file = new File(filePath);
		
		System.out.println(filePath);
		InputStream imageStream = new FileInputStream(file.getAbsolutePath());
		byte[] imageByteArray = imageStream.readAllBytes();
		imageStream.close();
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
	
	/** /image/review/{path}
	 * 리뷰 사진 조회
	 * @return 				main notice list
	 * @author 	이동혁
	 * */
	@RequestMapping(value="/video/recipe/{path1}/{path2}", method=RequestMethod.GET)
	@ResponseBody()
	public ResponseEntity<byte[]> getRecipeVideo(@PathVariable String path1, @PathVariable String path2) throws Exception{
		String filePath = new File("").getAbsolutePath() + File.separator + File.separator + "video" + File.separator + path1 + File.separator + path2;
		File file = new File(filePath);
		
		System.out.println(filePath);
		InputStream imageStream = new FileInputStream(file.getAbsolutePath());
		byte[] imageByteArray = imageStream.readAllBytes();
		imageStream.close();
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
	
	@RequestMapping(value="/video/post", method=RequestMethod.POST)		//
	public boolean videoCreateTest(
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("r_id") Integer r_id) throws Exception{
		
		FileHandler fh = new FileHandler();

		List<String> paths = fh.uploadRecipeVideoFiles(files, Integer.toString(r_id));
		String pathList = "";
		
		for(String path: paths) {
			pathList += path;
			pathList += "&";
		}
		
		System.out.println(pathList);
		
		return true;
	}
	@RequestMapping(value="/video/{path}", method=RequestMethod.POST)		//
	public ResponseEntity<byte[]> videoReadTest(@PathVariable String path1, @PathVariable String path2) throws Exception {
		String filePath = new File("").getAbsolutePath() + File.separator + File.separator + "video" + File.separator + path1 + File.separator + path2;
		File file = new File(filePath);
		
		System.out.println(filePath);
		InputStream imageStream = new FileInputStream(file.getAbsolutePath());
		byte[] imageByteArray = imageStream.readAllBytes();
		imageStream.close();
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);				// View의 폴더, 파일 이름을 문자열로 리턴해준다.
	}
}
