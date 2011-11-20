import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

//300 pixels on the screen = 1 meter
//The ball is drawn every 0,025 second (40 times / 1 second)


public class BallGameField extends JPanel implements ActionListener{

	//constructor
	public BallGameField(){
		this.setPreferredSize(new Dimension(500, 400));
		timer = new Timer(timerInterval, this);
		timer.start();
	}
	
	//every time the timer "ticks", this method is called
	public void actionPerformed(ActionEvent e) {
		ball1.setBounds(getWidth(), getHeight());	//sets the bounds of the window
		ball1.moveBall();	//moves the ball
		repaint();			//this method indirectly calls paintComponent(Graphics g)
	}
	
	//this method is invoked every time an action is performed
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ball1.drawBall(g);
	}
	
	public int getTimerInterval(){
		return timerInterval;
	}
	
	private TestBall ball1 = new TestBall(100, 379, 7, 0, 8, 7);
	private int timerInterval = 25;	//time between calls to repaint
	private Timer timer;			//timer to animate one step
	
//	private Ball ball1 = new Ball(100, 100, 7, 1, 8, 7);
//	private int timerInterval = 25;	//time between calls to repaint
//	private Timer timer;			//timer to animate one step
}
