package server;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerMainScreen extends JFrame {

	public ServerMainScreen() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(new Pannel1());
		this.add(new Pannel2());
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ServerMainScreen();
	}

}

class Pannel1 extends JPanel {
	public Pannel1() {
		this.add(new JLabel("panel 1"));
	}
}

class Pannel2 extends JPanel {
	public Pannel2() {
		this.add(new JLabel("panel 2"));
	}
}
