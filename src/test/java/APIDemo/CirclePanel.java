package APIDemo;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CirclePanel extends JPanel
  {
//    public static void main(String[] args) throws Exception
//    {
//        JFrame f = new JFrame();
//
//        f.setContentPane(new CirclePanel());
//        f.setSize(500,500);
//        
//        f.setVisible(true);
//    }

    public void paint(Graphics g)
    {
       super.paint(g);
        
       
        //Draws the line
        g.drawArc(0,0,this.getWidth(), this.getHeight(),0,-360);
        g.drawArc(100,100,this.getWidth()/6, this.getHeight()/6,0,-360);
        g.drawArc(300,100,this.getWidth()/6, this.getHeight()/6,0,-360);
        g.drawArc(200,250,this.getWidth()/6, this.getHeight()/6,0,-360);
        
        //draws filled circle
        
       
    }
  }

