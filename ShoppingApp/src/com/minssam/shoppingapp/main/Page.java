package com.minssam.shoppingapp.main;

import java.awt.Dimension;

import javax.swing.JPanel;

//모든 페이지의 부모패널 정의(공통된 코드가 있을경우 이 클래스에 적용시키자)
public class Page extends JPanel{
	private AppMain appMain; 
	
	//다른 패키지에서도 접근할 수 있도록
	public AppMain getAppMain() {
		return appMain;
	}
	
	public Page(AppMain appMain) {
		this.appMain=appMain;
		setPreferredSize(new Dimension(1200,700));
		setVisible(false);
	}
}
