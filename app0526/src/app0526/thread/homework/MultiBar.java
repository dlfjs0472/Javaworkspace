package app0526.thread.homework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class MultiBar extends JFrame{
	JButton bt;
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;

	
	public MultiBar() {
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bt=new JButton("Start!!");
		
		Dimension d = new Dimension(275, 35);
		bar1.setPreferredSize(d);
		bar2.setPreferredSize(d);
		bar3.setPreferredSize(d);
		
		setLayout(new FlowLayout());
		
		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startBar();
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(800, 400, 300, 200);
	}
	public void startBar() {
		MyThread m1=new MyThread(bar1, 20);
		MyThread m2=new MyThread(bar2, 10);
		MyThread m3=new MyThread(bar3, 5);
		
		m1.start();
		m2.start();
		m3.start();
		
	}
	public static void main(String[] args) {
		new MultiBar();
	}
}