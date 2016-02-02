<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>认证码验证页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0"> 
</head>

<body>
<% 
 String rand = (String)session.getAttribute("rand");
 String input = request.getParameter("rand");
%>
系统产生的认证码为： <%= rand %><br>
您输入的认证码为： <%= input %><br>
<br>
<%
  if (rand.equals(input)) {
%>
<font color=green>输入相同，认证成功！</font>
<%
  } else {
%>
<font color=red>输入不同，认证失败！</font>
<%
  }
  
  session.removeAttribute("rand");
%>
</body>
</html>