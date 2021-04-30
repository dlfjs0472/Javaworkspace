package vehicle;

//전기차를 정의한다
public class ElectCar extends Car{

	//부모의 메서드 즉 로직이 현 시점에 적절치 않을 경우
	//자식은 부모의 메서드를 재정의 할 수 있는데, 이러한 부모메서드
	//재정의 기법을 가리켜 오버라이딩(overriding)이라 한다.
	//주의할점) 오버라이딩은 부모의 메서드와 완전일치해야 한다
	//만일 다른 메서드명으로 갈 경우, 그건 업그레이드가 아닌
	//새로운 메서드 정의가 되어 버린다..
	public void tick(){
		System.out.println("전기동력엔진이 가동중입니다!!");
	}

	public void stop(){
		System.out.println("전기동력엔진이 가동을 멉춥니다!!");
	}
}