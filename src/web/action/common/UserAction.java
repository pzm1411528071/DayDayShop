package web.action.common;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import domain.User;
import service.UserService;
import utils.CommonsUtils;
import utils.MD5Utils;
import utils.MailUtils;
@SuppressWarnings("all")
public class UserAction implements ServletRequestAware,ServletResponseAware{
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
	// 用户注销
	public String userLogout() {
		session.removeAttribute("user");//从session中将user删除
		Cookie cookie_username = new Cookie("cookie_username", "");//将存储在客户端的cookie删除掉
		cookie_username.setMaxAge(0);
		Cookie cookie_password = new Cookie("cookie_password", "");
		cookie_password.setMaxAge(0);
		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		return "success";
	}
	// 生成验证码
	public void userCheckCode() throws Exception {
		response.setContentType("image/jpeg");
		int width = 60;
		int height = 30;
		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "No-caxhe");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 创建内存图像并获得其图形上下文
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 产生随机验证码
		// 定义验证码的字符表
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}
		// 产生图像
		// 画背景
		g.setColor(new Color(0x008080));
		g.fillRect(0, 0, width, height);
		// 随机产生120个干扰点
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);// 干扰点
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));// 斜体|粗体
		// 在不同的高度上输出验证码的不同字符
		g.drawString("" + rands[0], 1, 19);
		g.drawString("" + rands[1], 16, 18);
		g.drawString("" + rands[2], 31, 20);
		g.drawString("" + rands[3], 46, 21);
		g.dispose();// 释放画笔资源
		// 将图像输出到客户端
		ServletOutputStream sos = response.getOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", baos);
		byte[] buffer = baos.toByteArray();
		response.setContentLength(buffer.length);
		sos.write(buffer);
		baos.close();
		sos.close();
		// 将验证码放在session中
		session.setAttribute("CheckCode", new String(rands));
	}
	// 登录页面刷新改变验证码
	public String userLoginChangeCheckCode() {
		return "success";
	}
	// 处理登录
	public String userLogin() throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String info = "";
		String serverCheckCode = (String) session.getAttribute("CheckCode");// 服务器端验证码
		String userCheckCode = request.getParameter("checkcode");// 客户端验证码
		if (password == null) {
			return "error";
		} else {
			String md5Str = MD5Utils.md5(password);// 获取加密后的字符串
			UserService service = new UserService();
			User user=service.checkUser(username, md5Str);
			if (user!=null) {
				//***************判断用户是否勾选了记住用户名*****************
				String rememUsername=request.getParameter("rememUsername");
				if("rememUsername".equals(rememUsername)){
					//创建存储用户名的cookie
					Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
					cookie_username.setMaxAge(10*60);//设置cookie的最大生存时间,以秒为单位
					response.addCookie(cookie_username);
				}
				//***************判断用户是否勾选了自动登录*****************
				String autoLogin = request.getParameter("autoLogin");//获得autoLogin的value
				if("autoLogin".equals(autoLogin)){
					//创建存储用户名的cookie
					Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
					cookie_username.setMaxAge(10*60);//设置cookie的最大生存时间,以秒为单位
					//创建存储密码的cookie
					Cookie cookie_password = new Cookie("cookie_password",user.getPassword());
					cookie_password.setMaxAge(10*60);
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);
				}
				session.setAttribute("user", user);//将user对象放入session中
				return "success";
			} else {
				if (!serverCheckCode.equalsIgnoreCase(userCheckCode)) {
					info = "验证码不正确";
				} else if (service.checkUsername(username)) {
					info = "密码不正确";
				} else {
					info = "用户名不正确或未注册";
				}
				request.setAttribute("loginInfo", info);
				return "error";
			}
		}
	}
	// 利用ajax检查用户名是否存在
	public void checkUsername() throws Exception {
		String username = request.getParameter("username");
		UserService service = new UserService();
		boolean isExist = false;
		isExist = service.checkUsername(username);
		response.getWriter().write("{\"isExist\":" + isExist + "}");// JSON格式返回
	}
	// 注册页面刷新改变验证码
	public String userRegisterChangeCheckCode() {
		return "success";
	}
	// 处理注册
	public String userRegister() {
		Map<String, String[]> properties = request.getParameterMap();// 通过name把请求参数封装到Map<String,String[]>中
		for (Iterator<?> iter = properties.entrySet().iterator(); iter.hasNext();) {// 遍历
			Map.Entry element = (Map.Entry) iter.next();
			Object strKey = element.getKey();// key值
			String[] value = (String[]) element.getValue();// value,数组形式
			System.out.print(strKey.toString() + "=");
			for (int i = 0; i < value.length; i++) {
				System.out.print(value[i] + ",");
			}
		}
		User user = new User();
		// 映射封装
		try {
			BeanUtils.populate(user, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 后台判断输入信息是否符合要求
		String info = "";
		String serverCheckCode = (String) session.getAttribute("CheckCode");// 服务器端验证码
		String userCheckCode = request.getParameter("checkcode");// 客户端验证码
		String confirmpwd = request.getParameter("confirmpwd");
		UserService service = new UserService();// 将user传递给service层
		if (serverCheckCode.equalsIgnoreCase(userCheckCode) != true) {
			info = "验证码不正确,请重新输入!";
			request.setAttribute("registerInfo", info);
			return "error1";
		} else {
			if ((!service.checkUsername(user.getUsername()))
					&& Pattern.matches("([A-Za-z])(\\w{5,})", user.getPassword())
					&& user.getPassword().equals(confirmpwd)
					&& Pattern.matches("\\w+@\\w+[.](com||cn|com.cn||org||gov)", user.getEmail())
					&& (user.getProvince() != "--请选择--") && (user.getBirthday() != null) && (user.getName() != null)
					&& (user.getSex() != null)) {
				String md5Str = MD5Utils.md5(user.getPassword());// 用户密码采用md5加密
				user.setPassword(md5Str);
				user.setUid(CommonsUtils.getUUID());// uuid设置用户id
				user.setState(0);// 设置为未激活
				String activeCode = CommonsUtils.getUUID();
				user.setCode(activeCode);// 设置激活码
				boolean isRegisterSuccess = service.register(user);
				if (isRegisterSuccess) {// 注册成功
					// 发送激活邮件
					String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
							+ "<a href='http://localhost:8080/DayDayShop/userActive?activeCode=" + activeCode + "'>"
							+ "http://localhost:8080/DayDayShop/userActive?activeCode=" + activeCode + "</a>";
					try {
						MailUtils.sendMail(user.getEmail(), emailMsg);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					return "success";// 使用转发便于将用户名和密码直接显示登录页面
				} else {
					return "error2";
				}
			} else {
				info = "部分信息为空或输入信息有误!";
				System.out.println((!service.checkUsername(user.getUsername())) + " "
						+ Pattern.matches("([A-Za-z])(\\w{5,})", user.getPassword())
						+ user.getPassword().equals(confirmpwd)
						+ Pattern.matches("\\w+@\\w+[.](com||cn|com.cn||org||gov)", user.getEmail())
						+ (user.getProvince() != "--请选择--") + (user.getBirthday() != null) + (user.getName() != null)
						+ (user.getSex() != null));
				request.setAttribute("registerInfo", info);
				return "error1";
			}
		}
	}
	//邮箱激活注册账户功能
	public String userActive() {
		//获得激活码
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		service.active(activeCode);
		//跳转到登录页面
        return "success";
	} 
}
