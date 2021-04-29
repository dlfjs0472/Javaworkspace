package traffic;
class Bus{
	String color;
	String seat;
	boolean camera;
	

	//생성자로 버스를 초기화 해보자
	public Bus(){
		color="blue";
		seat="가죽시트";
		camera=true;
	}

	//일반 메서드로 버스를 초기화 해보자
	public void init(){
		color="red";
		seat="인조시트";
		camera=false;
	}

	public static void main(String[] args){
		Bus b = new Bus();
		b.init();
		
	}


}