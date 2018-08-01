package domain;
public class CartItem {
/* *
 * 购物车订单项:product+buyNum+subTotal
 * */
	private Product product;//购物车订单项内部的商品
	private int buyNum;//购物车订单项商品数量
	private double subTotal;//购物车订单项商品金额
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
