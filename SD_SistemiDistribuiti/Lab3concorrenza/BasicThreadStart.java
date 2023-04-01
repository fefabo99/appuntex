package SD_SistemiDistribuiti.Lab3concorrenza;

public class BasicThreadStart extends Thread {
	SharedObject mySo;
	String name;
	
	public BasicThreadStart(SharedObject so, String name) {
		mySo= so;
		this.name=name;
		//start();
	}

	public void run() {
		
		for(int i=0;i<6;i++){
			
				System.out.println("Sono thread "+ name + ":"+ ((SharedObject)mySo).increment(name));
				
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		}
	}
    
	public static void main(String[] args) throws InterruptedException {
		
		SharedObject so= new SharedObject();
		
		BasicThreadStart t1 = new BasicThreadStart(so, "t1");
		BasicThreadStart t2 = new BasicThreadStart(so, "t2");
		BasicThreadStart t3 = new BasicThreadStart(so, "t3");
		t1.start();
		t2.start();
		t3.start();
		// come li lancio?
		
	}
}

