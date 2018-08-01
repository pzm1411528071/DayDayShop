package service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import dao.AdminDao;
import domain.Admin;
import domain.Category;
import domain.Condition;
import domain.Order;
import domain.Product;
public class AdminService{
	//查询所有的商品
	public List<Product> findAllProduct(){
		AdminDao dao = new AdminDao();
		try {
			return dao.findAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//获得所有商品类别
	public List<Category> findAllCategory() {
		AdminDao dao = new AdminDao();
		try {
			return dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    //保存商品
	public void saveProduct(Product product){
		AdminDao dao = new AdminDao();
		try {
			dao.saveProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//根据pid删除商品
	public void delProductByPid(String pid){
		AdminDao dao = new AdminDao();
		try {
			dao.delProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//更新商品
	public void updateProduct(Product product){
		AdminDao dao = new AdminDao();
		try {
			dao.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//根据订单id查询订单项和商品信息
	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminDao dao = new AdminDao();
		try {
			return dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//根据商品分类cid删除商品分类
	public void delCategoryByCid(String cid) {
		AdminDao dao = new AdminDao();
		try {
		    dao.delCategoryByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//保存商品分类
	public void addCategory(String cid,String cname) {
		AdminDao dao = new AdminDao();
		try {
		    dao.addCategory(cid,cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//根据商品分类cname更改商品分类
	public Category findCategory(String cname) {
		AdminDao dao = new AdminDao();
		try {
			return dao.findCategory(cname);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//根据商品分类cname更改商品分类
	public void updateCategory(String cid, String cname) {
		AdminDao dao = new AdminDao();
		try {
		    dao.updateCategory(cid,cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//获得所有订单
	public List<Order> findAllOrders() {
		AdminDao dao = new AdminDao();
		List<Order> ordersList = null;
		try {
			ordersList = dao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}
	//根据条件查询商品列表
	public List<Product> findProductListByCondition(Condition condition){
		AdminDao dao = new AdminDao();
		try {
			return dao.findProductListByCondition(condition);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//根据pid查询商品对象
	public Product findProductByPid(String pid){
		AdminDao dao = new AdminDao();
		try {
			return dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//管理员登录
	public Admin login(String ausername, String apassword) {
		AdminDao dao = new AdminDao();
		try {
			return dao.login(ausername,apassword);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
