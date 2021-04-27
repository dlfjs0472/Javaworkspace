/*현실에 강아지를 정의해보자*/
	class Dog{
		/*강아지가 가질 수 있는 속성들을 변수로 선언
		하나의 클래스가 보유한 변수들은 현실의 객체를 반영한 상태, 속성을 표현했다고 하여
		property라(속성) 한다...
		*/
		int age=160;
		String color="white";//js와 같이 문자열을 객체로 처리한다~~
		String name="Fluffy";

		//함수 즉 메서드 정의..js와 약간 틀림(전체적인 틀은 같다)
		//public = 접근제한자, void= 이메서드가 반환값이 없다, 즉 반환값이 없는 함수를 의미...
		public String getName(){ //String을 반환하는 메서드
			return name;
		}

		public int getAge(){ //int를 반환하는 메서드
			return age;
		}

		public void run(){
			System.out.println(name+"가 뛰어요");
		}
	}

