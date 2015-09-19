package com.qijiabin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qijiabin.biz.UserBiz;
import com.qijiabin.entity.Userinfo;

@SuppressWarnings("serial")
@Controller("userAction")
@Results(
		{
			@Result(name="reg_ok",location="doList.action",type="redirect"),
			@Result(name="failure",location="/error.jsp"),
			@Result(name="list_ok",location="/list.jsp"),
			@Result(name="delete_ok",location="doList.action",type="redirect"),
			@Result(name="login_ok",location="doList.action",type="redirect"),
			@Result(name="update_ok",location="doList.action",type="redirect"),
			@Result(name="prepare_ok",location="/update.jsp")
		}
)
public class UserAction extends ActionSupport implements ModelDriven<Userinfo>{
	private static Logger log = Logger.getLogger(UserAction.class);  
	private Userinfo user = new Userinfo();
	
	private UserBiz userBiz;
	
	public Userinfo getUser() {
		return user;
	}
	public void setUser(Userinfo user) {
		this.user = user;
	}
	public UserBiz getUserBiz() {
		return userBiz;
	}
	@Resource(name="userBiz")
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	
	
	@Action("/user/doAdd")
	public String doAdd(){
		log.info("doAdd-->"+user.getUname()+","+user.getUpass());
		userBiz.add(user);
		return "reg_ok";
	}
	
	@Action("/user/doList")
	public String doList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int pageSize = 3;//∑÷“≥µ•Œª
		String pageIdStr = request.getParameter("pageId");
		int pageId = 1;
		try{
			pageId = Integer.parseInt(pageIdStr);
		}catch (NumberFormatException e) {
			pageId = 1;
		}
		if (pageId < 1) {
			pageId = 1;
		}
		int pageCount = (int) userBiz.getTotalPages(pageSize);
		if (pageId > pageCount) {
			pageId = pageCount;
		}
		request.setAttribute("pageId", pageId);
		request.setAttribute("pageCount", pageCount );
		List<Userinfo> list = userBiz.getAll(pageId,pageSize);
		request.setAttribute("list", list);
		return "list_ok";
	}
	
	@Action("/user/doDelete")
	public String doDelete(){
		userBiz.delete(user);
		return "delete_ok";
	}
	
	@Action("/user/doLogin")
	public String doLogin(){
		Userinfo u = null;
		u = userBiz.login(user);
		if(u!=null)
			return "login_ok";
		return "failure";
	}
	
	@Action("/user/doUpdate")
	public String doUpdate(){
		userBiz.update(user);
		return "update_ok";
	}
	
	@Action("/user/doPrepare")
	public String doPrepare(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("user", user);
		return "prepare_ok";
	}
	
	public Userinfo getModel() {
		return this.user;
	}

}
