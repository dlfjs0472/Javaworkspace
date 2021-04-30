package race;

class UseHuman{
	public static void main(String[] args){
		Black b = new Black();
		//b.age=7; //존재하지 않는 변수라서 컴파일 에러
		//b.IQ=110; // 단지 Black 클래스에 존재하지 않을뿐이지,
		//이미 부모 클래스에 존재하기 때문에, 가지고 있는것과 같다 에러X

		//상속관계에서 주의할점!! 부모의 코드가 자식 클래스에 붙여넣기가 되는게 아니라,
		//단지 서로 다른 인스턴스 영역을 가진 상태에서, 자식의 인스턴스가 부모의 인스턴스 영역을
		//마치 자기것 처럼 접근할 수 있는, 즉 영역을 확장한 것 뿐이다...
		//따라서 부모와 자식은 자신만의 변수를 가질 수 있고, 이때 변수명이 동일할 수도 있다.
		b.speak();
		b.drive();
		System.out.println(b.IQ);
	}
}