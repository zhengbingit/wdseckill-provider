package com.wd.service.user;

import com.wd.dao.user.IUserDao;
import com.wd.entity.User;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public boolean addUserService(User user) {
		userDao.addUser(user);
		return true;
	}

	public boolean loginService(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
