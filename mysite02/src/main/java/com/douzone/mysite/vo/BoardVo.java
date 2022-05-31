package com.douzone.mysite.vo;

public class BoardVo {

	private Long no;
	private String title;
	private String contents;
	private String regDate;
	private Long hit;
	private Long groupNo;
	private Long orderNo;
	private Long depth;

	private Long userNo;
	private String userName;





	public Long getNo() {
		return no;
	}





	public void setNo(Long no) {
		this.no = no;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getContents() {
		return contents;
	}





	public void setContents(String contents) {
		this.contents = contents;
	}





	public String getRegDate() {
		return regDate;
	}





	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}





	public Long getHit() {
		return hit;
	}





	public void setHit(Long hit) {
		this.hit = hit;
	}





	public Long getGroupNo() {
		return groupNo;
	}





	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}





	public Long getOrderNo() {
		return orderNo;
	}





	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}





	public Long getDepth() {
		return depth;
	}





	public void setDepth(long depth) {
		this.depth = depth;
	}





	public Long getUserNo() {
		return userNo;
	}





	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	@Override
	public String toString() {
		return String.format("BoardVo [no=%s, title=%s, contents=%s, redDate=%s, hit=%s, groupNo=%s, orderNo=%s, depth=%s, userNo=%s, userName=%s]", no, title, contents, regDate, hit, groupNo, orderNo, depth, userNo, userName);
	}

}