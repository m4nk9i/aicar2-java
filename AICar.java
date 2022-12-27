import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * AICar class 
 * artificial intelligence car class
 */


public class AICar {

    NeuralNet nnet;
    Vec2 pos;
    double dir;


    /**
     *   constructor
     */
    public AICar()
    {
        this.nnet=new NeuralNet(4, 10, 4);
        this.pos=new Vec2(0, 0);
        this.dir=0.75*Math.PI;
    }
    /**
     * Calculates current state of a car
     * 
     * @param dt -time step
     */
    public void Calculate(double dt)
    {
        Vec2 dv=new Vec2(Math.cos(this.dir), Math.sin(this.dir));
        //String tstr=dv.toString("");
        //System.out.println(tstr);
        this.nnet.CalculateLayers();
        this.pos.Add(dv);
       // this.dir+=0.01;
    }

    /**
     * clones a car object
     */
    public AICar clone()
    {
        AICar tcar=new AICar();
        tcar.nnet=this.nnet.clone();
        return tcar;
    }

    /**
     * method of drawing the car
     * @param gr2D - Graphics2D context
     */

    public void paint(Graphics2D gr2D)
    {
        gr2D.setPaint(Color.blue);
        gr2D.setStroke(new BasicStroke(2));
        AffineTransform Tx=gr2D.getTransform();
        gr2D.translate(100, 100);
       
        gr2D.translate(this.pos.x, this.pos.y);
        gr2D.rotate(this.dir); 
        gr2D.drawRect(-10, -5, 20, 10);
        gr2D.setTransform(Tx);
        //gr2D.drawLine(-5, 0, 500, 500);
    }

    /**
     * writes car parameters and state to a string
     * @param pident - indentation
     * @return result string
     */

    public String toString(String pident)
    {
        String tstr="";
        tstr+=pident+"AICar\n";
        tstr+=this.nnet.toString(pident+"   ");
        tstr+=this.pos.toStr(pident+"   ");
        return tstr;
    }
}
