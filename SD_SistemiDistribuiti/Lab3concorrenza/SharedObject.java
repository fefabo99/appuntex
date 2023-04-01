package SD_SistemiDistribuiti.Lab3concorrenza;

public class SharedObject {
	
	protected int numWrite=0;
	protected String lastWriter="";
	
	public int getNumWrite() {
		return numWrite;
	}
	public void setNumWrite(int numWrite) {
		this.numWrite = numWrite;
	}
	public String getLastWriter() {
		return lastWriter;
	}
	public void setLastWriter(String lastWriter) {
		this.lastWriter = lastWriter;
	}
	// questo modella, piu' o meno, l'operazione atomica di scrittura
	public synchronized String increment(String writer) {
		String s = "numwrite era " + numWrite + " e ora e' ";
		numWrite++;
		lastWriter= writer;
		s= s+ numWrite + " aggiornato da " + lastWriter;
		return s;
	}

}
