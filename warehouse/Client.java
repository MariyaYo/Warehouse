package warehouse;

import java.util.Random;

public class Client extends Thread{
	
	private Shop shop;
	private String product;
	private int quantity;
	Random rn = new Random();
	
	public Client(Shop s){
		if(s != null){
			this.shop = s;
		}
		int quantity = rn.nextInt(2) + 3;
		this.quantity = quantity;
		int product = rn.nextInt(9);
		switch (product) {
		case 1:
			this.product = "Chicken";
			break;
		case 2:
			this.product = "Beef";
			break;

		case 3:
			this.product = "Pork";
			break;
		case 4:
			this.product = "Apple";
			break;
		case 5:
			this.product = "Orange";
			break;
		case 6:
			this.product = "Banana";
			break;
		case 7:
			this.product = "Cucumber";
			break;
		case 8:
			this.product = "EggPlant";
			break;

		default:
			this.product = "Potato";
			break;
		}
	}
	
	@Override
	public void run() {
		while(true){
			shop.SendProducts(this.product, this.quantity);
			System.out.println("klienta kupi " + this.product + " kg " + this.quantity);
			try {
				sleep(1500);
			} catch (InterruptedException e) {
				System.out.println("cant sleep");
			}
		}
	}
}
