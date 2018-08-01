package web.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import domain.User;
import service.UserService;
@WebFilter("/*")
public class AutoLoginOrRemUsernameFilter implements Filter{
/* *
 * 自动登录过滤器
 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;//强转成HttpServletRequest
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			String cookie_username = null;
			String cookie_password = null;
			//获取携带用户名和密码cookie
			Cookie[] cookies = req.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					//获得想要的cookie
					if("cookie_username".equals(cookie.getName())){
						cookie_username = cookie.getValue();
					}
					if("cookie_password".equals(cookie.getName())){
						cookie_password = cookie.getValue();
					}
				}
			}
			UserService service = new UserService();
			if(cookie_username!=null&&cookie_password!=null){
				//选择--自动登录
				//获得cookie里的用户名和密码去数据库校验该用户名和密码是否正确
				user = service.checkUser(cookie_username,cookie_password);
				if(user==null){
					System.out.println("cookie存入的user信息不正确!!!");
				}else{
					req.getSession().setAttribute("user", user);//完成自动登录 	
				}
			}else if(cookie_username!=null&&cookie_password==null){
				//选择--记住用户名
				//获得cookie里的用户名去数据库校验该用户名是否存在
				if(!service.checkUsername(cookie_username)){
					System.out.println("cookie存入的username不正确!!!");
				}else{
					req.getSession().setAttribute("user", user);//完成记住用户名 	
				}
			}
		}
		chain.doFilter(req, response);//放行
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}
}
