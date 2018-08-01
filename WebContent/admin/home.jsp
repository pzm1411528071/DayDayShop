<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{
	SCROLLBAR-ARROW-COLOR: #ffffff;  
	SCROLLBAR-BASE-COLOR: #dee3f7;
}
</style>
</head>
<frameset rows="130,*,43" frameborder="0px" border="0px" framespacing="0px">
    <frame src="${pageContext.request.contextPath}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
    <frameset cols="159,*" frameborder="0px" border="0px" framespacing="0px">
		<frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="mainFrame">
    </frameset>
    <frame src="${pageContext.request.contextPath}/admin/bottom.jsp" name="bottomFrame" scrolling="NO"  noresize>
</frameset>
</html>
