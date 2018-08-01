package web.action.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import domain.Admin;
import service.AdminService;
public class AdminAction implements ServletRequestAware,ServletResponseAware{
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
    //管理员登录
  	public String login() {
  		String ausername=request.getParameter("ausername");
  		String apassword=request.getParameter("apassword");
  		AdminService service=new AdminService();
  		Admin admin=service.login(ausername,apassword);
  		if(admin==null){
  			String info="用户名或密码错误!!";
  			request.setAttribute("info", info);
  			response.setContentType("text/html;charset=UTF-8");
  			return "error";
  		}else{
  			session.setAttribute("admin", admin);
  			return "success";
  		}
  	}
}
