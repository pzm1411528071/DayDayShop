package domain;
public class Condition {
/* *
 * 商品管理的搜索条件封装类
 * */
	private String pname;//商品名称
	private String isHot;//是否热门
	private String cid;//商品类别编号
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getIsHot() {
		return isHot;
	}
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
	
}
