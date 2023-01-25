package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;

	// selectLikeCountByPostIdOrUserId를 동적 쿼리로서 공용으로 사용
	public void likeToggle(int postId, int userId) {
		// 좋아요 있는지 확인
		if (likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 있으면 제거
			likeDAO.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가
			likeDAO.insertLike(postId, userId);
		}
	}
	
	public boolean existLike(int postId, Integer userId) { // 비로그인이어도 볼 수 있게 Integer
		if(userId == null) { // 비로그인
			return false;
		}
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0 ? true : false; // 로그인
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId,null);
	}
}
