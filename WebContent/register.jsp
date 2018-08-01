<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<!-- 引入JS框架的核心文件,jQuery在BootStrap前面,BootStrap的编码是建立在jQuery的基础上 -->
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义JS文件 -->
<script src="js/register1.js" type="text/javascript"></script>
<script src="js/register2.js" type="text/javascript"></script>
<!-- 引入BooStrap核心样式文件 -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<!-- 引入自定义样式文件 -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/register.css" type="text/css" />
</head>
<script type="text/javascript">
$(function() {//AJAX判断用户是否存在
	$("#username").blur(function() {
		var usernameInput = $(this).val();
		$.post(
			"${pageContext.request.contextPath}/checkUsername",
			{ "username" : usernameInput }, //输入的用户名
			function(data) {//回调函数,data响应参数对象
			var isExist = data.isExist;
			var usernameInfo = "";
			if (isExist) {
				usernameInfo = "该用户名已经存在";
				$("#usernameInfo").css("color", "red");
			} else {
				usernameInfo = "该用户可以使用";
				$("#usernameInfo").css("color", "green");
			}
			$("#usernameInfo").html(usernameInfo);//html相当于innerHTML
		    }, 
		    "json"
	    );
	});
});
</script>
<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>
	
	<!-- container是bootstrap.min.css定义好的容器 -->
	<div class="container" style="width: 98%; background: url('images/regist_bg.jpg');">
	
	    <!-- row,col-sm-,col-md-*(sm,md是不同设备屏幕大小,在每个row下md或sm后面数字相加等于12)是bootstrap.min.css定义好的栅格系统 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;" method="post" id="form1">
				
				    <!-- form-group是bootstrap.min.css定义好的单独表单控件,不同控件的样式设计参照帮助文档 和源码bootstrap.css-->
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名" value="${param.username}">
						</div>
						<div class="col-sm-4">
						    <span id="usernameInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="请输入密码" value="${param.password}">
						</div>
						<div class="col-sm-4">
						    <span id="passwordInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd" name="confirmpwd"
								placeholder="请输入确认密码" value="${param.confirmpwd}">
						</div>
						<div class="col-sm-4">
						    <span id="confirmpwdInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="请输入邮箱" value="${param.email}">
						</div>
						<div class="col-sm-4">
						    <span id="emailInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="请输入姓名" value="${param.name}">
						</div>
						<div class="col-sm-4">
						    <span id="nameInfo"></span>
						</div>
					</div>
					
					<div class="form-group opt">
						<label for="province" class="col-sm-2 control-label">籍贯</label>
						<div class="col-sm-2">
							<select class="form-control" id="province" name="province">
                                <option>--请选择--</option>
									<option value="北京">北京</option><option value="天津">天津</option><option value="上海">上海 </option>
									<option value="重庆">重庆</option><option value="河北">河北</option><option value="山西">山西</option>
									<option value="辽宁">辽宁</option><option value="吉林">吉林</option><option value="黑龙江">黑龙江</option>
									<option value="江苏">江苏</option><option value="浙江">浙江</option><option value="安徽">安徽</option>
									<option value="福建">福建</option><option value="江西">江西</option><option value="山东">山东</option>
									<option value="河南">河南</option><option value="湖北">湖北</option><option value="湖南">湖南</option>
									<option value="广东">广东</option><option value="海南">海南</option><option value="四川">四川</option>
									<option value="贵州">贵州</option><option value="云南">云南</option><option value="陕西">陕西</option>
				                    <option value="甘肃">甘肃</option><option value="青海">青海</option><option value="台湾">台湾</option>
									<option value="内蒙古">内蒙古</option><option value="广西">广西</option><option value="西藏">西藏</option>
									<option value="宁夏">宁夏</option><option value="新疆">新疆</option><option value="香港">香港</option>
									<option value="澳门">澳门</option>
                            </select>
						</div>
						<div class="col-sm-2">
							<select class="form-control" id="city" name="city"></select>
						</div>
						<div class="col-sm-4">
						    <span id="provinceInfo"></span>
						</div>
					</div>
					
					<div class="form-group opt">
						<label for="sex" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" value="男">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" value="女" >
								女
							</label>
						</div>
						<div class="col-sm-4">
						    <span id="sexInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="birthday" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" id="birthday" name="birthday" value="${param.birthday}">
						</div>
						<div class="col-sm-4">
						    <span id="birthdayInfo"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="telephone" class="col-sm-2 control-label">电话</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="telephone" name="telephone"
							placeholder="请输入电话号码" value="${param.telephone}">
						</div>
						<div class="col-sm-4">
						    <span id="telephoneInfo"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="checkcode" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="checkcode" name="checkcode">
						</div>
						<div class="col-sm-1">
						 <img src="userCheckCode"/> 
						</div>
						<div class="col-sm-2">
						 <input type="submit" value="换一张" onclick="form1.action='userRegisterChangeCheckCode'">
						</div>
						<div class="col-sm-3">
						 <span id="checkcodeInfo" style="color:red">${checkcodeInfo}</span>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit" onclick="form1.action='userRegister'"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
								<span id="registerInfo" style="color:red">${registerInfo}</span>
						</div>
					</div>
					
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	
	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>




