package app0512.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas{
	MoveTest moveTest; // null
	
	//이 생성자를 호출하는 者는 , MoveTest 의 주소값을
	//인수로 넘겨야 한다!!
	//이렇게하면, MyCanvas는 태어날때부터 MoveTest의
	//주소값을 보유하게 된다..
	public MyCanvas(MoveTest moveTest) {
		this.moveTest=moveTest;
	}
	
	/*켄버스에 그려질 그림을 개발자가 주도하여, 원하는 
	그림을 그려보자!!
	paint 메서드에 대하여....
	특징: 개발자가 paint메서드를 호출해서는 안된다!!!
	      왜??  자바의 모든 컴포넌트는 스스로를 그린다..즉 paint메서드는 시스템이 그래픽의 렌더링시
	      필요에 의해 호출하게 됨..
	      언제호출됨?  처음의 그림과 비교해서 바뀐부분이 있다면, 시스템이 알아서 다시 호출 
	      동작원리: 사실 다시 그리는과정은 내부적으로 아래의 절차를 거친다. 
	                 1.기존의 그림을 지운다 (update메서드) 
	                 2.그림을 그린다. (paint메서드)
	      문제점?  사용자가 윈도우창의 크기를 변화시킨다거나, 하는 그래픽상의 변화를 통해 paint()
	      메서드를 호출하는게 아니라, 개발자가 원하는 시점에 프로그래밍적으로 paint()를 호출하는
	      방법은 없을까?  ex) 버튼을 누를때 그림을 다시 그리게...
	      sun 에서는 그래픽이 화면에 반영되는 처리는 시스템이 하기 때문에 paint()메서드 호출을
	      원칙적으로 막아놓았다, 따라서 개발자는 시스템에게 paint()가 호출될 수 있도록 간접적으로
	      요청을 하는 방식으로 그래픽처리를 할 수 있다..이때 호출해야 할 메서드가 바로 repaint() 
	      repaint() 그래픽의 갱신을 요청하게 되고, 이때 이 요청이 들어오면 update() 가 호출되어
	      화면을 지우며, 화면을 지우고나서 깨끗해지면 paint() 
	      repaint() --> update()  --> paint()
	*/
	
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);//이 시점부터는, 노란색으로 그려진다...
		g.fillOval(moveTest.x, 100, 100, 100);
	}
}
