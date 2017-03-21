package warehouse;

import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {
	
	protected static final int MIN_QUANTITY = 5;
	protected HashMap<String , HashMap<String, Integer>> catalog;
	
	public Warehouse() {
		catalog = new HashMap<>();
		catalog.put("Vegetables", new HashMap<>());
		catalog.put("Fruits", new HashMap<>());
		catalog.put("Meats", new HashMap<>());
		catalog.get("Vegetables").put("Potato", 15);
		catalog.get("Vegetables").put("EggPlant", 15);
		catalog.get("Vegetables").put("Cucumber", 15);
		catalog.get("Fruits").put("Banana", 15);
		catalog.get("Fruits").put("Orange", 15);
		catalog.get("Fruits").put("Apple", 15);
		catalog.get("Meats").put("Pork", 15);
		catalog.get("Meats").put("Beef", 15);
		catalog.get("Meats").put("Chicken", 15);
	}
	
	public synchronized void deliver(){
		while(!deficitnaStoka()){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("i dont want to wait!");
			}
		}
		zapulniDeficitniteStoki();
		notifyAll();
	}
	
	protected void zapulniDeficitniteStoki() {
		for(Entry <String , HashMap<String, Integer>> e : catalog.entrySet()){
			for(Entry<String , Integer> e2 : e.getValue().entrySet()){
				e2.setValue(e2.getValue()+25);
			}
		}
	}

	protected boolean deficitnaStoka() {
		for(Entry <String , HashMap<String, Integer>> e : catalog.entrySet()){
			for(Entry<String , Integer> e2 : e.getValue().entrySet()){
				if(e2.getValue() < MIN_QUANTITY){
					System.out.println("Produkta e iz4erpan. Zaredete go!");
					return true;
				}
			}
		}
		return false;
	}

	public synchronized void SendProducts(String product){
		while(!productNotInStoke(product)){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("oh im interupted");
			}
		}
		prodaiStokata();
		notifyAll();
	}

	private void prodaiStokata() {
		for(Entry <String , HashMap<String, Integer>> e : catalog.entrySet()){
			for(Entry<String , Integer> e2 : e.getValue().entrySet()){
				e2.setValue(e2.getValue()-5);
			}
		}
	}
	
	protected boolean productNotInStoke(String p) {
		for(Entry <String , HashMap<String, Integer>> e : catalog.entrySet()){
			for(Entry<String , Integer> e2 : e.getValue().entrySet()){
				if(e2.getValue() < MIN_QUANTITY && e2.getValue().equals(p)){
					return true;
				}
			}
		}
		return false;
	}
	
}	
