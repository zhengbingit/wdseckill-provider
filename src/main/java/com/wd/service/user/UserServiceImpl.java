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
		return userDao.addUser(user) == true ? true : false;
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

	public boolean editUserMoneyService(int u_id, double u_money) {
		return userDao.updateMoney(u_id, u_money);
	}

	public double getUserMoneyService(int u_id) {
		return userDao.getMoney(u_id);
	}


}
