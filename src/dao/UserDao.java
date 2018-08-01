package dao;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import domain.User;
import utils.DataSourceUtils;
public class UserDao {
/* *
 * 用户信息-数据库操作类
 * */
	//查询用户名是否存在
	public long checkUsername(String username){
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";//大于1说明存在
		long query=0;
		try {
			query = (long) runner.query(sql, new ScalarHandler(), username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return query;//QueryRunner默认会关闭资源
	}
	//查询用户名是否存在
	public User find(String username,String password){
		User user=null;
		try {
			user=new QueryRunner(DataSourceUtils.getDataSource()).query("select * from user user where username=? and password=?", new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//添加单个用户
	public void add(User user){
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user(uid,username,password,email,name,province,city,sex,birthday,telephone,state,code) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			runner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),user.getProvince(),user.getCity(),user.getSex(),user.getBirthday(),user.getTelephone(),user.getState(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//删除单个用户
	public void delete(String username){
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from user where username=?";
		try {
			runner.update(sql,username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//更新单个用户
	public void update(User user){
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set password=? and email=? and name=? and province=? and city=? and sex=? and birthday=? where username=?";
		try {
			runner.update(sql,user.getPassword(),user.getEmail(),user.getName(),user.getProvince(),user.getCity(),user.getSex(),user.getBirthday(),user.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//查询所有用户
	public List<User> findAll(){
		List<User> list=null;
		try {
			list = new QueryRunner(DataSourceUtils.getDataSource()).query("select * from user", new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//修改激活状态
	public void active(String activeCode) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state=? where code=?";
		try {
			runner.update(sql, 1,activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
