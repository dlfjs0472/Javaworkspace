package app0601.xml.data.table;

//산 한개의 정보를 담을 수 있는 VO 정의
public class Mountain {
	private String mntiadd; //산의 소재지
	private String mntidetails;//상세내용
	private String mntihigh;//산의 높이
	private int mntilistno;//산의 번호 
	private String mntiname;//산의 이름
	
	
	public String getMntiname() {
		return mntiname;
	}
	public void setMntiname(String mntiname) {
		this.mntiname = mntiname;
	}
	public String getMntiadd() {
		return mntiadd;
	}
	public void setMntiadd(String mntiadd) {
		this.mntiadd = mntiadd;
	}
	public String getMntidetails() {
		return mntidetails;
	}
	public void setMntidetails(String mntidetails) {
		this.mntidetails = mntidetails;
	}
	public String getMntihigh() {
		return mntihigh;
	}
	public void setMntihigh(String mntihigh) {
		this.mntihigh = mntihigh;
	}
	public int getMntilistno() {
		return mntilistno;
	}
	public void setMntilistno(int mntilistno) {
		this.mntilistno = mntilistno;
	}
	
	
}
