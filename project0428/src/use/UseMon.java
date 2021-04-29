/*현재 패키지와는 다른 패키지에 있는 클래스 사용해보기!!*/
package use;

//이 클래스에서 사용하고 픈 외부에 존재하는 클래스는 import명령어로 명시한다
//이때, 주의할점은 사용하고자 하는 클래스형태는 원본이 아니라, 사용가능한 .class
//기계어!! 따라서 bin 디렉토리 밑에있는 클래스의 위치를 지정해줘야 한다..

//아래의 코드는 개념상으로는 틀린게 없다.. 하지만, 중립적인 루트를 해야하므로 classpath 설정!
//자바는 디렉토리라는 말도 쓰지 않을 뿐 아니라, 즉 패키지라고 하며 패키지는 .으로 구분한다...
import monster.PocketMon;

class UseMon{
	public static void main(String[] args){
		//포켓몬 인스턴스 1개 생성하여, 메서드 호출하기!!
			PocketMon mon = new PocketMon();
	}
}