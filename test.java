import java.io.*;

public class test{
	 public static void main(String[] args) throws IOException{
		FileReader fw=new FileReader("test.txt");
		int i=fw.read();
		System.out.println((char)i);
		
		fw.close();	
	}
}