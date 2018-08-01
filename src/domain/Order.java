package domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Order {
/* *
 * 订单类对应数据库orders(mysql数据库不能取表名为order会与其内置标识符发生冲突)
 * */
	private String oid;//订单的订单号
	private Date ordertime;//下单时间
	private double total;//订单的总金额
	private int state;//订单支付状态 1代表已付款 0代表未付款
	private String address;//收货地址
	private String name;//收货人
	private String telephone;//收货人电话
	private User user;//订单属于哪个用户
	//虽然订单项已经描述了订单与订单项的关系,订单项可以确定订单,且数据库订单表并没有下面字段,但是为了分层取数据数据,加入orderItems,这样直接传Order就可以得到订单项
	List<OrderItem> orderItems = new ArrayList<OrderItem>();//订单中有多少订单项,购物车信息能改,订单信息不能改所以定义成List
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
