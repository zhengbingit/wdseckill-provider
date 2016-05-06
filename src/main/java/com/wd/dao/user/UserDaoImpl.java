package com.wd.dao.user;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.wd.entity.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao,Serializable {

	private static final long serialVersionUID = 2521157222410631449L;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("载入SqlSessionFactory");
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	public boolean addUser(User user) {
		super.getSqlSession().insert("com.wd.dao.user.mapper.addUser", user);
		return true;
	}

	public User getUser(String u_name) {
		return super.getSqlSession().selectOne("com.wd.dao.user.mapper.getUser", u_name);
	}

	public boolean isSell(int u_id) {
		System.out.println("Cache: isSell");
		return false;
	}

	public String getStore(int u_id) {
		System.out.println("Cache: getStore");
		return null;
	}

	public double getMoney(int u_id) {
		System.out.println("Cache: getMoney");
		return 0;
	}

	public boolean updateMoney(int u_id, double money) {
		User u = new User();
		u.setU_id(u_id);
		u.setU_money(money);
		super.getSqlSession().update("com.wd.dao.user.mapper.updateUser", u);
		return false;
	}

}
