import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main extends JFrame{
  public  Main(){

    setSize(400,300);
    
    setTitle("shougi");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args){
    JFrame w=new Main();
    w.show();
  }
}
