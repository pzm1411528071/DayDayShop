package web.action.privilege;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import domain.Cart;
import domain.CartItem;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import service.ProductService;
import utils.CommonsUtils;
import utils.PaymentUtil;
public class ProductAction implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
    public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		this.session=request.getSession();
	}
    public void setServletResponse(HttpServletResponse response) {
    	this.response=response;
	}
    // 获得我的订单
 	public String productMyOrders() throws Exception {
 		User user = (User) session.getAttribute("user");
 		if(user==null){
			System.out.println("用户未登录,不能进入权限页面!!");
			response.sendRedirect("login.jsp");//重定向不加/
			return null;
		}
 		ProductService service = new ProductService();
 		List<Order> orderList = service.findAllOrders(user.getUid());//查询该用户的所有的订单信息(单表查询orders表)
 		if (orderList != null) {//循环所有的订单 为每个订单填充订单项集合信息
 			for (Order order : orderList) {
 				//获得每一个订单的oid
 				String oid = order.getOid();
 				//查询该订单的所有的订单项---mapList封装的是多个订单项和该订单项中的商品的信息
 				List<Map<String, Object>> mapList = service.findAllOrderItemByOid(oid);
 				//将mapList转换成List<OrderItem> orderItems
 				for (Map<String, Object> map : mapList) {
 					try {//这里不用映射封装,因为数据库不区分大小写,而OrderItem的属性subTotal有大写,映射不进去
 						//设置查询之后的数据,没有的属性默认为空,因为是传入前台,不传入数据库
 						OrderItem item = new OrderItem();
 						item.setCount((int)map.get("count"));
 						item.setSubTotal((double)map.get("subtotal"));
 						Product product = new Product();
 						product.setPimage((String)map.get("pimage"));
 						product.setPname((String)map.get("pname"));
 						product.setShop_price((double)map.get("shop_price"));
 						item.setProduct(product);//将product封装到OrderItem
 						order.getOrderItems().add(item);//将orderitem封装到order中的orderItemList中
 					} catch (Exception e) {
 						e.printStackTrace();
 					}
 				}
 			}
 		}
 		//重新封装订单的原因是无法直接从数据库获得没有对应"我的订单"页面字段信息
        request.setAttribute("orderList", orderList);
 		return "success";
 	}
    // 确认订单---更新收获人信息+在线支付
 	public void productConfirmOrder() throws Exception {
 		// 1、更新收货人信息
 		Map<String, String[]> properties = request.getParameterMap();// 通过name把请求参数封装到Map<String,String[]>中
 		Order order = new Order();
 		BeanUtils.populate(order, properties);// 映射封装
 		ProductService service = new ProductService();
 		service.updateOrderAdrr(order);
 		// 2、在线支付
 		// 只接入第三方支付平台易宝支付
 		// 获得 支付必须基本数据
 		String orderid = request.getParameter("oid");
 		String money = "0.01";// String money = order.getTotal()+"";为了测试支付0.01
 		// 银行
 		String pd_FrpId = request.getParameter("pd_FrpId");
 		// 发给支付公司需要哪些数据
 		String p0_Cmd = "Buy";
 		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
 		String p2_Order = orderid;
 		String p3_Amt = money;
 		String p4_Cur = "CNY";
 		String p5_Pid = "";
 		String p6_Pcat = "";
 		String p7_Pdesc = "";
 		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
 		// 第三方支付可以访问网址
 		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
 		String p9_SAF = "";
 		String pa_MP = "";
 		String pr_NeedResponse = "1";
 		// 加密hmac 需要密钥
 		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
 		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
 				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

 		String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId=" + pd_FrpId + "&p0_Cmd=" + p0_Cmd
 				+ "&p1_MerId=" + p1_MerId + "&p2_Order=" + p2_Order + "&p3_Amt=" + p3_Amt + "&p4_Cur=" + p4_Cur
 				+ "&p5_Pid=" + p5_Pid + "&p6_Pcat=" + p6_Pcat + "&p7_Pdesc=" + p7_Pdesc + "&p8_Url=" + p8_Url
 				+ "&p9_SAF=" + p9_SAF + "&pa_MP=" + pa_MP + "&pr_NeedResponse=" + pr_NeedResponse + "&hmac=" + hmac;
 		response.sendRedirect(url);// 重定向到第三方支付平台
 	}
 	//提交订单
	public String productSubmitOrder() throws Exception {
		User user = (User) session.getAttribute("user");
		if(user==null){
			System.out.println("用户未登录,不能进入权限页面!!");
			response.sendRedirect("login.jsp");//重定向不加/
			return null;
		}
		Order order = new Order();//封装一个Order对象 传递给service层
		String oid = CommonsUtils.getUUID();//生成一个订单的订单号
		order.setOid(oid);
		order.setOrdertime(new Date());//private Date ordertime;下单时间
		Cart cart = (Cart) session.getAttribute("cart");//获得session中的购物车
		double total = cart.getTotal();//获得订单的总金额
		order.setTotal(total);
		order.setAddress(request.getParameter("address"));//收货地址
		order.setName(request.getParameter("name"));//收货人
		order.setTelephone(request.getParameter("telephone"));//收货人电话
		order.setState(0);//订单支付状态 1代表已付款 0代表未付款
		order.setUser(user);//订单属于哪个用户
		Map<String, CartItem> cartItems = cart.getCartItems();//获得购物车中的购物车订单项的集合map
		for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){//取出每一个购物车订单项
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();//创建新的订单项
			orderItem.setItemid(CommonsUtils.getUUID());//生成订单项的id
			orderItem.setCount(cartItem.getBuyNum());//订单项内商品的购买数量
			orderItem.setSubTotal(cartItem.getSubTotal());//订单项小计
			orderItem.setProduct(cartItem.getProduct());//订单项内部的商品
			orderItem.setOrder(order);//订单项内部的商品
			order.getOrderItems().add(orderItem);//将订单项添加到订单的订单项集合中
		}
		ProductService service = new ProductService();
		service.submitOrder(order);//order对象封装完毕,传递数据到service层
		session.setAttribute("order", order);
		return "success";
	}
}
