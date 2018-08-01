package domain;
public class OrderItem {
/* *
 * 订单项类
 * */
	private String itemid;//订单项的id
	private int count;//订单项内商品的购买数量
	private double subTotal;//订单项小计
	private Product product;//订单项内部的商品
	private Order order;//订单项属于哪个订单
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
