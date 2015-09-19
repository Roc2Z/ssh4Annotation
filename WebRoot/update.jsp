<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
   
   <form action="user/doUpdate.action" method="post">
   		id:<input type="text" name="uid" value="${user.uid }" readonly="readonly"><br/>
   		name:<input type="text" name="uname"><br/>
   		pass:<input type="text" name="upass"><br/>
   		<input type="submit" value="确定">
   </form>
   
  </body>
</html>
