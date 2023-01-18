package com.sns.post.bo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public boolean addPost(
			String userName, String content
			, MultipartFile file
			){
				
		// 파일 업로드 => 경로
		String imagePath = null;
			if(file != null) {
				imagePath = fileManagerService.saveFile(userName, file);
			}
				
		// DAO insert
		return postDAO.insertPost(userName, content, imagePath);
	}
}
