import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {
    JButton but1,but2;
    JSlider sli1;
    NeuralNet nnet;
    String rstr;
    AICar icar;
    Timer timer;
    MyPanel()
    {
        timer = new Timer(10, this);
		timer.start();
        icar=new AICar();
       // icar.gr2d=(Graphics2D)g;
        icar.Calculate(0.0);

        nnet=new NeuralNet(10,10,4);
        nnet.CalculateLayers();
        String rstr=nnet.toString("* ");
        //Console console = System.console();
        System.out.printf(rstr);

        but1=new JButton("ABC");
        but2=new JButton("XYZ");
        sli1 = new JSlider(JSlider.HORIZONTAL,
                                      1, 100, 33);
      //  sli1.addChangeListener(this);
        but1.setLocation(10, 10);
        but2.setLocation(60, 10);
        but1.setVisible(true);
        but2.setVisible(true);
        //but1.setLocationRelativeTo(null);
        but1.setBounds(50,100,95,30);
        this.add(but1);
        this.add(but2);
        this.add(sli1); 
        this.setPreferredSize(new Dimension(500,500));

    }
    /* 
    public void stateChanged(ChangeEvent e)
    {
        JSlider source = (JSlider)e.getSource();
        
    }
    */
    public void paint(Graphics g)
    {
        Graphics2D g2D= (Graphics2D) g;
        g2D.setPaint(Color.blue);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(0, 0, 500, 500);
        icar.Calculate(0.0);
        icar.paint(g2D);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
