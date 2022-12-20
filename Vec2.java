 

public class Vec2 {
    double x,y;
    double len,dir;

    public Vec2(double px,double py)
    {
        this.x=px;
        this.y=py;
        this.dir=Math.atan2(py, px);
        this.len=this.length();
    }

    public Vec2 clone()
    {
        Vec2 tv=new Vec2(this.x, this.y);
        return tv;
    }
/** 
    public Vec2 Add(Vec2 pv)
    {   
        Vec2 tv=new Vec2(this.x+pv.x, this.y+pv.y); 
        return tv;
    }
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

    public Vec2 Plus(Vec2 pv)
    {
        return (new Vec2(this.x+pv.x,this.y+pv.y));
    }


    public Vec2 Minus(Vec2 pv)
    {
        return (new Vec2(this.x-pv.x,this.y-pv.y));
    }

    public String toStr(String pident)
    {
        String tstr=pident+"[ "+Double.toString(this.x)+","+Double.toString(this.y)+"]";
        return tstr;
    }

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
    public void multfloat(double psk)
    {
        this.x*=psk;
        this.y*=psk;
        this.dir=Math.atan2(this.y, this.y);
    }

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
