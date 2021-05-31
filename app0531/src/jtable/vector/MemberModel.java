package jtable.vector;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//Member 테이블의 전용 TableModel
public class MemberModel extends AbstractTableModel {
	Vector<Member> data=new Vector<Member>(); 
	Vector<String> column=new Vector<String>();
	
	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return column.size();
	}
	
	public String getColumnName(int col) {
		return column.get(col);
	}

	public Object getValueAt(int row, int col) {
		String value =null;
		Member member = data.get(row);
	
		if(col==0) {
			value=Integer.toString(member.getMember_id());
		}else if(col==1) {
			value=member.getUser_id();
		}else if(col==2) {
			value=member.getPassword();
		}else if(col==3) {
			value=member.getName();
		}else if(col==4) {
			value=member.getRegdate();
		}
		return value;
	}

	
}
