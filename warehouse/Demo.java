package warehouse;

import java.util.ArrayList;

public class Demo {
	public static void main(String[] args) {
		
		Warehouse wh = new Warehouse();
		
		Supplier sup = new Supplier(wh);
		sup.start();
		Shop shop1 = new Shop(wh);
		Thread t1 = new Thread(shop1);
		t1.start();
		Shop shop2 = new Shop(wh);
		Thread t2 = new Thread(shop2);
		t2.start();
		Shop shop3 = new Shop(wh);
		Thread t3 = new Thread(shop3);
		t3.start();
		
		ArrayList<Client> clienti = new ArrayList<>();
		for(int i = 0; i < 9 ; i++){
			Client c = null;
			if(i <3){
				c = new Client(shop1);
			}
			if(i<6){
				c = new Client(shop2);
			}
			if(i<9){
				c = new Client(shop3);
			}
			clienti.add(c);
		}
		
		for(Client c : clienti){
			c.start();
		}
	}
}
