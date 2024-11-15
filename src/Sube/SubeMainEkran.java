package Sube;

import javax.swing.*;

public class SubeMainEkran {
	
	JFrame MainFrame;
	public SubeMainEkran() {
		Initializer();
	}
	
	public void Initializer() {
		MainFrame = new JFrame();
		MainFrame.setSize(1200,1200);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void ShowFrame() {
		MainFrame.setVisible(true);
	}
}
