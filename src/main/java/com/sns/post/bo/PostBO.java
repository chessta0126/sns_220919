package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public int addPost(int userId, String userLoginId, String content, MultipartFile file){
				
		// 파일 업로드 => 경로
		String imagePath = null;
		if(file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
				
		// DAO insert
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	
	public void deletePostByPostIdUserId(int postId,int userId) {
		// 기존 글 가져오기
		Post post = postDAO.selectPostByPostIdUserId(postId, userId);
		
		// 이미지 있으면 이미지 삭제
		if(post.getImagePath() != null) {
			fileManagerService.deleteFile(post.getImagePath());		
		}
		
		// 글 삭제
		postDAO.deletePostByPostIdUserId(postId, userId);
		
		// 댓글 삭제
		
		// 좋아요 삭제
	}
}
