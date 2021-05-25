package app0525.thread2;

//무한루프로 별을 출력하는 실행단위
public class StarThread extends Thread {

	public void run() {
		while (true) { // 브레이스를 만나면 죽는다 죽지 않게 하려면 반복문
			System.out.println("★");
		}
	}

}
