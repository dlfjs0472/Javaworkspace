/*현실의 사물을 표현하기 위함이 아니라, 단지 실행부를 두기위한 Dummy 클래스*/

class UseDuck{
	public static void main(String[] args){
		//하드 디스크에 컴파일되어 있는 기계오리를 메모리에 살아 숨쉬게 해보자!!
		//즉 실행해서 메모리에 올리자!!
		int x=7;
		Duck d1=new Duck();

		// @naver.com 에서 @는 ~어디에 at 전치사
		System.out.println("d1이 담고있는 오리 인스턴스의 주소값은"+d1);

		//d1은 결국, 실제 오리 자체를 담고있는게 아니라, Heap영역에 생성된
		//오리 인스턴스의 주소값을 담고있다, 즉 오리를 가리키고 있다 하여
		//또한 오리를 참조하고 있다고 하여, reference 변수라 한다.

	}
}