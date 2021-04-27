public class Desk2 {
	int x=9;
	static int y=3;
	



	{ //A
		for(int i=0;i<10;i++){
			x++;
		}
	}
	
	static { //B  , 클래스가 로드될때 동작한다!! 즉 실행시 실행시 동작한다!! 
				//main메서드 호출시 동작함!!
		for(int i=0;i<20;i++){
			y++;
		}
	}
	                              
	public static void main(String[] args){
		//System.out.println(x); //C 메모리 올라온적도 없고, static 영역에서 레퍼런스변수
		//없이 x를 접근할수도 없다...
		int a=new Desk2().x; //D
		System.out.println(a); //E
		System.out.println(y); //F 
		
	}
}