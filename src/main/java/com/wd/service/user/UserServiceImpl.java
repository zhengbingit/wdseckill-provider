package com.wd.service.user;

import com.wd.dao.user.IUserDao;
import com.wd.entity.User;
import com.wd.util.MD5Test;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public boolean addUserService(User user) {
		user.setU_pwd(MD5Test.getMD5(user.getU_pwd()));
		return userDao.addUser(user) == true ? true : false;
	}

	@SuppressWarnings("unused")
	public User loginService(User user) {
		User u = userDao.getUser(user.getU_name());
		if(u != null){
			String u_pwd = u.getU_pwd();
			String l_pwd = MD5Test.getMD5(user.getU_pwd());
			if(u_pwd.equals(l_pwd)) {
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
