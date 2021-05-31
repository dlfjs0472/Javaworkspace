package jtable.vector;

//현실의 하나의 게시판을 DB분야에서는 Table로 정의하고, OOP에서는 class로 표현한다
//이때 oop개발자는 게시판의 컬럼만 봐도, oop의 클래스로 변환할 줄 알아야 함
//프로젝트 진행시 db에 존재하는 table이 만일 197개일 경우, 적어도 이에 대응하는 VO클래스의
//갯수는 197개 이상된다..
public class Notice {
	private int notice_id;
	private String title;
	private String writer;
	private String content;
	private int hit;
	private String regdate;
	
	
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	

}
