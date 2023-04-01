

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Giocatore implements Runnable {

    private int idGiocatore;

    private Arbitro arbitro;

    private int numeroCarte;

    private List<Integer> listaCarte = null; 
    
    public Giocatore(int idGiocatore, Arbitro arbitro, int numeroCarte) {
        this.idGiocatore = idGiocatore;
        this.arbitro = arbitro;
        this.numeroCarte = numeroCarte;
        listaCarte = new ArrayList<>(numeroCarte);
        for (int i = 0; i < numeroCarte; i++) {
            listaCarte.add(i + 1);
        }
        Collections.shuffle(listaCarte);
    }

    public void run(){

        for(int i = 0; i < numeroCarte; i++) {
            arbitro.gioca(idGiocatore, listaCarte.get(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }

        int vincitore = arbitro.vincitore();
         if(vincitore == idGiocatore){
            System.out.println("Sono io il vincitore! - Thread " + idGiocatore);
        }
        else {
            // System.out.println("Sono io il vincitore! - Thread " + idGiocatore);
            System.out.println("Sono il thread " + idGiocatore + " e ho perso!");
        }

    }
}