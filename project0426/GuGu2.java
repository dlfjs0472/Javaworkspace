class GuGu2{
	public static void main(String[] args) {
		int gugu = Integer.parseInt(args[0]);
		System.out.println("당신이 자바실행시 넘긴 매개변수로 풀이할 구구단은"+args.length);
		for(int i=gugu; i>=2;i--){
			for(int j=1; j<=9; j++){
				System.out.println(i+"*"+j+"="+gugu*j);
			}
			
		}
	}
}
