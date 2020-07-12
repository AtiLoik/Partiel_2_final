package com.ensup.master.serviceImpl;

import com.ensup.master.dao.IUserDao;
import com.ensup.master.daoImpl.UserDao;
import com.ensup.master.metier.User;

/**
 * CourseService
 * 
 * @author DANON
 *
 */
public class UserService {

	IUserDao idao;
	UserDao dao;
	
	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public UserService() {
		super();
		dao = new UserDao();
	}

	/**
	 * get an user
	 * @param login
	 * @param password
	 * @return 
	 */
	public User getUser(String login, String password) {
		return dao.getUser(login, password);
	}

}
