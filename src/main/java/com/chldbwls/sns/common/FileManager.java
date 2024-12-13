package com.chldbwls.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "C:\\springProject\\upload\\SNS";
	
	public static String saveFile(int userId, MultipartFile file) {
	
		// 파일 이름
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			// bytes가 실제 파일 경로
			// java.nyo?import
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			// 파일 저장 실패
			return null;
		}
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}

}
