import javax.swing.*;

public class MyFrame extends JFrame {

        

    MyPanel panel;
    MyFrame()
    {
        panel=new MyPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
       // this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    

    }
    
}
