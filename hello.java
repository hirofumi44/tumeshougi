import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class hello {
    static int [][] Board=new int[8][8];
    public static void main(String args[] ) throws Exception {
      init();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String line= br.readLine();
      int t=Integer.parseInt(line);
      int turn;
      for(int i=0;i<t;i++) {
          String[] s = br.readLine().trim().split(" ");

          if(s[0].equals("B"))
              turn=1;
          else
              turn=-1;

          //printb();
          set(turn,Integer.parseInt(s[1])-1,Integer.parseInt(s[2])-1);


        }

      check();
      /*
        BufferedReader br = new BufferedReader[new InputStreamReader[System.in]];
        String line= br.readLine[];
        int n=Integer.parseInt[line];

        for[int i=0;i<n;i++]{
          String s = br.readLine[].trim[];
          int len=s.length[];
          String last=s.substring[len-1];
          String last1=s.substring[len-2,len-1];
          String last2=last1+last;


        }
        */


    }
    private static void printb(){
      System.out.println();
      for(int j=0;j<8;j++){
          for(int i=0;i<8;i++){
              if(Board[i][j]==-1)
                  System.out.print("W");
              else if(Board[i][j]==1)
                  System.out.print("B");
              else
                  System.out.print("E");
                }
          System.out.println();
        }
      System.out.println();

    }
    private static  void init(){
        Board[3][3]=Board[4][4]=-1;
        Board[4][3]=Board[3][4]=1;
    }
    private static void set(int turn,int x,int y){
        int [][] V={{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
        Board[x][y]=turn;
        int other=-turn;
        for(int[] ve :V){
            //#System.out.println( ve
            int i=ve[0],j=ve[1];
            //#System.out.println( i,j
            int cx=x,cy=y;
            int count=0;
            while(true){
              //  #System.out.println( Board[x][y-i]
                if(cx+i>=0 && cx+i<8 && cy+j>=0 && cy+j<8 && Board[cx+i][cy+j]==other){
                    count++;
                    cx+=i;
                    cy+=j;
                  }
                else
                    break;
                  }

            if(0<=cx+i && cx+i<8 && 0<=cy+j && cy+j<8 && Board[cx+i][cy+j]==turn && count>0){
                int sx=x;
                int sy=y;
                for(int z=0;z<count;z++){
                    sx+=i;
                    sy+=j;
                    Board[sx][sy]=turn;
                  }
                }
              }
            }
    private static void check(){
                int b=0,w=0;
                for(int i=0;i<8;i++)
                    for(int j=0;j<8;j++){
                        if(Board[i][j]==1)
                            b++;
                        else if(Board[i][j]==-1)
                            w++;
                          }

                System.out.println(String.format("%1$02d", b)+"-"+String.format("%1$02d", w));
                if(b>w)
                    System.out.println( "The black won!");
                else if(b<w)
                    System.out.println( "The white won!");
                else
                    System.out.println( "Draw!");
              }
}
