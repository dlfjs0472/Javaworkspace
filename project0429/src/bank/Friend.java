package bank;
class Friend{
	public static  void main(String[] args){
		Account acc = new Account();
		System.out.println("친구의 조작 전 잔고는 "+acc.getBalance());
		//acc.balance=acc.balance+8000;
		//변수에 대한, 직접접근을 하지말고, 메서드 호출로 데이터를 변경해보자
		acc.setBalance(150000);
	
		System.out.println("친구의 조작 후 잔고는 "+acc.getBalance());


		//현재 Friend 클래스는 Account와 같은 패키지에 있으므로, Account가 보유한
		//default 접근제한이 걸려 있는 모든 변수에 맘대로 접근이 가능하다!!!
		System.out.println("은행명 : "+acc.bankName);
		System.out.println("계좌주 : "+acc.master);
		System.out.println("계좌번호 : "+acc.num);
	}	
}