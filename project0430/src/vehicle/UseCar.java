package vehicle;

public class UseCar{
	public static void main(String[] args){
		Car car = new ElectCar();
		car.turnOnLight();
		car.tick();

		/*
		원칙)상속관계에 있는 인스턴스는 자료형을 기준으로 찾아가서 사용하면 된다..
			예)		Bird b = new Duck();
					b.로 접근할 수 있는 인스턴스는 Bird의 인스턴스를 접근할 수 있다..
		예외) 단, 자식이 부모의 메서드를 오버라이드 했을때는 레퍼런스 변수가 부모이건
				자식이건 무조건 업그레이드 된 메서드를 최우선으로 호출한다!!!
		*/


	}
}