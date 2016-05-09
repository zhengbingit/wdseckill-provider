package com.wd.service.user;

import com.wd.entity.User;
/**
 * 用户Service
 * @author zhengbinMac
 *
 */
public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean addUserService(User user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User loginService(User user);

	/**
	 * 根据u_id减少用户余额
	 * @param u_id
	 * @param u_money
	 * @return
	 */
	public boolean editUserMoneyService(int u_id, double u_money);

	public double getUserMoneyService(int u_id);
}
