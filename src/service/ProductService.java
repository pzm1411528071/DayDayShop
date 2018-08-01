package service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import domain.Category;
import domain.Order;
import domain.PageBean;
import domain.Product;
import utils.DataSourceUtils;
public class ProductService {
	public List<Product> findAllProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAllProduct();
	}
	//根据关键字查询商品
	public List<Object> findProductByWord(String word) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findProductByWord(word);
	}
	//获得热门商品
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;

	}
	//获得最新商品
	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}
	//获得分类数据
	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
    //根据商品类别id,当前页,每页显示商品数获得分页信息
	public PageBean<Product> findProductListByCid(String cid, int currentPage, int currentCount) {
        ProductDao dao = new ProductDao();
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(currentPage);//设置当前页
		pageBean.setCurrentCount(currentCount);//设置每页显示的条数
		int totalCount = 0;//设置商品总数
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);//向上取整获得商品总页数
		pageBean.setTotalPage(totalPage);
		//当前页显示的数据
		int index = (currentPage-1)*currentCount;// 当前页与起始索引index的关系
		List<Product> productList = null;
		try {
			productList = dao.findProductByPage(cid,index,currentCount);// select * from product where cid=? limit ?,?
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setProductList(productList);
		return pageBean;
	}
	//根据商品id获得商品信息
	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	//提交订单 将订单的数据和订单项的数据存储到数据库中
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		try {
			DataSourceUtils.startTransaction();//开启事务
			dao.addOrders(order);//调用dao层存储order表数据的方法
			dao.addOrderItem(order);//调用dao层存储orderitem表数据的方法
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();//事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataSourceUtils.commitAndRelease();//提交事务并释放资源
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//修改订单地址,姓名,电话信息
	public void updateOrderAdrr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAdrr(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//修改订单支付状态 1:已支付,0:未支付
	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//获得指定用户的订单集合
	public List<Order> findAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	//获得指定订单的订单项集合
	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}
}
