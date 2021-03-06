
package g151210311.deadlock;
import java.util.Scanner;
/**
 Ahmet Mücahit Gürer
 G151210311
 İşletim Sistemleri Proje
 */
public class G151210311Deadlock {

private int proses, kaynak, max[][], atanmis[][],gecici[][], istek[];

    public static void main(String[] args) {
      System.out.println("_______Ölümcül Kitlenme Tespit Algoritması_______");
      G151210311Deadlock deadlock = new G151210311Deadlock();
      deadlock.giris();
      deadlock.goster();
      deadlock.cagir();

}
    public void giris(){
    Scanner sc=new Scanner(System.in);
    System.out.print("Proses Sayısını Giriniz: ");
    proses=sc.nextInt();  
    System.out.print("Kaynak Sayısını Giriniz: ");
    kaynak=sc.nextInt(); 

    gecici=new int[proses][kaynak];  
    max=new int[proses][kaynak];
    atanmis=new int[proses][kaynak];
    istek=new int[kaynak];

    System.out.println("Atanmış Matrisini Giriniz ([Proses][Kaynak]) Adedi Kadar ..:");
 for(int i=0;i<proses;i++)
      for(int j=0;j<kaynak;j++)
     atanmis[i][j]=sc.nextInt(); 

 System.out.println("Max Matrisini Giriniz ([Proses][Kaynak] Adedi Kadar ..:");
 for(int i=0;i<proses;i++)
      for(int j=0;j<kaynak;j++)
     max[i][j]=sc.nextInt(); 

    System.out.println("İstek Matrisini Giriniz [Kaynak] Adedi Kadar ..:");
    for(int j=0;j<kaynak;j++)
     istek[j]=sc.nextInt();  

    sc.close();          
}

public void goster(){
 

    System.out.println("\n\nProsesler      Atanmış       Max       İstek");
    for(int i=0;i<proses;i++){
        System.out.print("P"+(i+1));
        System.out.print("           ");
            for(int j=0;j<kaynak;j++){
            System.out.print("  "+atanmis[i][j]);
            }
        System.out.print("   ");
        for(int j=0;j<kaynak;j++){
            System.out.print("  "+max[i][j]);
            }
            System.out.print("   ");
        if(i==0){
            for(int j=0;j<kaynak;j++){
               System.out.print("  "+istek[j]); 
            }
        }
         System.out.println();
    }
}

public void cagir(){
    int biten[],kontrolet=1;
    int guvensiz[];
    int i,j,say;

    biten=new int[100];
    guvensiz=new int[100];

    for(i=0;i<proses;i++)
           {
                          biten[i]=0;
           }

    for(i=0;i<proses;i++)
           {
                          for(j=0;j<kaynak;j++)
                          {
                                         gecici[i][j]=max[i][j]-atanmis[i][j];
                          }
           }
    while(kontrolet==1)
           {
            kontrolet=0;
            for(i=0;i<proses;i++) {
                int c=0;
                for(j=0;j<kaynak;j++){
                if((biten[i]==0)&&(gecici[i][j]<=istek[j])) {
                    c++;
                    if(c==kaynak){
                        for(say=0;say<kaynak;say++) {
                            istek[say]+=atanmis[i][j];
                            biten[i]=1;
                            kontrolet=1; }
                    if(biten[i]==1){
                        i=proses; } 
                    }
                }
            }
            }
            }
            j=0;
            kontrolet=0;
            for(i=0;i<proses;i++)
            {
                          if(biten[i]==0)
                          {
                                         guvensiz[j]=i;
                                         j++;
                                         kontrolet=1;
                          }
           }
             if(kontrolet==1)
             {

                          System.out.println("\n\nSistem Deadlockta. Deadlock proessler;\n");
                          for(i=0;i<proses;i++)
                          {
                              System.out.println("P"+guvensiz[i]);
                          }
           }
             else
           {
                          System.out.println("Deadlock yoktur.");
           }  
}
}
