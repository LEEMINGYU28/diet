package com.diets.user.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diets.user.dao.UserDAO;
import com.diets.user.domain.User;


@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	public void add(User user) {
		user.setPassword(cryptoPassword(user.getPassword()));
		userDao.add(user);
	}
	private String cryptoPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] sha256Hash = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : sha256Hash) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public User login(User user) {
		User tempUser = userDao.get(user.getUserId());

		if (tempUser != null && tempUser.getPassword().equals(cryptoPassword(user.getPassword()))) {
			return tempUser;
		}
		return null;
	}
}
