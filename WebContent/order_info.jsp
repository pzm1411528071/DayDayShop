<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单支付页面</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/order_info.css" type="text/css" />
<script type="text/javascript">
	function confirmOrder(){
		//提交表单
		$("#orderForm").submit();
	}
</script>
</head>
<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${order.oid }</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<!-- 遍历订单项 -->
						<c:forEach items="${order.orderItems }" var="orderItem">
						
							<tr class="active">
								<td width="60" width="40%">
									<img src="${pageContext.request.contextPath }/${orderItem.product.pimage}" width="70" height="60">
								</td>
								<td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
								<td width="20%">￥${orderItem.product.shop_price}</td>
								<td width="10%">${orderItem.count}</td>
								<td width="15%"><span class="subtotal">￥${orderItem.subTotal }</span></td>
							</tr>
						
						</c:forEach>

					</tbody>
				</table>
			</div>
			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${order.total }元</strong>
			</div>
		</div>

		<div>
			<hr />
			<form id="orderForm" class="form-horizontal" action="${pageContext.request.contextPath }/productConfirmOrder" method="post"
				style="margin-top: 5px; margin-left: 150px;">
				<!-- 因为post提交请求头不能传数据,订单oid通过表单提交,设置成隐藏 -->
				<input type="hidden" name="oid" value="${order.oid}">
				
				<div class="form-group">
					<label for="address" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="address" name="address"
							placeholder="请输入收货地址">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="name" name="name"
							value="${user.username}">
					</div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-1 control-label">电话</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="telephone" name="telephone"
							value="${user.telephone}">
					</div>
				</div>
			    <hr />
				<div style="margin-top: 5px; margin-left: 150px;">
					<strong>选择银行：</strong>
					<p>
						<br /> 
						<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked" />
						工商银行 <img src="images/bank/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />
						中国银行 <img src="images/bank/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />
						农业银行 <img src="images/bank/abc.bmp" align="middle" /> <br /> <br /> 
						<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />
						交通银行 <img src="images/bank/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />
						平安银行<img src="images/bank/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />
						建设银行 <img src="images/bank/ccb.bmp" align="middle" /> <br /> <br /> 
						<input type="radio" name="pd_FrpId" value="CEB-NET-B2C" />
						光大银行 <img src="images/bank/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />
						招商银行 <img src="images/bank/cmb.bmp" align="middle" />
					</p>
					<hr />
					<p style="text-align: right; margin-right: 100px;">
						<a  href="javascript:;" onclick="confirmOrder()">
							<img src="images/finalbutton.gif" width="204" height="51"
							border="0" />
						</a>
					</p>
					<hr />
				</div>
			</form>
		</div>

	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>