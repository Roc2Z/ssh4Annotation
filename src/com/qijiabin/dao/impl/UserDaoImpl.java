package com.qijiabin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.qijiabin.dao.UserDao;
import com.qijiabin.entity.Userinfo;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void add(Userinfo u) {
		this.getSessionFactory().getCurrentSession().save(u);
	}

	@SuppressWarnings("unchecked")
	public List<Userinfo> getAll() {
		String hql = "from Userinfo as u";
		return (List<Userinfo>) this.getSessionFactory().openSession().createQuery(hql);
	}

	public void delete(Userinfo user) {
		this.getSessionFactory().getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public Userinfo login(Userinfo u) {
		String hql="from Userinfo u where u.uname="+u.getUname()+" and u.upass="+u.getUpass();
		List<Userinfo> list = (List<Userinfo>) this.getSessionFactory().getCurrentSession().createQuery(hql);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	public int update(Userinfo user) {
		int count=0;
		try {
			this.getSessionFactory().getCurrentSession().update(user);
			count=1;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Userinfo> getAll(final int curPage, final int pageSize) {
		final String hql="from Userinfo";
		Session session =  this.getSessionFactory().getCurrentSession();
        Query query =  session.createQuery(hql);
        query.setFirstResult((curPage-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.list();
	}

	@SuppressWarnings("rawtypes")
	public long getTotalPages(int pageSize) {
		String hql="select count(u) from Userinfo u";
		List list = (List) this.getSessionFactory().getCurrentSession().createQuery(hql).list();
		Long count = (Long)list.get(0);//获取总记录数
		long totalPages = count%pageSize==0?count/pageSize:count/pageSize+1;
		return totalPages;
	}

}
