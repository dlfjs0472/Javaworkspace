//Account 와는 다른 패키지에서 접근 제한을 실습해본다!!
package use;
import bank.Account;

class Hacker{
	public static void main(String[] args){
		//은행계좌를 메모리에 올려놓고, 맘대로 장난쳐보자!!
		Account acc = new Account();


		//아래의 코드는 패키지 위치와 상관없이 balance변수 자체가 private이기 때문에
		//그 어떤 누구도 접근이 불가..
		//System.out.println("현재"+acc.master+"이 보유한 잔고는"+acc.balance);

		//은행명, 계좌주, 계좌번호 접근가능?
		System.out.println("은행명 : "+acc.bankName);
		System.out.println("계좌주 : "+acc.master);
		System.out.println("계좌번호 : "+acc.num);

		
	}

}