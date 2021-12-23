////////////////////////////////////////////////////////////////////////////////
// File:            CoolGuy.java
// Course:          CSC 232 A, Spring 2021
// Authors:         Hamza Sultan Khan Niazi
//
// Acknowledgments: None
//
// Online sources:  None
////////////////////////////////////////////////////////////////////////////////
package csc232;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
   A cool guy with ski glasses and a mustache. And he is sunburnt too.
*/
public class CoolGuy implements Image
{
  	@Override
	public void render(Graphics2D g) {
		Shape bigCircle = new Ellipse2D.Double(0.25,0.25,0.5,0.5);
		g.setColor(Color.RED);
		g.fill(bigCircle);
		Shape Curve = new Arc2D.Double(0.25,0.50,0.25,0.15,0,-180,1);
    Shape Curve1 = new Arc2D.Double(0.50,0.50,0.25,0.15,0,-180,1);
    g.setColor(Color.BLACK);
		g.fill(Curve);
    g.fill(Curve1);
		g.setColor(Color.YELLOW);
		Shape Curve2 = new Arc2D.Double(0.25,0.30,0.5,0.1,0,-180,1);
		g.fill(Curve2);
    g.setColor(Color.WHITE);
    Shape line = new Rectangle2D.Double(0.4875,0.46,0.025,0.1);
		g.fill(line);
	}
}