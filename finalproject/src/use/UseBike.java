package use;
import traffic.Bike; // traffic 이전 디렉토리 경로는 클래스패스에서 참조
class UseBike{
	public static void main(String[] args){
		Bike b= new Bike();

		b.run();
	}
}