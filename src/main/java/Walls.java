package src.main.java;
import java.util.LinkedList;
import java.awt.*;


/**
 * Walls class
 */

public class Walls {
    LinkedList<Vec2[]> Segments;

    /**
     * constructor
     */
    public Walls()
    {
        Segments= new LinkedList<Vec2[]>();
        
    }

    /**
     * draws a wall segment
     * @param gr2D - Graphics2D context
     */

    public void draw(Graphics2D gr2D)
    {
        gr2D.setPaint(Color.red);
        gr2D.setStroke(new BasicStroke(2));
        for (int i=0;i<this.Segments.size();i++)
        {
            gr2D.drawLine((int)(this.Segments.get(i)[0].x),(int)(this.Segments.get(i)[0].y),
            (int)(this.Segments.get(i)[1].x),(int)(this.Segments.get(i)[1].y));
        }
   }

   /**
    * converts a wall segment to a string   
    * @param pident indentation
    * @return
    */
   public String toStr(String pident)
   {
        Vec2 tv1,tv2;
        Vec2[] tt;
        String tstr="";
        for (int i=0;i<this.Segments.size();i++)
        {
            tt=this.Segments.get(i);
            tv1=tt[0];
            tv2=tt[1];
            tstr+=pident+" - "+tv1.toStr(" ")+" "+tv2.toStr(" ")+"\n";

        }
        return tstr;
   }
   /**
    * converts an JSON string to wall segments TODO: fix this method completly
    * @param pstr - JSON string of segments' coordinates
    */

   public void parseJSON(String pstr)
   {
        Vec2[] tlis={new Vec2(100, 100),new Vec2(10, 10)};;
        String tstr="";
        tstr=pstr.substring(pstr.indexOf("[")+1, pstr.lastIndexOf("]")-1);

        while(tstr.length()>0)
        {
            int i1=tstr.indexOf("[");
            int i2=tstr.indexOf("]");
            String[] astr={"0","0","1","0"};
            try
            {
                astr=tstr.substring(i1+1, i2).split(",");
                tstr=tstr.substring(i2+1);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println("index out");
                tstr="";
            }
            finally
            {
                tlis[0]=new Vec2(Double.parseDouble(astr[0]),Double.parseDouble(astr[1]));
                tlis[1]=new Vec2(Double.parseDouble(astr[2]),Double.parseDouble(astr[3]));
            }
            //System.out.println(astr[0]+" && "+astr[1]);
            System.out.println(tlis[0].toStr("")+" ^ "+tlis[1].toStr(""));
            this.Segments.add(tlis.clone());
            
            /*for (String a : astr)
            System.out.println(a);
    */
        }

        System.out.println(tstr);
   }

}
