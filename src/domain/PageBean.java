package domain;
import java.util.ArrayList;
import java.util.List;
public class PageBean<T> {
/* *
 * 商品列表的页面信息类
 * */
	private int currentPage;//当前页
	private int currentCount;//当前页显示的商品数
	private int totalCount;//商品总数
	private int totalPage;//总页数
	private List<T> productList = new ArrayList<T>();//存放商品信息
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getProductList() {
		return productList;
	}
	public void setProductList(List<T> productList) {
		this.productList = productList;
	}
}
