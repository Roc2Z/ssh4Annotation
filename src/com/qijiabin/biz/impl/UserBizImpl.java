package com.qijiabin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qijiabin.biz.UserBiz;
import com.qijiabin.dao.UserDao;
import com.qijiabin.entity.Userinfo;

@Service("userBiz")
@Transactional
public class UserBizImpl implements UserBiz {
	private UserDao userDao;
	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Transactional
	public void add(Userinfo u) {
		userDao.add(u);
	}

	@Transactional
	public List<Userinfo> getAll() {
		return userDao.getAll();
	}

	@Transactional
	public void delete(Userinfo user) {
		userDao.delete(user);
	}

	@Transactional
	public Userinfo login(Userinfo user) {
		return userDao.login(user);
	}

	@Transactional
	public int update(Userinfo user) {
		return userDao.update(user);
	}

	@Transactional
	public List<Userinfo> getAll(int curPage, int pageSize) {
		return userDao.getAll(curPage, pageSize);
	}

	@Transactional
	public long getTotalPages(int pageSize) {
		return userDao.getTotalPages(pageSize);
	}

}
