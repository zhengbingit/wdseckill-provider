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

	public User loginService(User user) {
		User u = userDao.getUser(user.getU_name());
		if(u != null){
			if(u.getU_pwd().equals(user.getU_pwd())) {
				return u;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}


}
