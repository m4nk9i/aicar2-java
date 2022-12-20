import java.util.LinkedList;

public class Walls {
    LinkedList<Vec2[]> Segments;

    public Walls()
    {
        Segments= new LinkedList<Vec2[]>();
        Vec2[] tlis={new Vec2(1, 1),new Vec2(10, 10)};
        Segments.add(tlis.clone());

        tlis[0]=new Vec2(20, 20);
        tlis[1]=new Vec2(30, 30);
        Segments.add(tlis.clone());
   }
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


}
