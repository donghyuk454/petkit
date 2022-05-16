package com.petkit.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.petkit.classification.ImageClassification;

@Component
public class FileHandler {
	
	public List<String> uploadImageFiles(List<MultipartFile> files, ImageClassification category, String u_id, String id) throws IllegalStateException, IOException{
		List<String> fileList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(files)) {			
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = "images" + File.separator + ImageClassification.getName(category) + File.separator + u_id + "-" + id;
            File file = new File(path);
            
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
  
	            // 디렉터리 생성에 실패했을 경우
	            if(!wasSuccessful)
	                System.out.println("file: was not successful");
            }

            // 다중 파일 처리
            for(MultipartFile file_ : files) {
                // 기존 파일명 사용
                String file_name = file_.getOriginalFilename();
                System.out.println(file_name);
  
                // 생성 후 리스트에 추가
                fileList.add(file_name);
                // 업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + path + File.separator + file_name);
                file_.transferTo(file);
                
                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }
		}
		
		return fileList;
	}
	
	public boolean deleteFile(ImageClassification category, String u_id, String id) {
		String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
		String path = "images" + File.separator + ImageClassification.getName(category) + File.separator + u_id + "_" + id;
		
        File folder = new File(absolutePath+path);
        System.out.println(absolutePath+path);
        boolean wasSuccessful = false;

        try {
        	if(folder.exists()) {
        		for (File f: folder.listFiles()) {
        			f.delete();
        		}
        		wasSuccessful = folder.delete();
        	}
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println(wasSuccessful);
		return wasSuccessful;
	}
	
	public List<String> uploadRecipeVideoFiles(List<MultipartFile> files, String r_id) throws IllegalStateException, IOException{
		List<String> fileList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(files)) {			
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = "video" + File.separator + r_id;
            File file = new File(path);
            
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
  
	            // 디렉터리 생성에 실패했을 경우
	            if(!wasSuccessful)
	                System.out.println("file: was not successful");
            }
            int idx = 1;
            // 다중 파일 처리
            for(MultipartFile file_ : files) {
                // 기존 파일명 사용
                String file_name = file_.getOriginalFilename();
                System.out.println(file_name);
  
                // 생성 후 리스트에 추가
                fileList.add(file_name);
                // 업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + path + File.separator + file_name);
                file_.transferTo(file);
                
                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
                idx++;
            }
		}
		
		return fileList;
	}
}
