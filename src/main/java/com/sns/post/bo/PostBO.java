package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;

	@Autowired
	private LikeBO likeBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	public int addPost(int userId, String userLoginId, String content, MultipartFile file){
				
		// 파일 업로드 => 경로
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
				
		// DAO insert
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
	
	public void deletePostByPostIdUserId(int postId,int userId) {
		// 기존 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.error("[delete post] postId:{}, userId:{}", postId, userId);
			return;
		}
		
		// 이미지 있으면 이미지 삭제
		fileManager.deleteFile(post.getImagePath());
		
		// 글 삭제
		postDAO.deletePostByPostIdUserId(postId, userId);
		
		// 댓글 삭제
		commentBO.deleteCommentsByPostId(postId);
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
	}
}
