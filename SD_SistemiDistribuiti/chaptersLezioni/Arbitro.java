
public class Arbitro{

    private int numeroGiocatori;

    private int numeroCarte;

    private final int maxTurni;
    
    private int[] vincite;

    private int[][] giocate;

    private int turno = 1;

    private int numeroGiocate = 0;

    private boolean gameOver = false;

    public Arbitro(int numeroGiocatori, int numeroCarte) {
        this.numeroGiocatori = numeroGiocatori;
        this.numeroCarte = numeroCarte;
        this.maxTurni = numeroCarte;
        vincite = new int[numeroGiocatori];
        //la riga sopra assume che i giocatori siano ordinati in modo sequenziale e identificati dall'id che sarebbe l'indice della lista. Potrebbe essere fatto anche con una struttura chiave-valore.
        for(int i = 0; i < numeroGiocatori; i++) {
            vincite[i] = 0;
        }
        giocate = new int[numeroGiocatori][maxTurni];
        for(int i = 0; i < numeroGiocatori; i++) {
            for(int j = 0; j < maxTurni; j++) {
                giocate[i][j] = 0;
            }
        }
    }
    
    public synchronized void gioca(int idGiocatore, int carta) {
        while(giocate[idGiocatore][turno-1] != 0){
            try {
                System.out.println("Il giocatore " + idGiocatore + " è in pausa.");
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        giocate[idGiocatore][turno-1] = carta;
        System.out.println("Il giocatore");
        numeroGiocate++;

        if(numeroGiocate == numeroGiocatori) {
            System.out.println("___Turno terminato___");
            aggiornaVincitore();
            numeroGiocate = 0;
            turno++;
            if(turno > maxTurni) {
                gameOver = true;
                System.out.println("___Partita terminata___");
            }
            notifyAll();
        }
    }
    
    private void aggiornaVincitore() {
        int max = 0;
        int idMax = 0;
        for (int i = 0; i < numeroGiocatori; i++) {
            if(giocate[i][turno-1] > max) {
                max = giocate[i][turno-1];
                idMax = i;
            }
        }

        for (int i = 0; i < numeroGiocatori; i++) {
            if(giocate[i][turno-1] == max && i != idMax) {
                return;
            }
        }

        vincite[idMax]++;
    }
    
    public synchronized int vincitore() {
        while(!gameOver) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int max = 0;
        int idMax = 0;

        for (int i = 0; i < numeroGiocatori; i++) {
            if(vincite[i] > max) {
                max = vincite[i];
                idMax = i;
            }
        }

        for (int i = 0; i < numeroGiocatori; i++) {
            if(vincite[i] == max && idMax != i) {
                return -1;
            }
        }
        return idMax;
    }
}