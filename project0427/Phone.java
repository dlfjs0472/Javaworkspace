class Phone{   //멤버변수는 3개(static도 멤버), 멤버메서드는 1개(main은 실행부일뿐)
   int price=5000;
   String name="G21";
   static String company="Samsung";
         //static 인 '고정적인' 이란 뜻의 수정자로 불린다
         //static 으로 선언된 변수, 메서드는 new 할때
         //해당 인스턴스에 소속되지 않는다
         //따라서 원본 클래스에 존재하게 된다
         //따라서 static 으로 선언된 멤버변수를 특히나 클래스 변수라 한다
         //static이 있다면 전역뿐만 아니라 공유도 가능하다! ★

   public void ring(){
      System.out.println("벨이 울려요");
   }

   public static void main(String[] args){
      //String c=company; //클래스가 보유한 클래스변수는 이렇게 접근
      
      Phone p1 = new Phone();
      p1.company = "LG";

      company = "Motor";
 
      Phone p2 = new Phone();
      p2.company = "Sambo";

      System.out.println(p1.company);

      Phone p1 = new Phone;
   }

}