class Phone{   //��������� 3��(static�� ���), ����޼���� 1��(main�� ������ϻ�)
   int price=5000;
   String name="G21";
   static String company="Samsung";
         //static �� '��������' �̶� ���� �����ڷ� �Ҹ���
         //static ���� ����� ����, �޼���� new �Ҷ�
         //�ش� �ν��Ͻ��� �Ҽӵ��� �ʴ´�
         //���� ���� Ŭ������ �����ϰ� �ȴ�
         //���� static ���� ����� ��������� Ư���� Ŭ���� ������ �Ѵ�
         //static�� �ִٸ� �����Ӹ� �ƴ϶� ������ �����ϴ�! ��

   public void ring(){
      System.out.println("���� �����");
   }

   public static void main(String[] args){
      //String c=company; //Ŭ������ ������ Ŭ���������� �̷��� ����
      
      Phone p1 = new Phone();
      p1.company = "LG";

      company = "Motor";
 
      Phone p2 = new Phone();
      p2.company = "Sambo";

      System.out.println(p1.company);

      Phone p1 = new Phone;
   }

}