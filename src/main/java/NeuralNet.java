package src.main.java;
import java.lang.Float;
import java.awt.*;

/**
 * class of neural network
 */

public class NeuralNet {
    float[] l_layer0,l_layer1,l_layer2;
    float[][] l_conn01,l_conn12;

    /**
     * class constructor     
     * @param pl0 number of 0th layer neurons (sensors)
     * @param pl1 number of 1st layer neurons
     * @param pl2 number of 2nd layer neurons (actuators)
     */
    public NeuralNet(int pl0,int pl1, int pl2)
    {
        l_layer0=new float[pl0];
        l_layer1=new float[pl1];
        l_layer2=new float[pl2];
        l_conn01=new float[pl0][pl1];
        l_conn12=new float[pl1][pl2];

        for(int i=0;i<pl1;i++)
        {
            l_layer1[i]=(float)(0.0);
            for (int j=0;j<pl0;j++)
            {
                l_conn01[j][i]=(float)0.0;
            }
            for (int j=0;j<pl2;j++)
            {
                l_conn12[i][j]=(float)0.0;
            }
        }

        for(int i=0;i<pl0;i++)
        {
            l_layer0[i]=(float)0.0;
        }

        for (int i=0;i<pl2;i++)
        {
            l_layer2[i]=(float)0.0;
        }

        //initial node connections
        this.l_layer0[0]=1.0f;
        this.l_conn01[0][0]=1.0f;
        this.l_conn12[0][0]=1.0f;
        this.l_conn12[0][1]=0.5f;
        
    }

    /**
     * calculate new state of layers for new state of inpun neurons
     */

    public void CalculateLayers()
    {
        for (int i=0;i<this.l_layer1.length;i++)
        {
            this.l_layer1[i]=0.0f;
            for (int j=0;j<this.l_layer0.length;j++)
            {
                this.l_layer1[i]+=this.l_layer0[j]*this.l_conn01[j][i];
            }
        }
        for (int i=0;i<this.l_layer2.length;i++)
        {
            this.l_layer2[i]=0.0f;
            for (int j=0;j<this.l_layer1.length;j++)
            {
                this.l_layer2[i]+=this.l_layer1[j]*this.l_conn12[j][i];
            }
        }

    }

    /**
     * converts current state of neurons to a string
     * @param pident identation
     * @return result string
     */

    public String toString(String pident)
    {
        String tstr=pident+" layer 0: [ ";
        //tstr+=l_layer0;
         
        for (int i=0;i<this.l_layer0.length;i++)
        {   
            tstr+=Float.toString(l_layer0[i])+" ";
        }
        tstr+="]\n";
        
        tstr+=pident+" layer 1: [ ";
        for (int i=0;i<this.l_layer1.length;i++)
        {   
            tstr+=Float.toString(l_layer1[i])+" ";
        }
        tstr+="]\n";
        
        tstr+=pident+" layer 2: [ ";
        for (int i=0;i<this.l_layer2.length;i++)
        {   
            tstr+=Float.toString(l_layer2[i])+" ";
        }
        tstr+="]\n";

        tstr+=pident+" connections 0-1: [\n";
        for (int i=0;i<this.l_layer0.length;i++)
        {
            tstr+=pident+"[ ";

            for (int j=0;j<this.l_layer1.length;j++)
            {   
                tstr+=Float.toString(this.l_conn01[i][j])+" ";
            }
            tstr+="]\n";
        }
        tstr+=pident+"]\n";
       
        tstr+=pident+" connections 1-2: [\n";
        for (int i=0;i<this.l_layer1.length;i++)
        {
            tstr+=pident+"[ ";

            for (int j=0;j<this.l_layer2.length;j++)
            {   
                tstr+=Float.toString(this.l_conn12[i][j])+" ";
            }
            tstr+="]\n";
        }
        tstr+=pident+"]\n";
       
        
        return tstr;
    }

    /**
     * clones current network to a new
     */

    public NeuralNet clone()
    {
        NeuralNet tnet;
        tnet=new NeuralNet(this.l_layer0.length, this.l_layer1.length, this.l_layer2.length);
        tnet.l_conn01=this.l_conn01.clone();
        tnet.l_conn12=this.l_conn12.clone();
        tnet.l_layer0=this.l_layer0.clone();
        tnet.l_layer1=this.l_layer1.clone();
        tnet.l_layer2=this.l_layer2.clone();
        return tnet;
    }

    /**
     * method to draw current neural net map
     * @param gr2D Graphics2D context
     */
    public void paint(Graphics2D gr2D)
    {

    }

}
