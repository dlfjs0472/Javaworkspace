package monster; //이 클래스가 monster라는 디렉토리에 있음을 선언

//패키지에 넣은 클래스는 아무도 접근할 수 없기에, 공개해놓아야 한다!!!!
public class PocketMon{
	
	String name = "피카츄";
	int level = 19; //전투력

	public void sound(){
		System.out.println("삐까!!");
	}
}