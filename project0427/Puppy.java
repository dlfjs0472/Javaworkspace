/*강아지를 정의한다*/

class Puppy{
	int age=5;
	boolean hasGuard; //멤버변수를 개발자가 초기화하지 않으면, 이 클래스로부터
								//메모리에 올라가는 인스턴스의 변수값에 디폴트값이 적용됨
								//by 컴파일러에 의해 초기화 된다..
								//논리값은 false, 정수 0 
								//이 원칙은 멤버변수에만 적용된다..즉 객체의 속성에 대해서만..
	
	public void setAge(){
		age=3;
		//아래의 k는 자동 초기화되지 않는다!!!! 왜? 멤버변수가 아니라서!!
		int k;
		System.out.println(k);
	}

	public static void main(String[] args){
		//강아지의 나이를 3세로 변경한 후, 나이를 출력해본다 
		//age=3;
		//System.out.println(age);
		int x=3;
		Puppy p = new Puppy(); //강아지 1마리의 인스턴스를 생성...
		//즉 위의 과제를 수행하려면, 생물학적으로 강아지가 태어나야 가능하다...
		p.setAge(); //나이 변경 메서드 호출
		System.out.println(p.age);

		
	}
}