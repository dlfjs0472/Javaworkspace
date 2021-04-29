package bank;

class Enemy{

	public static void main(String[] args){
		//악의의 의도를 가지고 잔고를 감소
		Account acc = new Account();
		acc.balance=-10000000;
		System.out.println(acc.master+"의 잔고는"+acc.balance);

	}
}