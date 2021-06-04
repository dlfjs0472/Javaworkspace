package app0603.email;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MailSendApp extends JFrame{
	JTextField t_receiver;
	JTextField t_sender;
	JTextField t_title;
	JTextArea area;
	JButton bt;
	Properties props;
	
	public MailSendApp() {
		t_receiver = new JTextField(25);
		t_sender = new JTextField("dlfjs0472@gmail.com",25);
		t_title = new JTextField(25);
		area = new JTextArea();
		bt = new JButton("전송");
		
		setLayout(new FlowLayout());
		area.setPreferredSize(new Dimension(250, 150));
		
		add(t_receiver);
		add(t_sender);
		add(t_title);
		add(area);
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMail();
			}
		});
		
		setBounds(600, 200, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public void sendMail() {
		props = new Properties(); //Map 유형중 하나!!
		//props.put("키"," 넣고싶은값"); 
		props.put("mail.smtp.host","smtp.gmail.com"); //smpt 서버 주소 작성	
		props.put("mail.smtp.port",465); //smpt 서버 포트번호	
		props.put("mail.smtp.auth","true"); //권한 true	
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust","smtp.gmail.com"); //SSL 사용시
		
		//세션객체 생성(java mail api에 Session 이라는 객체가 있다)
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			//구글 개인설정(계정관리-보안-2단계인증-앱비밀번호 )에서 생성된 앱 비밀번호를 메일 에서 사용할 수 있다!
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication("이메일", "비밀번호");
				return new PasswordAuthentication("dlfjs0472@gmail.com", "");
			}
		});
		
		//메시지 구성(메일로 보낼 내용 등 구성)
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(t_sender.getText()));//발신자 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(t_receiver.getText())); //받는자 주소
			message.setSubject(t_title.getText()); //메일 주소
			message.setContent(area.getText(), "text/html;charset=utf8");//HTMl을 지원하는 내용일 경우 인코딩 지정하자!!
			Transport.send(message);// 구성된 메시지 객체를 이용하여 전송 시작~
			JOptionPane.showMessageDialog(this, "메일발송 성공");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MailSendApp();
	}
}
