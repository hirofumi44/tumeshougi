import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

class tumeshougi{
		final static int Board_size=5,Tesuu=1;
		final static int Ou=1,Kin=2,Gin=3,Hu=4,Kyou=5,Hisha=6,Kaku=7,Kei=8,Ryu=9,Uma=10,Narigin=11,Tokin=12,Narikyou=13,Narikei=16;
		final static int Nari=8;
		final static String Koma[]={"ou","kin","gin","hu","kyou","hi","kaku","keima","ryu","uma"};
		final static boolean Kind[]={true,true,true,true,true,false,true,false};
		final static boolean Gind[]={true,true,true,false,false,true,false,true};
		final static boolean Hud[]={false,true,false,false,false,false,false,false};
		final static int Hishad[]={1,3,4,6};
		final static int Kakud[]={0,2,5,7};
		final static boolean Komadir[][]={Kind,Gind,Hud};
		static int Board[][]=new int[Board_size][Board_size];
		static int Oux=0,Ouy=1;
		static int Dir[][]={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
		final static int Ko_Toatal[]={Ou,1,Kin,4,Gin,4,Hu,16,Kyou,4,Hisha,2,Kaku,2,Kei,4};

		static Stack<Integer> motigoma =new Stack<Integer>();
		static ArrayList<Integer> aigoma =new ArrayList<Integer>();
		static Stack<Integer> stack =new Stack<Integer>();
		//static ArrayList<Integer> motigoma=new ArrayList<Integer>();
		//static int motigoma_save=-1;


	static void print_board(){
		for(int i=0;i<Board_size;i++)
		{
			for (int j=0;j<Board_size ;j++ ) {
				switch(Board[i][j])
            {
                case Ou:
                    System.out.printf("%3s","O");
                    break;
                case Kin:
                    System.out.printf("%3s","K");
                    break;
                case -Kin:
                    System.out.printf("%3s","-K");
                    break;
                case Gin:
                    System.out.printf("%3s","G");
                    break;
                case -Gin:
                    System.out.printf("%3s","-G");
                    break;
                case Hu:
                    System.out.printf("%3s","H");
                    break;
                case -Hu:
                    System.out.printf("%3s","-H");
                    break;
                case Kyou:
                    System.out.printf("%3s","Ky");
                    break;
                case -Kyou:
                    System.out.printf("%3s","-Ky");
                    break;
                case Hisha:
                    System.out.printf("%3s","Hi");
                    break;
                case -Hisha:
                    System.out.printf("%3s","-Hi");
                    break;
                case Kaku:
                    System.out.printf("%3s","Ka");
                    break;
                case -Kaku:
                    System.out.printf("%3s","-Ka");
                    break;
                 case Kei:
                    System.out.printf("%3s","Ke");
                    break;
                case -Kei:
                    System.out.printf("%3s","-Ke");
                    break;
                case Ryu:
                    System.out.printf("%3s","Ry");
                    break;
                case -Ryu:
                    System.out.printf("%3s","-Ry");
                    break;
                case Uma:
                    System.out.printf("%3s","Um");
                    break;
                case -Uma:
                    System.out.printf("%3s","-Um");
                    break;
                case Narigin:
                    System.out.printf("%3s","NG");
                    break;
                case -Narigin:
                    System.out.printf("%3s","-NG");
                    break;
                default:
                    System.out.printf("%3s","*");
                    break;

            }

			}
			System.out.println();
		}
		System.out.println();
	}
	static void copy(int x[][],int y[][]){
		for(int i=0;i<Board_size;i++)
			for (int j=0;j<Board_size ;j++)
				x[i][j]=y[i][j];
	}


	static void Array_add(ArrayList<Integer> A,ArrayList<Integer> B,ArrayList<Integer> C,ArrayList<Integer> D){
		A.addAll(B);
		A.addAll(C);
		A.addAll(D);

	}
	static void move(int x,int y,int p){
		int t=Board[x][y];
		Board[x][y]=0;
		x+=Dir[p][0];
		y+=Dir[p][1];
   		Board[x][y]=t;
}
static void move(int x,int y,int cx,int cy,int koma){

		Board[x][y]=0;
   	Board[cx][cy]=-koma;

   	System.out.print(cx+" "+cy+" "+Koma[Math.abs(koma)-1]);

   	System.out.println();
}

	static boolean[] koma_hex(int i,int j){
    //int count=0;
    boolean r[]=new boolean[8];
    for(int k=0;k<8;k++){
        if((i+Dir[k][0])>=0&&(i+Dir[k][0])<Board_size&&(j+Dir[k][1])>=0&&(j+Dir[k][1])<Board_size){

        	r[7-k]=true;
        	//count++;
        }
    }
        //System.out.print("array");

      /*  for (int k=0;k<array.size();k++) {
        	System.out.print(array.get(k));

        }*/
        //r[8]=(count==0?false:true);
    return r;

}

static boolean[] outedir(int koma){
	boolean kouho[]=koma_hex(Oux,Ouy);
	for(int i=0;i<8;i++){
				kouho[i]=kouho[i]&Komadir[koma-2][i];
				//System.out.print(kouho[i]);
			}
			return kouho;
}
static ArrayList<int []> tobi_search(int x,int y,int koma,int dir){
	ArrayList <int []> oute_list=new ArrayList<int []>();
	boolean f=false;
	int sx=x,sy=y;
	int Dire[]=new int[4];
	if(koma==Hisha)
		Dire=Hishad;
	else if(koma==Kaku)
		Dire=Kakud;
	while(true)
				{
					x+=Dir[7-dir][0];
					y+=Dir[7-dir][1];
					if(x<0||x>=Board_size||y<0||y>=Board_size)
						break;
					if(Board[x][y]<=0){
						if(Board[x][y]==0){

								if(!f&&motigoma.contains(koma)){
								int r[]={x,y,koma,0};
								oute_list.add(r);
								}


							if(f)
								continue;

						}
						else{
							f=!f;
							sx=x;
							sy=y;
						}
					if(koma==Hisha||koma==Kaku){
									if(dir==Dire[0]||dir==Dire[3]){
										ArrayList<int[]> list1=tobi_search2(x,y,koma,Dire[1]);
										list1.addAll(tobi_search2(x,y,koma,Dire[2]));
										for(int i[]:list1)
										{
											int s1[]={x,y,-koma,-(10*i[0]+i[1])};
											oute_list.add(s1);
											//System.out.println("x"+x+" i"+i[0]);
											if(x<=2||i[0]<=2){
												int s2[]={x,y,-(koma+3),-(10*i[0]+i[1])};
												oute_list.add(s2);
											}

										}

								}
								else{
									ArrayList <int[]> list2=tobi_search2(x,y,koma,Dire[0]);
									list2.addAll(tobi_search2(x,y,koma,Dire[3]));
									for(int i[]:list2)
										{
											int s1[]={x,y,-koma,-(10*i[0]+i[1])};
											oute_list.add(s1);
											//System.out.println("x"+x+" "+i[0]);
											if(x<=2||i[0]<=2){
												int s2[]={x,y,-(koma+3),-(10*i[0]+i[1])};
												oute_list.add(s2);
											}
										}
								}
							}



					}
					else if(Board[x][y]>1){
						if(Board[x][y]==koma&&f==true){
							int r1[]={sx,sy,-koma,-(10*x+y)};
							oute_list.add(r1);
							if((x<=2||sx<=2)&&(koma==Hisha||koma==Kaku)){
										int s2[]={sx,sy,-(koma+3),-(10*x+y)};
										oute_list.add(s2);
									}
						}
						break;
					}

				}
		return oute_list;
}
static ArrayList<int []> tobi_search2(int x,int y,int koma,int dir){
	ArrayList <int []> list=new ArrayList<int []>();
	boolean semegawa=koma>0?true:false;
		if(semegawa&&koma==Kei){
				int sx1=x+2,sy1=y-1,count=2;
			if(sx1<Board_size){
				while(count-->0)
				if(sy1>=0&&sy1<Board_size&&Board[sx1][sy1]==Kei)
				{
					int k[]={sx1,sy1,-Kei};
					list.add(k);
					sy1+=2;
				}


			}
			return list;
		}
		if(!semegawa){
			ArrayList <int[]>no_move_list=no_move_list();
			int cx[]={x,y};

			while(true)
				{
					cx[0]+=Dir[dir][0];
					cx[1]+=Dir[dir][1];

					if(cx[0]<0||cx[0]>=Board_size||cx[1]<0||cx[1]>=Board_size)
						break;
					if(Board[cx[0]][cx[1]]==0)
							continue;
						for(int a[]:no_move_list)
							if(a==cx){
								return null;
							}


					if(Board[cx[0]][cx[1]]<0){
						if(Board[cx[0]][cx[1]]==koma){
							int r1[]={cx[0],cx[1],koma};
							list.add(r1);
						}
						if((koma==-Hisha||koma==-Kaku)&&Board[cx[0]][cx[1]]==koma-3){
							int r1[]={cx[0],cx[1],koma-3};
							list.add(r1);
						}

						break;
					}
					else{
							break;

					}
				}
			}
		while(semegawa)
				{
					x+=Dir[7-dir][0];
					y+=Dir[7-dir][1];

					if(x<0||x>=Board_size||y<0||y>=Board_size)
						break;


					if(Board[x][y]==0)
						continue;
					else if(Board[x][y]>0){
						if(Board[x][y]==koma){
							int r1[]={x,y,-koma};
							list.add(r1);

						}
						if((koma==Hisha||koma==Kaku)&&Board[x][y]==koma+3){
							int r1[]={x,y,koma+3};
							list.add(r1);
						}
						break;
					}
					else{
							break;
					}
				}


		return list;
}
static ArrayList<int[]> tobidougu(int x,int y,int koma){
		ArrayList <int[]> oute_list=new ArrayList<int[]>();
		switch(koma){
			case Kyou:
			oute_list=tobi_search(x,y,Kyou,1);
			break;
			case -Kyou:
			oute_list=tobi_search2(x,y,-Kyou,1);
			break;

			case Hisha:
			for(int i:Hishad)
				oute_list.addAll(tobi_search(x,y,Hisha,i));
			break;
			case -Hisha:
			for(int i:Hishad)
				oute_list.addAll(tobi_search2(x,y,-Hisha,i));
			break;


			case Kaku:
			for(int i:Kakud)
				oute_list.addAll(tobi_search(x,y,Kaku,i));
			break;
			case -Kaku:
			for(int i:Kakud)
				oute_list.addAll(tobi_search2(x,y,-Kaku,i));
			break;

			case Kei:

			int x0=x+2;
			for(int y0=y-1;y0<=y+1;y0+=2)
				if(motigoma.contains(Kei)&&y0>=0&&y0<Board_size&&Board[x0][y0]==0){
							int a[]={x0,y0,Kei};
							oute_list.add(a);
			}

			int sx=x+4;
			int sy;
			if(sx<Board_size)
				for(sy=y-2;sy<=y+2;sy+=2)
					if(sy>=0&&sy<Board_size&&Board[sx][sy]==Kei){
						if(sy<=y&&Board[x+2][y-1]<=0){
							int r[]=new int[4];
							r[0]=x+2;
							r[1]=y-1;
							r[2]=-Kei;
							r[3]=-(10*sx+sy);
							oute_list.add(r);

						}
						if(sy>=y&&Board[x+2][y+1]<=0){
							int r1[]=new int[4];
							r1[0]=x+2;
							r1[1]=y+1;
							r1[2]=-Kei;
							r1[3]=-(10*sx+sy);
							oute_list.add(r1);

						}
					}
			break;
			case -Kei:
			int sx1=x-2,sy1=y-1;
			if(sx1>=0){
				if(sy1>=0&&Board[sx1][sy1]==-Kei)
				{
					int k[]={sx1,sy1};
					oute_list.add(k);

				}
				sy1+=2;
				if(sy1<Board_size&&Board[sx1][sy1]==-Kei)
				{
					int k1[]={sx1,sy1};
					oute_list.add(k1);

				}
			}
				break;

		}

		return oute_list;
}
static ArrayList<int[]>  oute_list(){
	Set<int []> oute=new HashSet<int []>();

	for(int koma=Kin;koma<=Hu;koma++){
		boolean kouho[]=outedir(koma);
			int x=0,y=0;
			for(int j=0;j<8;j++){
				if(!kouho[j])
					continue;

					x=Oux+Dir[7-j][0];
					y=Ouy+Dir[7-j][1];


				if(Board[x][y]>1)
					break;
				else if(Board[x][y]==0&&motigoma.contains(koma)){
						int ou[]={x,y,koma,0};
						oute.add(ou);
						}

				int sx=x;
				int sy=y;
			for(int i=0;i<8;i++)
			{
				if(!kouho[i])
					continue;
				x=sx+Dir[7-i][0];
				y=sy+Dir[7-i][1];
				if(x<0||x>=Board_size||y<0||y>=Board_size)
						continue;

				if(Board[x][y]==koma){
						    int ou[]={sx,sy,-koma,-(10*x+y)};
						    oute.add(ou);

						}
				if(koma==Kin&&Board[x][y]>=Narigin){
							int ou[]={sx,sy,-Board[x][y],-(10*x+y)};
						    oute.add(ou);

					}
				}
			}


		}

		ArrayList <int[]> oute_list=new  ArrayList<int[]>(oute);



		for(int j=0;j<8;j++){

					int x[]={Oux+Dir[7-j][0],Ouy+Dir[7-j][1]};
					//System.out.println("xy"+x[0]+" "+x[1]);
					if(x[0]<0||x[0]>=Board_size||x[1]<0||x[1]>=Board_size)
							continue;
					if(Board[x[0]][x[1]]>1)
						continue;

					ArrayList <int[]> kiki_list=kiki_list(x[0],x[1],true);

					for(int[] k:kiki_list)
					{
						if((-k[2]==Kei||(-k[2]>=Gin&&-k[2]<=Kyou))&&(k[0]<=2||x[0]<=2)&&Kind[j]){
							int jj[]={x[0],x[1],-(k[2]+Nari),-(10*k[0]+k[1])};

							oute_list.add(jj);
						}
						if(-k[2]==Hisha&&(j==0||j==2||j==5||j==7)&&(k[0]<=2||x[0]<=2)){
							int jj[]={x[0],x[1],-Ryu,-(10*k[0]+k[1])};

							oute_list.add(jj);
						}
						if(-k[2]==Kaku&&(j==1||j==3||j==4||j==6)&&(k[0]<=2||x[0]<=2)){
							int jj[]={x[0],x[1],-Uma,-(10*k[0]+k[1])};

							oute_list.add(jj);
						}


				}

			}



	for(int koma=Kyou;koma<=Kei;koma++){
		oute_list.addAll(tobidougu(Oux,Ouy,koma));
	}

	/*for(int i=0;i<oute_list4.size();i++)
		if(oute_list.get(i)<0)
			oute_list4.remove(i);*/


			return oute_list;
}
/*
static boolean kiki(int x,int y,boolean semegawa){
	boolean k[]=koma_hex(x,y);
	boolean a[]=new boolean[8];
	int r[]=new int[3];

	for(int koma=Kin;koma<=Hu;koma++)
	for(int i=0;i<8;i++){
		int	cx=x+Dir[7-i][0];
		int	cy=y+Dir[7-i][1];
		if(semegawa==true){
			a[i]=k[i]&Komadir[koma-2][i];
			if(a[i]==true&&Board[cx][cy]==koma){
				return true;
		}
		}
		else{
			a[i]=k[i]&Komadir[koma-2][7-i];

			if(a[i]==true&&Board[cx][cy]==-koma){
				//System.out.println("dir="+i+"koma="+koma+" toru");
				Board[cx][cy]=0;

				Board[x][y]=-koma;
				return true;
		}
		}

	}


		return false;
}*/
static ArrayList<int []> no_move_list(){
	int Oogoma[][]={Hishad,Kakud};
	ArrayList <int[]> list=new ArrayList<int[]>();
	int x=Oux;
	int y=Ouy;
	while(++x<Board_size){

		if(Board[x][y]>Ou)
			break;
		if(Board[x][y]<0){
			if(tobi_search2(x,y,Kyou,1).isEmpty()==false)
				{
					int a[]={x,y};
					list.add(a);
				}
				break;
		}
	}
	int koma=Hisha;
	for(int j[]:Oogoma){
		for(int i:j){
			x=Oux;
			y=Ouy;

			while(true){
				x+=Dir[i][0];
				y+=Dir[i][1];

				if(x<0||x>=Board_size||y<0||y>=Board_size||Board[x][y]>Ou)
					break;

					if(Board[x][y]<0){
						if(tobi_search2(x,y,koma,7-i).isEmpty()==false)
							{
								int a[]={x,y};
								list.add(a);
							}
							break;
					}
				}

		}
		koma++;
	}
	return list;
}
static  ArrayList <int[]> kiki_list(int x,int y,boolean semegawa){
	ArrayList <int[]> kiki_list=new ArrayList<int[]>();

	if(semegawa){
		for(int koma=Kin;koma<=Hu;koma++)
		for(int i=0;i<8;i++){
			int	cx=x+Dir[i][0];
			int	cy=y+Dir[i][1];
			//System.out.print("cx"+cx+" "+cy);

				if(cx>=0&&cx<Board_size&&cy>=0&&cy<Board_size){
					if(Komadir[koma-2][7-i]&&Board[cx][cy]==koma){
						//System.out.println("a board"+i+" "+cx+" "+cy);
						int kiki[]={cx,cy,koma};
						kiki_list.add(kiki);

					}
					if(koma==Kin&&Komadir[koma-2][7-i]&&Board[cx][cy]>=Narigin){
						//System.out.println("a board"+i+" "+cx+" "+cy);
						int kiki[]={cx,cy,Board[cx][cy]};
						kiki_list.add(kiki);

					}


			}


		}

			kiki_list.addAll(tobi_search2(x,y,Kyou,1));
			kiki_list.addAll(tobi_search2(x,y,Kei,1));
			for(int i:Hishad){
				kiki_list.addAll(tobi_search2(x,y,Hisha,i));
				int	cx=x+Dir[i][0];
				int	cy=y+Dir[i][1];

				if(cx>=0&&cx<Board_size&&cy>=0&&cy<Board_size&&Board[cx][cy]==Uma){
					int kiki[]={cx,cy,Uma};
					kiki_list.add(kiki);
				}
			}
			for(int i:Kakud){
				kiki_list.addAll(tobi_search2(x,y,Kaku,i));
				int	cx=x+Dir[i][0];
				int	cy=y+Dir[i][1];
				if(cx>=0&&cx<Board_size&&cy>=0&&cy<Board_size&&Board[cx][cy]==Uma){
					int kiki[]={cx,cy,Uma};
					kiki_list.add(kiki);
				}
		}
		}

	else{
		ArrayList <int[]> no_move_list=no_move_list();
		for(int i[]:no_move_list)
			for(int j:i)
				System.out.print(j);
		System.out.println();
		for(int koma=Kin;koma<=Hu;koma++)
		for(int i=0;i<8;i++){
			boolean flag=false;
			int cx[]={x+Dir[i][0],y+Dir[i][1]};

				if(cx[0]<0||cx[0]>=Board_size||cx[1]<0||cx[1]>=Board_size)
					continue;

				if(Komadir[koma-2][i]&&Board[cx[0]][cx[1]]==-koma)
				{
					for(int a[]:no_move_list)
						if(a==cx){
							flag=true;
							break;
						}
					if(flag){

						int kiki[]={cx[0],cx[1],-koma};
						kiki_list.add(kiki);
					}
				}
		}
		for(int i=0;i<8;i++){
			int	cx=x+Dir[i][0];
			int	cy=y+Dir[i][1];
			if(cx>=0&&cx<Board_size&&cy>=0&&cy<Board_size&&Board[cx][cy]==Ou&&kiki_list(x,y,true).isEmpty()){
					/*ArrayList <int[]>list=kiki_list(cx,cy,true);
					System.out.println("kikicheck"+cx+" "+cy);
					for(int a[]:list)
						for(int b:a)
							System.out.print(b);*/
					int kiki[]={cx,cy,Ou};
					kiki_list.add(kiki);
			}
		}
			ArrayList <int[]> kyou=tobidougu(x,y,-Kyou);
			kiki_list.addAll(kyou);
			ArrayList <int[]> hisha=tobidougu(x,y,-Hisha);
			kiki_list.addAll(hisha);
			ArrayList <int[]> kaku=tobidougu(x,y,-Kaku);
			kiki_list.addAll(kaku);
			ArrayList <int[]> kei=tobidougu(x,y,-Kei);
			kiki_list.addAll(kei);

	}

		/*boolean f=false;
		for(int i=x+1;i<Board_size;i++){
				if(Board[i][y]==Kyou){
					kiki_list.add(i);
					kiki_list.add(y);
					kiki_list.add(Kyou);
				}
				else if(Board[i][Ouy]>1)
					break;
				else{
					if(f)
						break;
					f=!f;
				}

		}
	*/

		return kiki_list;
}


static boolean seme(int tesuu){
	int C1board[][]=new int[Board_size][Board_size];
	int COux=Oux;
	int COuy=Ouy;
	//System.out.println("seme method size"+motigoma.size());
	ArrayList<int[]> oute_list=oute_list();
	copy(C1board,Board);
	int uke_para[]={-1,-1};


	int count=0;
	/*System.out.println("oute_list");
	for(int i[]:oute_list)
		{
			for(int j:i)
				System.out.print(j);
			System.out.println();
	}
	*/


		for(int i[]:oute_list){
			boolean flag=false;
			if(i[2]<0){
				if(Board[i[0]][i[1]]<0){
					motigoma.add(-Board[i[0]][i[1]]);
					//System.out.print("motigoma add"+(-Board[i[0]][i[1]]));
				}
				move(-i[3]/10,-i[3]%10,i[0],i[1],i[2]);
			}
			else {
				Board[i[0]][i[1]]=i[2];
				motigoma.remove(motigoma.indexOf(i[2]));
				flag=true;
			}
				print_board();
				boolean uk=uke(i[0],i[1],--tesuu);
				System.out.println("uke ="+uk);

				//print_board();
				if(flag)
						motigoma.push(i[2]);
			if(!uk){
				System.out.println("tumi");
				return true;
				}

					copy(Board,C1board);
					Oux=COux;
					Ouy=COuy;

					tesuu++;
					//System.out.println("oure"+10*Oux+Ouy);
					print_board();



		}

	return false;
	}


static boolean uke(int x,int y,int tesuu){
	int Cboard[][]=new int[Board_size][Board_size];
	int COux=Oux;
	int COuy=Ouy;

	copy(Cboard,Board);
	//System.out.println("uke method tesuu"+tesuu);

	ArrayList <int[]> uke_list=new ArrayList<int[]>();;
	ArrayList <int[]> kiki_list=kiki_list(x,y,false);
	for(int i[]:kiki_list){
		int j[]={x,y,i[0],i[1],i[2]};
		uke_list.add(j);
		aigoma.add(Board[x][y]);
	}
	//nigeru
	for(int i=0;i<8;i++){
		int	cx=Oux+Dir[i][0];
		int	cy=Ouy+Dir[i][1];
		if(cx>=0&&cx<Board_size&&cy>=0&&cy<Board_size&&Board[cx][cy]==0&&kiki_list(cx,cy,true).isEmpty()){
			Board[Oux][Ouy]=0;
			Oux=cx;
			Ouy=cy;
			int j[]={cx,cy,-1,-1,Ou};
			uke_list.add(j);
			Board[Oux][Ouy]=Ou;
		}
	}

	if(Board[x][y]==Kyou&&(x-Oux)>1)
		for(int koma=Kin;koma<=Kei;koma++)
	{
		if(aigoma.contains(koma)){
		int j[]={Oux+1,Ouy,-2,-1,-koma};
		uke_list.add(j);


	}

	}

	if(Board[x][y]==Hisha||Board[x][y]==Ryu){
		int dis=0;
		if(x==Oux){
			while(y-Ouy-dis++>1){
				int j[]={Oux,Ouy+dis,-1,-1,-Hu};
				uke_list.add(j);
			}
			while(Ouy-y-dis++>1){
				int j[]={Oux,Ouy-1,-1,-1,-Hu};
				uke_list.add(j);
			}
		}
		 else if(y==Ouy){
			while(x-Oux-dis++>1){
				int j[]={Oux+dis,Ouy,-1,-1,-Hu};
				uke_list.add(j);
			}
			 if(Oux-x-dis++>1){
				int j[]={Oux-dis,Ouy,-1,-1,-Hu};
				uke_list.add(j);
			}
		}

	}
	/*
	System.out.println("ukelist check");
	for(int i[]:uke_list){
		for(int j:i)
			System.out.print(j);
		System.out.println();
	}*/
	if(uke_list.isEmpty()){
		//System.out.println("kikilist is empty");
		return false;
	}

	for(int i[]:uke_list){
		//System.out.println("dou"+Koma[Math.abs(i[4])-1]);
		if(i[4]==Ou){
			Board[Oux][Ouy]=0;
			Oux=i[0];
			Ouy=i[1];
		}
		Board[i[0]][i[1]]=i[4];
		if(i[2]>=0)
			Board[i[2]][i[3]]=0;
		else if(i[2]==-2)
			aigoma.remove(aigoma.indexOf(-i[4]));
		print_board();
		if(tesuu==0)
			return true;

		boolean uk=!seme(--tesuu);
		if(i[2]==-2)
			aigoma.add(i[4]);
		if(uk)
			return true;

		copy(Board,Cboard);
		Oux=COux;
		Ouy=COuy;
		tesuu++;
		//System.out.println("oure"+10*Oux+Ouy);
		print_board();
	}


	return false;
}

	static void init(){
		for(int i=0;i<Ko_Toatal.length;i+=2)
			for(int j=0;j<Ko_Toatal[i+1];j++)
				aigoma.add(Ko_Toatal[i]);
		//motigoma.push(Kin);
		motigoma.add(Kin);
		Board[Oux][Ouy]=Ou;
		Board[1][1]=-Gin;
		//Board[0][3]=Hisha;
		//Board[1][1]=-Kyou;
		//Board[1][2]=-Kyou;
		//Board[2][3]=Kaku;
		//Board[3][1]=Kyou;
		Board[2][1]=Kin;
		//Board[4][1]=Hisha;
		//Board[1][3]=Kin;
		//Board[2][0]=-Kaku;
		//Board[4][1]=Kyou;
		for(int i:motigoma)
			aigoma.remove(aigoma.indexOf(i));
		for(int i=0;i<Board_size;i++)
			for(int j=0;j<Board_size;j++)
				if(Board[i][j]!=0){
					aigoma.remove(aigoma.indexOf(Math.abs(Board[i][j])));
				}
	}


	public static void main(String[] args){
/*
		ArrayList <int[]> list=new ArrayList <int[]>();
		int a[]={1,2};
		list.add(a);
		for(int i[]:list)
		 if(i==a)
			System.out.print("e");
		int b[]={1,2};
		System.out.println("check"+list.indexOf(b));
		b[1]=3;
		System.out.println("check"+list.indexOf(b));
*/



	init();

	ArrayList <int[]> list=no_move_list();

		print_board();


		boolean se=seme(3);
		System.out.println("seme="+se);
		//print_board();
		//uke(r[0],r[1]);
		//print_board();

		/*
		System.out.println("check");
		for(int i[]:kiki_list(1,1,true))
			for(int j:i)
				System.out.print(j);
		System.out.println("check");
		for(int i[]:kiki_list(1,2,true))
			for(int j:i)
				System.out.print(j);*/
}
}
