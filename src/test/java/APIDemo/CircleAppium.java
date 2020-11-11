package APIDemo;

import io.appium.java_client.TouchAction;

public class CircleAppium {

	public void SwipeArc(double centerX, double centerY, double radius, double startDegree, double degrees, int steps)
	{
	    //interpolate along the circumference of the circle
	    double angle = degrees / steps;
	    double prevX = centerX + radius * Math.cos(startDegree * Math.PI / 180F); ;
	    double prevY = centerY + radius * Math.sin(startDegree * Math.PI / 180F);

	    TouchAction circleTouch = new TouchAction(driver); //Your appium driver object here
	    circleTouch.Press(prevX, prevY);

	    for(int i = 1; i <= steps; ++i)
	    {
	        double newX = centerX + radius * Math.cos((startDegree + angle * i) * Math.PI / 180F);
	        double newY = centerY + radius * Math.sin((startDegree + angle * i) * Math.PI / 180F);

	        double difX = newX - prevX;
	        double difY = newY - prevY;
	        circleTouch.MoveTo(difX, difY);

	        prevX = newX;
	        prevY = newY;
	    }

	    circleTouch.Release();
	    circleTouch.Perform();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}
