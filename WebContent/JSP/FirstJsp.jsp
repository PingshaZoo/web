
<%@page import="util.NetUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
Locale locale = request.getLocale();
Calendar calendar = Calendar.getInstance(locale);
int hour = calendar.get(Calendar.HOUR_OF_DAY);
String localIp = NetUtil.getLocalIp();

%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
<HTML>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<head><title> Hotel California </title></head>
<body>
 <b>Welcome to Hotel California</b><br/>
<img style="-webkit-user-select: none" src="http://<%=localIp%>:8080/web/Image"><br/>
 <a href = "http://<%=localIp%>:8080/web/Welcome">Welcome</a><br/>
 <a href = "http://<%=localIp%>:8080/web/FirstServlet">FirstServlet</a><br/>
 <a href = "http://<%=localIp%>:8080/web/Image">Image</a><br/>
 <a href = "http://<%=localIp%>:8080/web/ForwardReq">ForwardReq</a><br/>
 <a href = "http://<%=localIp%>:8080/web/UploadFile">UploadFile</a><br/>
 <a href = "http://<%=localIp%>:8080/web/DownloadFile">DownloadFile</a><br/>
 <a href = "http://<%=localIp%>:8080/web/FileBrowser">FileBrowser</a><br/>
 <a href = "http://<%=localIp%>:8080/web/RedirectReq">RedirectReq</a><br/>
 <a href = "http://<%=localIp%>:8080/web/RefreshWeb">RefreshWeb</a><br/>
</body>
</html>
