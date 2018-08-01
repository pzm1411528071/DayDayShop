/**
 * 自定义输入提示信息
 */
$(function(){
	var regexPass =/^[A-Za-z]\w{5,}$/;//必须字母开始,长度不小于5+1
	var regexEmail = /^\w+@\w+[.](com||cn|com.cn||org||gov)$/g;//邮箱格式
	var regexTel=/^\d{11}$/;//电话格式
	$("#username").focus(function(){
		var usernameInput = $(this).val();//input中
		var Info;//不能取和usernameInfo同名,js文件usernameInput不用定位可直接获取,会发生数据紊乱
		if(usernameInput==''||usernameInput==null){
			var Info = "用户名不能为空";
			$("#usernameInfo").css("color", "red");
		}else{
			var Info = "";
		}
		$("#usernameInfo").html(usernameInfo);
	});
	
	$("#password").blur(function(){
		var passwordInput = $(this).val();
		var Info;
		if(!regexPass.test(passwordInput)){
			Info = "必须字母开始,长度不小于6";
			$("#passwordInfo").css("color", "red");
		}else {
			Info = "密码符合要求";
			$("#passwordInfo").css("color", "green");
		}
		$("#passwordInfo").html(Info);
	});
	$("#password").focus(function(){
		var passwordInput = $(this).val();
		var Info;
		if(passwordInput==''||passwordInput==null){
			Info = "密码不能为空";
			$("#passwordInfo").css("color", "red");
		}else{
			Info = "";
		}
		$("#passwordInfo").html(Info);
	});
	
	$("#confirmpwd").blur(function(){
		var confirmpwdInput = $(this).val();
		var passwordInput = $("#password").val();
		var Info;
		if(!(confirmpwdInput==passwordInput)){//注意加括号
			Info = "两次密码不一致";
			$("#confirmpwdInfo").css("color", "red");
		}else{
			Info = "密码一致";
			$("#confirmpwdInfo").css("color", "green");
		}
		$("#confirmpwdInfo").html(Info);
	});
	$("#confirmpwd").focus(function(){
		var confirmpwdInput = $(this).val();
		var Info;
		if(confirmpwdInput==''||confirmpwdInput==null){
			Info = "确认密码不能为空";
			$("#confirmpwdInfo").css("color", "red");
		}else{
			Info = "";
		}
		$("#confirmpwdInfo").html(Info);
	});
	
	$("#email").blur(function(){
		var emailInput = $(this).val();
		var Info;
		if(!regexEmail.test(emailInput)){
			Info = "邮箱格式不正确";
			$("#emailInfo").css("color", "red");
		}else{
			Info = "邮箱格式正确";
			$("#emailInfo").css("color", "green");
		}
		$("#emailInfo").html(Info);
	});
	$("#email").focus(function(){
		var emailInput = $(this).val();
		var Info;
		if(emailInfo==''||emailInfo==null){
			emailInfo = "邮箱不能为空";
			$("#emailInfo").css("color", "red");
		}else{
			emailInfo = "";
		}
		$("#emailInfo").html(Info);
	});
	
	$("#name").blur(function(){
		var nameInput = $(this).val();
		var Info;
		if(nameInput==''||nameInput==null){
			nameInfo = "姓名不能为空";
			$("#nameInfo").css("color", "red");
		}else{
			nameInfo = "";
		}
		$("#nameInfo").html(Info);
	});
	
	$("#province").blur(function(){
		var provinceInput = $(this).val();
		var Info;
		if(provinceInput=='--请选择--'){//默认值
			Info = "籍贯未选";
			$("#provinceInfo").css("color", "red");
		}else{
			Info = "";
		}
		$("#provinceInfo").html(Info);
	});
	
	$("#telephone").blur(function(){
		var telephoneInput = $(this).val();
		var Info;
		if(!regexTel.test(telephoneInput)){
			Info = "电话格式不符合要求";
			$("#telephoneInfo").css("color", "red");
		}else {
			telephoneInfo = "电话符合要求";
			$("#telephoneInfo").css("color", "green");
		}
		$("#telephoneInfo").html(Info);
	});
	$("#telephone").focus(function(){
		var telephoneInput = $(this).val();
		var Info;
		if(telephoneInfo==''||telephoneInfo==null){
			Info = "电话不能为空";
			$("#telephone").css("color", "red");
		}else{
			Info = "";
		}
		$("#telephoneInfo").html(Info);
	});
});