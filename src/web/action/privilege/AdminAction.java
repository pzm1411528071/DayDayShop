package web.action.privilege;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import domain.Admin;
import domain.Category;
import domain.Condition;
import domain.Order;
import domain.Product;
import service.AdminService;
import utils.CommonsUtils;
public class AdminAction implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private File file;//上传文件对象
	private String fileFileName;//上传文件名
	private String fileContenType;//上传文件内容类型
	//传递请求到service层
	AdminService service = new AdminService();
    public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContenType() {
		return fileContenType;
	}
	public void setFileContenType(String fileContenType) {
		this.fileContenType = fileContenType;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
    public void setServletResponse(HttpServletResponse response) {
    	this.response=response;
	}
    //查询所有商品信息
  	public String adminFindAllProduct() {
  		//获得所有的商品的类别数据
  		List<Category> categoryList = service.findAllCategory();
  		request.setAttribute("categoryList", categoryList);
  		List<Product> productList = service.findAllProduct();
  		//将productList放到request域
  		request.setAttribute("productList", productList);//list.jsp需要categoryList和productList的数据
  		return "success";
  	}
  	//ajax获得所有商品类别
  	public void adminFindAllCategory() throws Exception {
  		//提供一个List<Category> 转成json字符串
  		List<Category> categoryList = service.findAllCategory();
  		Gson gson = new Gson();
  		String json = gson.toJson(categoryList);
  		response.setContentType("text/html;charset=UTF-8");
  		response.getWriter().write(json);
  	}
  	//直接获得所有商品类别
  	public String adminFindAllCategoryUI() {
  		//获得所有的商品的类别数据
  		List<Category> categoryList = service.findAllCategory();
  		request.setAttribute("categoryList", categoryList);
  		return "success";
  	}
  	//获得所有的订单
  	public String adminFindAllOrders() {
  		//获得所有的订单信息----List<Order>
  		List<Order> orderList = service.findAllOrders();
  		request.setAttribute("orderList", orderList);
  		return "success";
  	}
  	//根据订单id查询订单项和商品信息
  	public void adminFindOrderInfoByOid() throws Exception  {
  		Thread.sleep(1000);//模拟加载
  		//获得oid
  		String oid = request.getParameter("oid");
  		List<Map<String,Object>> mapList = service.findOrderInfoByOid(oid);
  		Gson gson = new Gson();
  		String json = gson.toJson(mapList);
  		response.setContentType("text/html;charset=UTF-8");
  		response.getWriter().write(json);
  	}
  	//根据商品分类cid删除商品分类
  	public String adminDelCategoryByCid() {
  		//获取要删除的cid
  		String cid = request.getParameter("cid");
  		//传递cid到service层
  		service.delCategoryByCid(cid);
  		return "success";//进入动态页面category/list.jsp
  	}
  	//根据商品分类cid删除商品分类
  	public String adminAddCategory() {
  		String cname=request.getParameter("cname");
  		//获得所有商品分类数量
  		int count=service.findAllCategory().size();
  		//count+1,cname传递到service层
  		String cid=String.valueOf(count+1);//count+1作为cid
  		service.addCategory(cid,cname);
  		return "success";//进入动态加载页面list.jsp
  	}
  	//根据商品分类cname更改商品分类
  	public String adminUpdateCategory() {
  		String cname=request.getParameter("cname");
  		String newcname=request.getParameter("newcname");
  		//获得要修改的商品类别
  		Category category=service.findCategory(cname);
  		//修改商品类别名称
  		service.updateCategory(category.getCid(), newcname);
  		return "success";//进入动态加载页面category/list.jsp
  	}
  	//根据商品pid删除商品信息
  	public String adminDelProductByPid() {
  		//获取要删除的pid
  		String pid = request.getParameter("pid");
  		//传递pid到service层
  		service.delProductByPid(pid);
  		return "success";
  	}
  	//根据部分商品名称,热度,种类查询商品信息
  	public String adminSearchProductList() throws Exception {
  		//1、收集表单数据
  		Map<String, String[]> properties = request.getParameterMap();
  		//2、将散装的查询数据封装到一个实体中
  		Condition condition = new Condition();
  		BeanUtils.populate(condition, properties);
  		//3、将实体传递给service层
  		List<Product> productList = service.findProductListByCondition(condition);
  		//准备商品类别
  		//获得所有的商品的类别数据
  		List<Category> categoryList = service.findAllCategory();
  		request.setAttribute("condition", condition);
  		request.setAttribute("categoryList", categoryList);
  		request.setAttribute("productList", productList);
  		return "success";
  	}
  	//显示默认商品信息至商品修改页面
  	public String adminUpdateProductUI() {
  		//获得要查询Product的pid
  		String pid = request.getParameter("pid");
  		//传递pid查询商品信息
  		Product product = service.findProductByPid(pid);
  		//获得所有的商品的类别数据
  		List<Category> categoryList = service.findAllCategory();
  		request.setAttribute("categoryList", categoryList);
  		request.setAttribute("product", product);
  		return "success";
  	}
  	//修改商品信息
  	public String adminUpdateProduct() throws Exception  {
  		//1、获取数据
  		Map<String, String[]> properties = request.getParameterMap();
  		//2、封装数据
  		Product product = new Product();
  		BeanUtils.populate(product, properties);
  		//手动设置表单中没有数据
  		product.setPimage("images/ele_equipment/pc/pc00019.jpg");//默认统一改为该图片
  		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//上架日期
  		String pdate = format.format(new Date());
  		product.setPdate(pdate);
  		product.setPflag(0);//商品是否下架  0代表未下架
  		//3、传递数据给service层
  		service.updateProduct(product);
  		//跳转到列表页面
  		return "success";//进入动态显示product/list.jsp
  	}
  	//添加商品信息
  	public String adminAddProduct() throws Exception {
  	    //上传图片至服务器appache而不是工程的地址,所以先在工程wbroot下创建upload,
		//但是项目部署后存入服务器的图片不会插入工程的upload
  		String realPath=ServletActionContext.getServletContext().getRealPath("/upload");
  	    //创建上传文件要存放的文件及其存放位置 (绝对路径)
		File saveFile=new File(new File(realPath),fileFileName);//上传文件存放路径
		if(!saveFile.getParentFile().exists()){
			saveFile.getParentFile().mkdir();
		}
		//利用commons.io包中的工具类,实现文件复制
		FileUtils.copyFile(file, saveFile);
		 //1、获取数据
  		Map<String, String[]> properties = request.getParameterMap();
  		//2、封装数据
  		Product product = new Product();
  		BeanUtils.populate(product, properties);
  	    //3.补充未封装的属性
		product.setPid(CommonsUtils.getUUID());//使用uuid作为商品pid
		product.setPimage("upload/"+fileFileName);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = format.format(new Date());//Date转String
		product.setPdate(pdate);
		product.setPflag(0);//未下架
		Category category = new Category();
		category.setCid(product.getCid());
		product.setCategory(category);//通过将cid传入category不是直接操作cid,便于后面sql语句的多表关联
		//将product传递给service层
		service.saveProduct(product);
  		return "success";
  	}
}