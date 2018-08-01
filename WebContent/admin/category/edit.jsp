<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</head>
<body>
	<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminUpdateCategory" method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26">
					<strong><strong>编辑分类</strong>
					</strong>
				</td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					原分类名称：
				</td>
				<td class="ta_01" bgColor="#ffffff">
				<!--  通过href带参数传递然后从param中获得数据 -->
					<input type="text" name="cname" value="${param.cname}"/>
				</td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					修改为：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="newcname" id="newcname"/>
				</td>
			</tr>
		
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
						&#30830;&#23450;
					</button>

					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>