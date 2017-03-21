package warehouse;

import java.util.HashMap;
import java.util.Map.Entry;

public class Shop extends Warehouse implements Runnable{
	Warehouse wh;
	
	public Shop(Warehouse wh){
		if(wh!=null){
			this.wh = wh;
		}
	}

	public synchronized void SendProducts(String product, int quantity){
		while(productNotInStoke(product)){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("oh im interupted");
			}
		}
		prodaiStokata(quantity);
		notifyAll();
	}

	private void prodaiStokata(int quantity) {
		for(Entry <String , HashMap<String, Integer>> e : catalog.entrySet()){
			for(Entry<String , Integer> e2 : e.getValue().entrySet()){
				e2.setValue(e2.getValue()-quantity);
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			this.deliver();
			
		}
	}
	
}
