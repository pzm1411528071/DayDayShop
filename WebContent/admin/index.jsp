<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>天天商城管理系统--登录界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
body {
  color: white;
}
</style>
</head>
<body style="background: #278296">
	<form method="post" action="${pageContext.request.contextPath }/login" target="_parent" name='theForm' onsubmit="return validate()">
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
		    <tr>
		        <td style="padding-left: 50px">
		            <table>
		                <tr>
		                    <td>管理员账号：</td>
		                    <td><input type="text" name="ausername" /></td>
		                </tr>
		                <tr>
		                    <td>管理员密码：</td>
		                    <td><input type="password" name="apassword" /></td>
		                </tr>
		                <tr>
		                    <td>&nbsp;</td>
		                    <td><input type="submit" value="进入管理中心" class="button" /></td>
		                </tr>
		                <tr>
		                    <td colspan="2">${info}</td>
		                </tr>
		            </table>
		        </td>
		    </tr>
		</table>
	</form>
</body>
</html>