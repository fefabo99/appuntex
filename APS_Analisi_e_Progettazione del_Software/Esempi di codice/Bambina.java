import java.util.Observer;
import java.util.Observable;
public class Bambina implements Observer {
    private int cambiDiNome = 0;
    public void update(Observable obs, Object arg) {
        System.out.println("Bambina: Vedo che il cagnolino adesso si chiama \"" + ((Cagnolino) obs).returnName() + "\"");
        cambiDiNome++;
    }
    public int numeroCambiDiNome() {
        return cambiDiNome;
    }
}