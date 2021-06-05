package app0604.tree.basic;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeTest extends JFrame{
	JTree tree;
	JScrollPane scroll;
	
	public TreeTest() {
		//최상위 노드 생성하기 
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("나의 스킬");
		DefaultMutableTreeNode c1=new DefaultMutableTreeNode("Java");
		DefaultMutableTreeNode cc1=new DefaultMutableTreeNode("Java상급");
		DefaultMutableTreeNode cc2=new DefaultMutableTreeNode("Java중급");
		DefaultMutableTreeNode cc3=new DefaultMutableTreeNode("Java초급");
		DefaultMutableTreeNode c2=new DefaultMutableTreeNode("Oracle");
		DefaultMutableTreeNode c3=new DefaultMutableTreeNode("Node.js");
		
		//노드간 소속관계 설정
		c1.add(cc1);
		c1.add(cc2);
		c1.add(cc3);
		
		top.add(c1);
		top.add(c2);
		top.add(c3);
		
		tree = new JTree(top);
		scroll = new JScrollPane(tree);
		
		//트리 붙이기
		add(scroll);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TreeTest();
	}

}