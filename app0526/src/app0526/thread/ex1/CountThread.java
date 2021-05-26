package app0526.thread.ex1;

//메인실행부 대신, 무한루프로 카운트를 증가시킬 세부실행단위인 쓰레드를 정의한다
public class CountThread extends Thread{
	
	//개발자는 쓰레드로 수행할 코드를 run에 작성한다!!
	//그러면, 이 run메서드를 jvm이 원하는 시점에 호출해준다~!! 즉 개발자가 호출하면 일반메서드 호출이 되며
	//메인쓰레드에 의해 호출되어 버린다!!
	public void run() {
		while(true) {
			
		}
	}
}
