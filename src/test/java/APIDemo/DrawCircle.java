package APIDemo;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class DrawCircle {

	public void drawCircle (AppiumDriver driver, Point origin, double radius, int steps) {
		  Point firstPoint = getPointOnCircle(0, steps, origin, radius);
		  PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		  Sequence circle = new Sequence(finger, 0);
		  circle.addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), firstPoint.x, firstPoint.y));
		  circle.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
		  for (int i = 1; i < steps + 1; i++) {
		    Point point = getPointOnCircle(i, steps, origin, radius);
		    circle.addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), point.x, point.y));
		  }
		  circle.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		  driver.perform(Arrays.asList(circle));
		}

	
	}

	


}
