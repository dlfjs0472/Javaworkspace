/*���ǿ� �������� �����غ���*/
	class Dog{
		/*�������� ���� �� �ִ� �Ӽ����� ������ ����
		�ϳ��� Ŭ������ ������ �������� ������ ��ü�� �ݿ��� ����, �Ӽ��� ǥ���ߴٰ� �Ͽ�
		property��(�Ӽ�) �Ѵ�...
		*/
		int age=160;
		String color="white";//js�� ���� ���ڿ��� ��ü�� ó���Ѵ�~~
		String name="Fluffy";

		//�Լ� �� �޼��� ����..js�� �ణ Ʋ��(��ü���� Ʋ�� ����)
		//public = ����������, void= �̸޼��尡 ��ȯ���� ����, �� ��ȯ���� ���� �Լ��� �ǹ�...
		public String getName(){ //String�� ��ȯ�ϴ� �޼���
			return name;
		}

		public int getAge(){ //int�� ��ȯ�ϴ� �޼���
			return age;
		}

		public void run(){
			System.out.println(name+"�� �پ��");
		}
	}

