 

/**
 * 2d vector class
 */

public class Vec2 {
    double x,y;
    double len,dir;

    /**
     * constructor
     * @param px - initial x-coordinate
     * @param py - initial y-coordinate   
     */
    public Vec2(double px,double py)
    {
        this.x=px;
        this.y=py;
        this.dir=Math.atan2(py, px);
        this.len=this.length();
    }

    /**
     * creates a new instance and copies coordinates
     */
    public Vec2 clone()
    {
        Vec2 tv=new Vec2(this.x, this.y);
        return tv;
    }
    /**
     * adds a vector to current one 
     * @param pv - vector do be summed
     */
    public void Add(Vec2 pv)
    {
        this.x+=pv.x;
        this.y+=pv.y;
        this.dir=Math.atan2(this.y, this.x);
        this.len=this.length();
       // String tstr="add "+this.x+" "+this.y;
       // System.out.println(tstr);
    }

    /**
     * adds a vector to a current one and returns result in a new instance
     * @param pv - vector to be added
     * @return - new instance of vector
     */

    public Vec2 Plus(Vec2 pv)
    {
        return (new Vec2(this.x+pv.x,this.y+pv.y));
    }

    /**
     * substracts a vector from a current one and returns result in a new instance
     * @param pv - vector to be substracted
     * @return - new instance of vector
     */

    public Vec2 Minus(Vec2 pv)
    {
        return (new Vec2(this.x-pv.x,this.y-pv.y));
    }

    /**
     * converts a vector to a string
     * @param pident identation
     * @return result string
     */
    public String toStr(String pident)
    {
        String tstr=pident+"[ "+Double.toString(this.x)+","+Double.toString(this.y)+"]";
        return tstr;
    }
    /**
     * calculates vectors length
     * @return  calculated length
     */

    public double length()
    {        
        return Math.sqrt(this.x*this.x+this.y+this.y);
    }

    /** 
    public Vec2 mulfloat(float psk)
    {
        return new Vec2(this.x*psk, this.y*psk);
    }
*/
    /**
     * multiplies vector by a number
     * @param psk multiplier
     */
    public void multfloat(double psk)
    {
        this.x*=psk;
        this.y*=psk;
        this.dir=Math.atan2(this.y, this.y);
    }
    
    /**
     * rotates a vector by an angle of deltaa
     * @param deltaa - angle of rotation
     */
    public void rotate(double deltaa)
    {
        this.dir+=deltaa;
        if (this.dir>2.0*Math.PI)
        {
            this.dir-=2.0*Math.PI;
        }
        if (this.dir<0.0)
        {
            this.dir+=2.0*Math.PI;
        }
        this.x=this.len*Math.cos(this.dir);
        this.y=this.len*Math.sin(this.dir);
    }
}
