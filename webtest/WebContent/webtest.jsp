<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.*, java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="com.oreilly.servlet.MultipartRequest" import="java.util.*" import="java.io.*"%> 
<% String savePath = "C:/Users/sayit/Desktop/ah"; // 저장할 디렉토리 (절대경로) 
int sizeLimit = 10 * 1024 * 1024; // 5메가까지 제한 넘어서면 예외발생 
try
 {
	 MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit);
// multi.getFile("file1");
// multi.getFile("file2");
// multi.getFile("file3");
// 	 System.out.println("request getContentType : " + request.getContentType());


} catch (Exception e) { 
out.print(e); 
out.print("예외 상황 발생..! "); 
} 
%>
<title>Insert title here</title>
</head>
<body>
</body>
</html>