import java.awt.*;

public class TestBall {

	//class variables
	final static double GRAVITY = 9.82;
	
	//Constructor
	public TestBall(int posX, int posY, int dX, int dY, int weight, int bounceFactor){
		this.posX = posX;
		this.posY = posY;
		this.dX = dX;
		this.dY = dY;
		this.weight = weight;
		this.bounceFactor = bounceFactor;
		velX = 1;
		velY = 1;
		color = Color.BLACK;
		timeInAir = 0;
		diameter = 21; // 21 pixels on the screen = 7 cm. (300 pixels = 1 meter) 
	}

	public void moveBall(){

		
		//===========================================================================================
		//GRAVITATION X
		//===========================================================================================
		//slow the ball down according to it's weight and friction against a wall
		if(dY == 0){
			dX -= 0.05 * weight;
		}
		if(dY == 0 && dX == 1){
			dX = 0;
		}

		//===========================================================================================
		//GRAVITATION Y
		//===========================================================================================
		//the number of timer-calls the ball has spent in the air since its last stop
		timeInAir++;

		//the ball is now about to travel downwards, reset the timeInAir as gravity starts pulling
		//the ball down
		if(dY == 0 && posY != bottomWall){
			dY = 1;
			timeInAir = 0;
		}
		//if the ball hit the bottom wall, make it lose some energy depending on its material (bounceFactor)
		if(posY == bottomWall){
			//dY -= (int) (dY * bounceFactor * 0.1);
		}
		if(posY == bottomWall && dY > -5){
			dY = 0;
		}

		//apply gravity to the ball taking its weight and time in the air since last stop into consideration
		if(dY > 0){ //ball is falling DOWN
			dY += (int)((timeInAir * 0.005) * weight * GRAVITY);
		}
		if(dY < 0){ //ball is bouncing UP
			dY = dY + (int)((timeInAir * 0.05) * weight * GRAVITY);
		}

		//===========================================================================================
		//MOVE THE BALL
		//===========================================================================================
		//move the ball (dX * velocity) pixels on the x-axis and (dY * velocity) pixels on the y-axis
		posX += (dX * velX);
		posY += (dY * velY);
		
		//===========================================================================================
		//BOUNDRY CHECKS
		//===========================================================================================
		//check for collision on the left wall, set to opposite direction if wall is hit
		if (posX < 0){
			posX = 0;
			dX = -dX;
		}
		
		//check for collision on the right wall, set to opposite direction if wall is hit
		if (posX > rightWall){
			posX = rightWall;
			dX = -dX;
		}
		
		//check for collision on the top wall, set to opposite direction if wall is hit
		if (posY < 0){
			posY = 0;
			dY = -dY;
		}
		
		//check for collision on the bottom wall, set to opposite direction if wall is hit
		if (posY > bottomWall){
			posY = bottomWall;
			dY = -dY;
			timeInAir = 0; //resets the time the ball has been in the air since the last hit of the bottom wall
		}
	}
	
	public double getVelocityX(){
		return velX;
	}

	public void setVelocityX(int velX){
		this.velX = velX;
	}

	public double getVelocityY(){
		return velY;
	}

	public void setVelocityY(int velY){
		this.velY = velY;
	}

	public int getdX(){
		return dX;
	}

	public void setdX(int dX){
		this.dX = dX;
	}

	public int getdY(){
		return dY;
	}

	public void setdY(int dY){
		this.dY = dY;
	}

	public int getWeight(){
		return weight;
	}

	public void setweight(int weight){
		this.weight = weight;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setBounds(int width, int height){
		rightWall = width - diameter;
		bottomWall = height - diameter;
	}
	
	public void drawBall(Graphics g){
		//ruler
		int counter = 0;
		for (int i=0; i <= bottomWall+diameter; i++){
			counter++;
			if (counter == 10){
				g.drawLine(0, i, 5, i);
			}
			if (counter == 20){
				g.drawLine(0, i, 10, i);
				counter = 0;
			}
		}
		g.setColor(color);
		g.drawOval(posX, posY, diameter, diameter);
		g.drawString("dX="+dX, 35, 100);//DEBUG REMOVE THIS
		g.drawString("dY="+dY, 35, 120);//DEBUG REMOVE THIS
		g.drawString("posY="+posY, 35, 160);//DEBUG REMOVE THIS
		g.drawString("timeInAir="+timeInAir, 35, 180);//DEBUG REMOVE THIS
		g.drawString("bottomWall="+bottomWall, 35, 200);//DEBUG REMOVE THIS
	}
	
	//instance variables
	private int posX;			//x coordinate of the upper left corner of the ball
	private int posY;			//y coordinate of the upper left corner of the ball
	private int dX;				//number of pixels to move the ball on the x-axis every time moveBall is called
	private int dY;	   			//number of pixels to move the ball on the y-axis every time moveBall is called
	private int velX;			//the velocity of the ball in the X-axis
	private int velY;	   		//the velocity of the ball in the Y-axis
	private int weight;	   		//the weight of the ball
	private int bounceFactor;	//the energy the ball will save thanks to its material. 1=least bouncy, 10=most bouncy
	private Color color;		//the color of the ball
	private int frict;	   		//the friction of the ball
	private int timeInAir;		//the amount of timer-calls the ball spent in the air since start or last hit in the bottom
	private int diameter;		//the diameter of the ball
	private int rightWall;		//the right window bound for the ball 
	private int bottomWall;		//the bottom window bound for the ball
}
