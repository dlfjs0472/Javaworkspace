package app0513.paint;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ColorBox extends JPanel{

	public ColorBox(Color color) {
		this.setPreferredSize(new Dimension(50,50));
		this.setBackground(color);
	}
}
