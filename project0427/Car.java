/*자동차를 정의해본다*/
class Car{
	String name="벤츠";
	int price=9000;
	String color="silver";

	public void setPrice(int price){
		this.price=price;
	}

	public static void main(String[] args){
		Car c1 = new Car();
		Car c2 = new Car();
		Car c3 = c2;


		c1.setPrice(8000);
		System.out.println(c1.price);
		System.out.println(c2.price);
		c2.setPrice(7000); //c2가 가리키고 있었던 자동차와 동일한 위치의 차를 가리키므로
		//즉 두번째 자동차의 가격을 7000으로 변경하게됨
		System.out.println(c2.price);
		System.out.println(c3.price);

	}
}