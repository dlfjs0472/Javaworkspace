package use;
import animal.Bird;//클래스패스 루트, 경로 밑에서부터 적자
			//
class UseBird{
	public static void main(String[] args){
		//나와는 다른 경로에 있는 클래스이 Bird를 사용해본다!!
		Bird b = new Bird();
		b.fly();
	}
}