package app0526.thread.homework;

import javax.swing.JProgressBar;

//바를 제어하는 쓰레드 코드가 너무나 중복되고 있으므로, 재사용성을 고려한 클래스 정의!!
public class MyThread extends Thread{
	int n;
	int step;
	JProgressBar bar; //null
	
	public MyThread(JProgressBar bar, int step) {
		this.bar=bar;
		this.step=step;
	}
	
	public void run() {
		while(true) {
			n+=step;
			bar.setValue(n);
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}