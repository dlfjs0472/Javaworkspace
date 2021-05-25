package app0525.thread2;

//무한루프로 숫자를 증가시키는 실행단위
public class CounterThread extends Thread{
	int count=0;
	//개발자는 독립적으로 즉 쓰레드로 실행시키고픈 로직을
	//run 메서드 안에 작성하면 된다
	public void run() {
		while(true) {
			count++;
			System.out.println(count);
			
			//non-Runnable영역으로 피신시키기!!!
			try {
				Thread.sleep(1000); // 1초간 runnable에서 빠져나가 있다가 해당시간이 초과되면
											//다시 runnable로 복귀...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
