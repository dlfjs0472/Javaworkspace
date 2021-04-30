package race;

//인간을 정의한다 (상위 객체일 수록 보편적 특징이 와야함)
public class Human{
	int IQ = 90;
	boolean hasName=true;

	public Human(int IQ){
		this.IQ=IQ;
		System.out.println("Human의 초기화");
	}
	//생성자 오버로딩..
	public Human(){
	}

	//따로 생성자를 만들지 않아 눈에는 안보이지만 자동으로 디폴트 생성자 생성

	public void speak(){
		System.out.println("말할 수 있다");
	}
	public void drive(){
		System.out.println("운전할 수 있다");
	}
	


}