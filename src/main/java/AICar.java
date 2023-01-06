package src.main.java;
import java.awt.*;
import java.awt.geom.AffineTransform;


/**
 * AICar class 
 * artificial intelligence car class
 */


public class AICar {

    NeuralNet nnet;
    Vec2 pos;
    double dir,omega;
    Vec2[] a_sensors;
    double a1,a2;
    float sens_range;
    double e_throttle,e_brakes,e_steering;
    double e_speed,e_max_torque,e_max_angle;
    double e_wheelbase=5.0f;
    double clock=0.0f;


    /**
     *   constructor
     */
    public AICar()
    {
        this.e_max_angle=20.0f*Math.PI/180.0f;
        this.sens_range=50;
        this.a1=10.0f*(float)Math.PI/180.0f;
        this.a2=45.0f*(float)Math.PI/180.0f;
        this.nnet=new NeuralNet(4, 10, 4);
        this.pos=new Vec2(0, 0);
        this.dir=60.0*Math.PI/180.0f;
        this.a_sensors=new Vec2[5];
        for (int i=0;i<5;i++)
        {
            this.a_sensors[i]=new Vec2(10,0);
        }
        
    }
    /**
     * Calculates current state of a car
     * 
     * @param dt -time step
     */
    public void Calculate(double dt)
    {
        this.clock+=dt*0.01;
        this.e_throttle=this.nnet.l_layer2[0]*this.e_max_torque;
        this.e_brakes=this.nnet.l_layer2[1];
        this.e_steering=(this.nnet.l_layer2[2]-0.5)*this.e_max_angle;
        this.e_steering=this.e_max_angle;
        this.e_steering=this.e_max_angle*Math.sin(this.clock);
        this.e_speed=1.0;

        this.omega=this.e_speed*Math.sin(this.e_steering)/this.e_wheelbase;
        //System.out.println(this.omega*dt+" "+this.dir);
        this.dir+=(this.omega*dt);
        //System.out.println(this.dir);
        Vec2 dv=new Vec2(this.e_speed*dt*Math.cos(this.dir), this.e_speed*dt*Math.sin(this.dir));
        //String tstr=dv.toString("");
        //System.out.println(tstr);
        this.nnet.CalculateLayers();
        this.pos.Add(dv);
       // this.dir+=0.01;
       this.a_sensors[0].setAngLen((float)(this.dir-this.a1), this.sens_range);
       this.a_sensors[1].setAngLen((float)(this.dir-this.a2), this.sens_range);
       this.a_sensors[2].setAngLen((float)this.dir, this.sens_range);
       this.a_sensors[3].setAngLen((float)(this.dir+this.a2), this.sens_range);
       this.a_sensors[4].setAngLen((float)(this.dir+this.a1), this.sens_range);


    
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
        this.paintInfo(gr2D);
        gr2D.setPaint(Color.blue);
        gr2D.setStroke(new BasicStroke(2));
        AffineTransform Tx=gr2D.getTransform();
        gr2D.translate(100, 100);
       
        gr2D.translate(this.pos.x, this.pos.y);
        gr2D.rotate(this.dir);
        gr2D.translate(0,this.e_wheelbase); 
        gr2D.drawRect(-10, -5, 20, 10);
        gr2D.setStroke(new BasicStroke(1));
        gr2D.setPaint(Color.gray);
        gr2D.drawLine(0, 0, (int)(this.sens_range*Math.cos(this.a1)),(int)(this.sens_range*Math.sin(this.a1)));
        gr2D.drawLine(0, 0, (int)(this.sens_range*Math.cos(this.a2)),(int)(this.sens_range*Math.sin(this.a2)));
        gr2D.drawLine(0, 0, (int)this.sens_range,0);
        gr2D.drawLine(0, 0, (int)(this.sens_range*Math.cos(this.a1)),(int)(this.sens_range*Math.sin(-this.a1)));
        gr2D.drawLine(0, 0, (int)(this.sens_range*Math.cos(this.a2)),(int)(this.sens_range*Math.sin(-this.a2)));
        gr2D.setPaint(Color.green);
        gr2D.drawLine(0,0,(int)(20.0*Math.cos(this.e_steering)),(int)(20.0*Math.sin(this.e_steering)));
        //    gr2D.drawLine(0, 0, (int)(this.sens_range*Math.sin(this.a2)),(int)(this.sens_range*Math.cos(this.a2)));
    //    gr2D.drawLine(0, 0, (int)(this.sens_range*Math.sin(this.a1)),(int)(this.sens_range*Math.cos(this.a1)));




        gr2D.setTransform(Tx);
        //gr2D.drawLine(-5, 0, 500, 500);
    }

    public void paintInfo(Graphics2D gr2D)
    {
        gr2D.drawString(this.toStr(""), 10, 10);
    }

    /**
     * writes car parameters and state to a string
     * @param pident - indentation
     * @return result string
     */

    public String toStr(String pident)
    {
        String tstr="";
        tstr+=pident+"AICar\r\n\r";
        //tstr+=this.nnet.toString(pident+"   ");
        tstr+=this.pos.toStr(pident+"   ");
        tstr+=this.dir+"  "+this.omega;
        return tstr;
    }
}
