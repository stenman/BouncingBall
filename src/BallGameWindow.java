import javax.swing.JFrame;

public class BallGameWindow extends JFrame {
	
	public BallGameWindow(){
		ballGameField = new BallGameField();
		add(ballGameField);
		pack();
		setVisible(true);
	}
	
	public static void main(String args[]){
		new BallGameWindow();
	}
	
	BallGameField ballGameField;
}
