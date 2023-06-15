import java.util.Observable;

public class Cagnolino extends Observable {
    private String nome = "Cagnolino senza nome";

    public void receiveName(String nuovoNome) {
        if (Math.random() < .5) {
            System.out.println( "Cagnolino: Mi piace! Ho cambiato il mio nome in \"" + nuovoNome + "\"... Arf! Bau!" );
            nome = nuovoNome;
            this.setChanged();
        } else {
            System.out.println( "Cagnolino: Io adesso mi chiamo \"" + nome + 
            "\" e non voglio cambiare il mio nome in \"" + nuovoNome + "\" Bau!" );
        }
        this.notifyObservers();
    }

    public String returnName() {
        return nome;
    }
}