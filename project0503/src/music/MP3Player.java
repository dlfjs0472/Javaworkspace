package music;
//볼륨 조절, mp3파일 지원...

/*
추상클래스를 상속받게 되는 자식은, 부모가 불완전하게 남겨놓은 빚더미인 불완전 메서드를
완성할 의무를 가지게 된다.. 구현강제된다..
즉 부모세대에서 완성하지 못한 것을 자식 세대에서 완성할 것을 떠넘긴 상태이다!!
결론) 추상클래스를 상속받은 자식은 부모가 완성하지 못한 추상메서드를 오버라이딩 해야한다.
*/
import riding.Roller;
import aircraft.ZetWing;

//자바에서는 다중상속을 금지하고 있다..즉 자식 클래스가 특정 클래스를 부모로 둔다면,
//더이상 다른 클래스를 추가해서 부모로 지정할 수 없다.. 즉 직계부모는 하나이다!!!
public class MP3Player extends MusicPlayer implements Roller, ZetWing{
								 /*is a*/
	public void setVolume(){
		System.out.println("MP3플레이어의 볼륨을 조절합니다.");
	}

	public void openMP3(){
		System.out.println("MP3파일을 지원합니다.");
	}

	public void fly(){
		System.out.println("음악을 들으며 하늘을 날아갑니다.");
	}
	public void roll(){
		System.out.println("롤링기능을 지원합니다..");
	}

}