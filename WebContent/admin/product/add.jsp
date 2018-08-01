<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(function(){
	    //页面加载完毕后去异步获得分类数据
		$.post(
			"${pageContext.request.contextPath}/adminFindAllCategory",
			function(data){
			//[{"cid":"xx","cname":"xxx"},{},{}]
			//拼接多个<option value=""></option>放到select中
			var content="";
			for(var i=0;i<data.length;i++){
				content+="<option value='"+data[i].cid+"'>"+data[i].cname+"</option>";
			}
			$("#cid").html(content);
			},
			"json"
		);		
	});
</script>
</head>
<body>
	<!-- 需要用到上传下载,必须post提交,这里不能用隐藏域传递参数,因为上传下载插件里的request发生变化-->
	<s:form name="Form1" action="adminAddProduct" method="post" enctype="multipart/form-data">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26">
					<strong><STRONG>添加商品</STRONG>
					</strong>
				</td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品名称：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="pname" value="" class="bg"/>
				</td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					是否热门：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<select name="is_hot">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					市场价格：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="market_price" value="" class="bg"/>
				</td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商城价格：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="shop_price" value="" class="bg"/>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品图片：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<input type="file" name="file" lable="选择要上传的图片">
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					所属分类：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<select id="cid" name="cid">
						
					</select>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<textarea name="pdesc" rows="5" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
						&#30830;&#23450;
					</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>