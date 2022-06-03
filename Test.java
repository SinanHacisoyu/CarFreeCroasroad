import javax.swing.JFrame;

//Sinan Hacisoyu

public class Test {
	
	public static void main(String[] args) {
		CarFreeCroasroad g = new CarFreeCroasroad();
		JFrame myFrame = new JFrame();
		new CarFreeCroasroad();
		myFrame.setVisible(true);//make this visible
		myFrame.setSize(800,800);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
		myFrame.add(g);
		myFrame.setResizable(false);
	}

}
