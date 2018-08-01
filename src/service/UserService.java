package service;
import dao.UserDao;
import domain.User;
public class UserService {
/* *
 * 用户服务类
 * */
	//输入用户名判断用户是否存在
	public boolean checkUsername(String username){
		UserDao dao = new UserDao();
		Long isExist = dao.checkUsername(username);
		return isExist>0?true:false;
	}
    //注册用户
	public boolean register(User user) {
		UserDao dao = new UserDao();
		dao.add(user);
		if(dao.find(user.getUsername(), user.getPassword())!=null){
			return true;
		}else{
			return false;
		}
	}
	//修改激活状态
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		dao.active(activeCode);
	}
	//输入用户名和密码判断用户是否存在并返回user对象
	public User checkUser(String username,String password) {
		UserDao dao=new UserDao();
		User user=dao.find(username, password);
		if(user!=null){
			return user;
		}else{
			return null;
		}
	}

}
