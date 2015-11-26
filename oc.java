//package ocero;

import java.util.Scanner;
import java.util.Stack;

public class board {
    static int[][] board = new int[8][8];
    private final static int d[][]={{-1,-1,-1,0,0,1,1,1},{-1,0,1,-1,1,-1,0,1}};//方向ベクトル
    private final static int SQUARE_EMPTY = 0;
    private final static int SQUARE_MINE = 1;
    private final static int SQUARE_OPPONENT = -1;
   static int SIZE=6;
    static int turn=1;
    static Stack<Integer> stack =new Stack<Integer>();

    public board ( ) {
        for (int i = 0 ; i < SIZE; i++) {
            for (int j= 0 ; j < SIZE ; j++) {
                board[i][j] = SQUARE_EMPTY;
            }
        }
        	board[SIZE/2-1][SIZE/2-1]=board[SIZE/2][SIZE/2]=SQUARE_MINE;
        	board[SIZE/2-1][SIZE/2]=board[SIZE/2][SIZE/2-1]=SQUARE_OPPONENT;
    }
    static void prints(){
    		//System.out.println("size"+stack.size());
    	for(int v:stack)
    		System.out.println(v);
    }
   static void printb(){ //表示
    	for(int j=0;j<SIZE;j++)
    		{
    			for(int i=0;i<SIZE;i++)
    				{
    					if(board[i][j]==SQUARE_MINE)
    						System.out.print("●");
    					else if(board[i][j]==SQUARE_OPPONENT)
    						System.out.print("○");
    					else
    						System.out.print("□");
    				}
    			System.out.println();
    		}
    }
    int jud()
    {
        int i,j,a=0,t=-1;
        for(i=0;i<SIZE;i++)
            for(j=0;j<SIZE;j++)
                a+=board[i][j];
        if(a>0)
        {
            t=1;
            System.out.println("黒の勝ち");
        }
        else if(a==0)
        {
            t=0;
            System.out.println("引き分け");

        }
        else
            System.out.println("白の勝ち");
        return(t);
    }

   static boolean res(int i,int j,boolean b,boolean s) //i,jに着手可能か？ b 書き込むか　c　スタックに入れるか
    {
        int x,f=0,y,e=0,sum=0;//左上２　上３　右上４　左５　右６　左下７　下８　右下９
       // int r[]=new int[8];
        boolean a=false;

    //    System.out.println(turn+"teban can?"+i+ " "+j);
        if(board[i][j]==0)
        {
            for(int c=0;c<8;c++)
            {
                x=i;
                y=j;
                f=0;

                while(true)
                {
                    x+=d[0][c];
                    y+=d[1][c];
                   // System.out.print(x+" "+y +" を検索");
                    if((x<0)||(x>SIZE-1)||(y<0)||(y>SIZE-1)||board[x][y]==0)
                    {
                        //r[c]=0; //こっち方向はだめ。
                        e=0;
                       // System.out.println("A");
                        break;

                    }

                    else if(board[x][y]==turn)
                    {
                        if(e>0){
                            //r[x]++;
                        	//System.out.println("set"+i+" "+j);
                        	sum+=e;//ひっくり返す石の総計
                        	if(b==true)
                        		while(e-->=0)//e個ひっくり返す
                        		{
                        			x-=d[0][c];
                        			y-=d[1][c];
                        			board[x][y]=turn;
                        			if(s==true)
                        				stack.push(10*x+y);

                        		}
                        	//turn*=-1;
                            a=true;
                        }
                      //  System.out.println("B"+e);
                          break;
                    }

                    else{
                        //r[c]++;
                    	e++;
                        f=1;
                      // System.out.println("C"+e);
                    }
                }

            }
            if(s==true)
            	stack.push(sum);
        }
        //for(x=0;x<10;x++)

        //printf("% d",a);
        //else
        //printf("not set\n");
       // if(a!=1)
        //	r[0]=-1;

       // for(int b:r)
        //	System.out.print(b);
    //    System.out.println();

        return(a);

    }
   /* void rev(int r[],int i,int j)
    {
        int x,y,t=0;
        board[i][j]=turn;
        //Qu[q++]=C*(10*r[0]+r[1]);
        for(int c=0;c<8;c++)
        {
            x=i;
            y=j;
            t+=r[c];

            while(r[c]-->0){

                x+=d[0][c];

                y+=d[1][c];

                board[x][y]=turn;

            }

        }
           turn*=-1;
    }*/
    int ser()
    {
        int x,y,i=0;
       // int r[]=new int[8];
        for(x=0;x<SIZE;x++)
        {
            for(y=0;y<SIZE;y++)
            {
               //r=res(x,y,false);
                if(res(x,y,false,false)==true)//置ける場合
                {
                    i++;
                  //  System.out.println("you can set "+x+" "+y);
                }
            }
        }
        return(i);
    }

  static  void ctrz()//１手前に戻す
    {
        int a=stack.pop();
        int z=stack.pop();
        board[z/10][z%10]=SQUARE_EMPTY;
        while(a-->0){
        	int s=stack.pop();
        	board[s/10][s%10]=turn;
        }
        //disp();
        turn*=-1;

    }

    void yourturn()

    {
    	Scanner stdIn=new Scanner(System.in);
        int x,i,j;
       // int r[]=new int[8];
        //C=W;
        do{
        	System.out.println("please");

        x=stdIn.nextInt();
         i=x/10;
         j=x%10;
         //r=res(i,j,true);
        }while(res(i,j,true,false)==false);

        //rev(r,i,j);
        printb();
        turn*=-1;
        //printf("%d",C);
       // C*=-1;

    }
    int hantei() //0 end 1 pass 2normal
    {
        int c=turn,h;
        //C=B;
        if(ser()==0)
        {
            turn*=-1;
            if(ser()==0){
                System.out.println("end");
                for(int i=0;i<5;i++)
                	System.out.print(Main.stack.pop());
                jud();
                h=0;
            }
            else{
                System.out.println("sente pass");
                h=1;
            }
        }

        else h=2;
        //C=c;
        return(h);
    }
}
public class Main(){
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int r[]=new int [8];
		int t;


		board b1=new board();
		b1.printb();
		System.out.println(b1.ser()+"can");

		while(true){
			t=b1.hantei();
			if(t==0)
				break;
			else if(t==2)
			{
		Outer:
		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++)
				{
					//r=b1.res(i, j,true);
					for(int c:r)
						System.out.print(c);
					System.out.println();
					System.out.print(r[0]);
					if(b1.res(i, j, true)==true){

						b1.printb();
						b1.turn*=-1;
						break Outer;
					}
				}
			}
			t=b1.hantei();
			if(t==0)
				break;
			else if(t==2)
				b1.yourturn();
		}
	}

}
