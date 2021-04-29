package traffic;

class Car{
	//메서드명은 소중하다.. 직관성을 부여하기 위해 힘들게 찾아서 작명하게 된다
	//그런데 큰 차이가 별로 없는 상황임에도, 메서드명을 정할때 전혀 다른
	//단어를 사용해야 할까? java에서는 같은 메서드 명을 사용할 수 있다..
	//메서드명은 같지만, 완전히 일치하게 정의하는 것이 아니라, 매개변수의 자료형과
	//갯수를 다르게 하면 중복 정의로 간주하지 않겠다!!
	//오버로딩(overroading) - 메서드 중첩
	//즉 개발자가 비슷한 로직으로 메서드를 정의할 경우, 큰 차이가 없음에도 불구하고
	//메서드명을 전혀 다르게 줘야하는 불편함을 해소 할 수 있다..
	public void run(){//
		System.out.println("자동차가 달린다..");
	}
	public void run(int vel){//
		System.out.println("자동차가 쪼금 달린다..");
	}
	public void run(boolean flag){//
		System.out.println("..?");
	}
}