package web.action.common;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import domain.Cart;
import domain.CartItem;
import domain.Category;
import domain.PageBean;
import domain.Product;
import redis.clients.jedis.Jedis;
import service.ProductService;
import utils.JedisPoolUtils;
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
	//显示首页的功能
	public String productIndex(){
		ProductService service = new ProductService();
		//准备热门商品
		List<Product> hotProductList = service.findHotProductList();
		//准备最新商品
		List<Product> newProductList = service.findNewProductList();
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);
		return "success";
	}
	//显示商品的类别的的功能
	public void productCategoryList() throws Exception {
        ProductService service = new ProductService();
		Jedis jedis = JedisPoolUtils.getJedis();//获得jedis对象 连接redis数据库
		String categoryListJson = jedis.get("categoryListJson");
		if(categoryListJson==null){//先从缓存中查询categoryListJson 如果有直接使用 没有在从数据库中查询存到缓存中
			System.out.println("缓存没有数据 查询数据库");
			//准备分类数据
			List<Category> categoryList = service.findAllCategory();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);//利用GSON将集合转换成JSON格式
			jedis.set("categoryListJson", categoryListJson);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}
	//根据商品的类别获得商品的列表
	public String productListByCid() {
		String cid = request.getParameter("cid");//获得商品类别id
		String currentPageStr = request.getParameter("currentPage");//获取指定类别的当前页
		if (currentPageStr == null){
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;
		ProductService service = new ProductService();
		PageBean<Product> pageBean = service.findProductListByCid(cid, currentPage, currentCount);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		List<Product> historyProductList = new ArrayList<Product>();// 定义一个记录历史商品信息的集合
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {//获得客户端携带名字叫pids的cookie
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();//pids:3-2-1
					String[] split = pids.split("-");//获得由pid组成的数组
					for (String pid : split) {
						Product pro = service.findProductByPid(pid);
						historyProductList.add(pro);//添加商品为pid的至历史记录的集合中
					}
				}
			}
		}
		request.setAttribute("historyProductList", historyProductList);// 将历史记录的集合放到request中
		return "success";
	}
	//根据关键字获得商品信息的功能(站内搜索)
	public void productSearchWord() throws Exception {
		String word = request.getParameter("word");//获得关键字
		ProductService service = new ProductService();
		List<Object> productList = null;
		try {
			productList = service.findProductByWord(word);//查询该关键字的所有商品
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();//使用JSON的转换工具GSON将对象或集合转成JSON格式的字符串,避免直接传递JSON格式过于麻烦
		String json = gson.toJson(productList);
		System.out.println(json);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	//将商品添加到购物车
	public String productAddProductToCart() {
		ProductService service = new ProductService();
		//获得要放到购物车的商品的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		if(buyNum<0||buyNum>Integer.MAX_VALUE){
			System.out.println("购买的商品数量有误!!!");
			return "error";
		}else{
			//获得product对象
			Product product = service.findProductByPid(pid);
			//计算小计
			double subTotal = product.getShop_price()*buyNum;
			//封装CartItem
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setBuyNum(buyNum);
			item.setSubTotal(subTotal);
			//获得购物车---判断是否在session中已经存在购物车
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart==null){
				cart = new Cart();
			}
			Map<String, CartItem> cartItems = cart.getCartItems();
			double newSubTotal = 0;
			if(cartItems.containsKey(pid)){//key是pid,如果key已经存在
				CartItem cartItem = cartItems.get(pid);
				int oldBuyNum = cartItem.getBuyNum();//取出原有商品的数量
				oldBuyNum+=buyNum;//加上再次购买数量
				cartItem.setBuyNum(oldBuyNum);
				cart.setCartItems(cartItems);//cartItems也要重新设置
				double oldSubTotal = cartItem.getSubTotal();//原来该商品的小计
				newSubTotal = buyNum*product.getShop_price();//新买的该商品的小计
				cartItem.setSubTotal(oldSubTotal+newSubTotal);//该商品的最新小计
			}else{//购物车中没有该商品
				cart.getCartItems().put(product.getPid(), item);
				newSubTotal = buyNum*product.getShop_price();
			}
			double total = cart.getTotal()+newSubTotal;
			cart.setTotal(total);//设置所有商品总计
			session.setAttribute("cart", cart);//重新设置购物车
			return "success";//只能用转发,如果这里用重定向刷新页面商品数量会再次计算,用转发刷新cart.jsp页面,不会将请求传入后台
		}
	}
	//显示商品的详细信息功能
	public String productInfo() {
		String currentPage = request.getParameter("currentPage");//获得当前页
		String cid = request.getParameter("cid");//获得商品类别
		String pid = request.getParameter("pid");// 获得要查询的商品的id
		ProductService service = new ProductService();
		Product product = service.findProductByPid(pid);
		request.setAttribute("product", product);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cid", cid);
		// 获得客户端携带cookie---获得名字是pids的cookie
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					//查询pids对应的value格式("3-1-2")转换成集合,并添加当前pid至集合中
					pids = cookie.getValue();//字符串"3-1-2"
					String[] split = pids.split("-");//字符数组{"3","1","2"}
					List<String> aslist = Arrays.asList(split);//集合["3","1","2"]
					LinkedList<String> list=new LinkedList<String>(aslist);
					if (list.contains(pid)) {//集合中存在当前pid
						list.remove(pid);//移除集合中的当前pid
						list.addFirst(pid);//将当前pid添加至集合首部
					} else {
						list.addFirst(pid);//将当前pid添加至集合首部
					}
					//将集合转换成value格式("3-1-2-8")
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list.size() && i < 7; i++) {
						sb.append(list.get(i));
						sb.append("-");
					}
					pids = sb.substring(0, sb.length() - 1);//最后一个-
				}
			}
		}
		Cookie cookie_pids = new Cookie("pids", pids);
		response.addCookie(cookie_pids);
		return "success";
	}
	//删除单一商品
	public String delProFromCart() {
		String pid = request.getParameter("pid");//获得要删除的item的pid
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubTotal());//需要修改总价
			cartItems.remove(pid);;//删除session中的购物车中的购物项集合中的item
			cart.setCartItems(cartItems);
		}
		session.setAttribute("cart", cart);
		return "success";//跳转回cart.jsp
	}
	//清空购物车
	public String productClearCart() {
		session.removeAttribute("cart");
		return "success";//跳转回cart.jsp
	}
}
