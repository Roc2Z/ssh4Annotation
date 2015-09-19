<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>

  </head>
  
  <body>
   
      <center>
   	<h1>用户信息</h1>
   		<table bordercolor="green" border="1">
   			<tr>
   			  <td>用户编号</td><td>用户姓名</td><td>用户密码</td><td>删除</td><td>修改</td>
   			</tr>
   			<c:forEach items="${list}" var="user">
   				<tr>
   					<td>${user.uid }</td>
   					<td>${user.uname }</td>
   					<td>${user.upass }</td>
   					<td><input type="button" value="删除" onclick="location.href='user/doDelete.action?uid=${user.uid}'"/></td>
   					<td><input type="button" value="修改" onclick="location.href='user/doPrepare.action?uid=${user.uid}&uname=${user.uname }&upass=${user.upass}'"/></td>
   				</tr>
   			</c:forEach>
   			
   			<tr>
			<td colspan="5" align="center"><a href="user/doList.action?pageId=1">首页</a>&nbsp;&nbsp;
			<a href="user/doList.action?pageId=${pageId-1 }">上一页</a>&nbsp;&nbsp;
			${pageId }/${pageCount }&nbsp;&nbsp;
			<a href="user/doList.action?pageId=${pageId+1 }">下一页</a>&nbsp;&nbsp;
			<a href="user/doList.action?pageId=${pageCount }">尾页</a></td>
			</tr>
			 
   		</table>
   </center>
   
  </body>
</html>
