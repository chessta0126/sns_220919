package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;


@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public void addUser(String loginId,String hashedPassword,String name,String email) {
		userDAO.insertUser(loginId, hashedPassword, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId,String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}

	public User getUserById(int userId) {
		return userDAO.selectUserById(userId);
	}
}
