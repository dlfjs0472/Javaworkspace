package com.minssam.shoppingapp.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.minssam.shoppingapp.main.AppMain;
import com.minssam.shoppingapp.main.Page;

public class JoinForm extends Page{
	JPanel p_container; //BorderLayout
	JPanel p_center;//form
	JPanel p_south; //버튼영역
	JLabel la_id,la_pass,la_name;
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JButton bt_login, bt_join;
	
	
	public JoinForm(AppMain appMain) {
		super(appMain);
		
		//생성
		p_container = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		la_name = new JLabel("Name");
		t_id = new JTextField();
		t_pass = new JPasswordField();
		t_name = new JTextField();
		bt_login = new JButton("Login");
		bt_join = new JButton("등록");
		
		
		
		//스타일 레이아웃
		p_container.setPreferredSize(new Dimension(250, 85));
		p_container.setLayout(new BorderLayout());
		p_center.setLayout(new GridLayout(3,2));
		
		//조립
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		p_center.add(la_name);
		p_center.add(t_name);
		
		p_south.add(bt_login);
		p_south.add(bt_join);
		
		p_container.add(p_center);
		p_container.add(p_south,BorderLayout.SOUTH);
		
		add(p_container);
		
		
		//리스너
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinForm.this.getAppMain().showHide(4);
			}
		});
		
		
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		//보이기
		
	}
	
	//회원등록
	public void regist() {
		String sql="insert into member(m_id, m_pass, m_name) values(?,?,?)";
		PreparedStatement pstmt=null;
		try {
			pstmt=this.getAppMain().getCon().prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword()));
			pstmt.setString(3, t_name.getText());
			
			int result = pstmt.executeUpdate();
			if(result==1) {
				JOptionPane.showMessageDialog(this.getAppMain(), "가입성공");
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "가입실패");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.getAppMain().release(pstmt);
		}
		
		
		
	}
}
