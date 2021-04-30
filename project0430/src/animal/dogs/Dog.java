package animal.dogs;

public class Dog{
	String name;
	String color="brown";
	boolean curly=true;


	public Dog(String name){
		this.name=name;
	}

	public void bark(){
		System.out.println(name+"가 짖습니다.");
	}

	public void walk(){
		System.out.println(name+"가 산책합니다");
	}

}