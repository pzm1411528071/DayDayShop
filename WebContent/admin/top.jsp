<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
<!-- 在jsp页面table写成外部引用样式不起作用,可改成操作div -->
</head>
<body>
	<table class="table1" height="88px" width="100%"  border="0px" cellspacing="0px" cellpadding="0px">
		<tr>
			<td width="100%" height="100px" background="${pageContext.request.contextPath}/images/top_100.jpg">
			</td>
		</tr>
	</table>
	<table width="100%"  border="0px" cellspacing="0px" cellpadding="0px">
		<tr>
			<td height="30px" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
				<table width="100%"  border="0px" cellspacing="0px" cellpadding="0px">
				    <tr>
						<td width="85%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td width="15%">
							<table width="100%"  border="0px" cellspacing="0px" cellpadding="0px">
					            <tr>
									<td width="16px"
										background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/images/mis_05a.jpg" width="6" height="18">
									</td>
									<td width="155px" valign="bottom" background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										管理员：<font color="blue">${admin.ausername}</font>
									</td>
									<td width="10px" align="right" background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/images/mis_05c.jpg" width="6" height="18">
									</td>
								</tr>
							</table>
						</td>
						<td align="right" width="5%">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
