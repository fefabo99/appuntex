package SD_SistemiDistribuiti.Lab3concorrenza;
// import java.util.Scanner;
import java.util.*;

public class ThreadCiao extends Thread{

    }

    public void run(){
        for(;;){
            System.out.println("ciao");
            try{
                Thread.sleep(100);
            }catch (InterruptedException e ){
                System.out.println("thread terminato");
            }
        }


    }

    public static void main(String[] agrs)throws InterruptedException{
        ThreadCiao s = new ThreadCiao();

    }
}