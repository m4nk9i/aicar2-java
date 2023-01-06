import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

/**
 * MyPanel class 
 * inherited from JPanel
 * uses ActionListener interface
 */

public class MyPanel extends JPanel implements ActionListener {
    String rstr;
    AICar icar;
    Walls walls;
    Timer timer;

    /**
     *  constructor
    */

    MyPanel()
    {
        String JSONstr="";
        timer = new Timer(10, this);
		timer.start();

        try{
            JSONstr = new String(Files.readAllBytes(Paths.get("walls1.json")));
        } 
        catch(IOException e)
        {}
        finally
        {
            walls=new Walls();
            walls.parseJSON(JSONstr);
        }
        System.out.println(walls.toStr("%%   "));
        icar=new AICar();
       // icar.gr2d=(Graphics2D)g;
        icar.Calculate(1.0f);

        this.setPreferredSize(new Dimension(800,500));

    }

    /**
     * repaints panel surface
     * walls,cars
     */
    public void paint(Graphics g)
    {
        Graphics2D g2D= (Graphics2D) g;
        super.paint(g);
       /*  
       g2D.setPaint(Color.blue);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(0, 0, 500, 500);
        */
        icar.Calculate(0.5);
        icar.paint(g2D);
        walls.draw(g2D);
    }

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
