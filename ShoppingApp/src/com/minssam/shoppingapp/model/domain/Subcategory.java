package com.minssam.shoppingapp.model.domain;

//아래의 클래스는 로직 작성용이 아닌, 오직 Subcategory 레코드 1건을 담기위한 VO이다!!
public class Subcategory {
	private int subcategory_id;
	private int topcategory_id;
	private String sub_name;
	
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	
	

}
