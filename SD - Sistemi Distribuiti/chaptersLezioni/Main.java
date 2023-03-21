public class Main{

    public static int NUMERO_GIOCATORI =3;

    public static int NUMERO_CARTE =10;

    public static void main(String[] args) {
        System.out.println("Inizio programma");
        Arbitro a = new Arbitro(NUMERO_GIOCATORI, NUMERO_CARTE);
        Thread[] threads = new Thread[NUMERO_GIOCATORI];
        
        for(int i = 0; i < NUMERO_GIOCATORI; i++) {
            threads[i] = new Thread(new Giocatore(i, a, NUMERO_CARTE));
            threads[i].start();
        }

        try {
            for(int i = 0; i < NUMERO_GIOCATORI; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
        }
        
        System.out.println("Gioco terminato");
    }
}