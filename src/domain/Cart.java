package domain;
import java.util.HashMap;
import java.util.Map;
public class Cart {
/* *
 * 购物车订单
 * */
	//存储购物车订单项,String代表pid
	private Map<String,CartItem> cartItems = new HashMap<String,CartItem>();
	private double total;//商品的总计
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}	
}
