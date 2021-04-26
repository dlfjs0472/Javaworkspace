/*이중포문으로 구구단 출력*/
class GuGu{
	public static void main(String[] args) {
		//main의 매개변수는 java.exe 호출시 그 값을 넘길 수 있다..
		//java 사과 딸기 바나나 <--매개변수에 크기가 3인 String배열이 전달
		System.out.println("당신이 main 호출시 즉 자바실행시 넘긴 매개변수는"+args.length);
			//넘겨받은 배열의 길이만큼 반복문 실행
			for(int i=0; i<args.length;i++){
				System.out.println(args[i]);
			}
	}
}
