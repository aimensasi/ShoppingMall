package com.blank.Store;

import com.blank.Commands.Invoker;
import com.blank.Commands.StoreCommands.AddToCartCommand;
import com.blank.Commands.StoreCommands.CheckoutCommand;
import com.blank.Commands.StoreCommands.ExitStoreCommand;
import com.blank.Customers.Customer;
import com.blank.Items.Item;
import com.blank.ShoppingCart.ShoppingCart;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Store{

	private static final AtomicInteger mIdCount = new AtomicInteger(0);

	private Integer mId;
	private String mName;
	private Customer mCurrentCustomer;
	private List<Customer> mCustomers = new ArrayList<>();
	private List<Item> mItems = new ArrayList<>();
	private Faker mFake;

	public Store() {
		mId = mIdCount.incrementAndGet();
		mFake = new Faker();
	}

	public void setName(String name) {
		mName = name;
	}

	public Integer getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}


	public List<Customer> getCustomers() {
		return mCustomers;
	}

	public List<Item> getItems(){
		return mItems;
	}

	/**
	 * Create fake users inside each store for the sake of example
	 */
	public void hostFakeCustomers(){

		for (int i = 0; i <= 10; i++){

			Customer customer = new Customer();
			customer.setName(mFake.name().fullName());
			customer.setStore(this);
			customer.setShoppingCart(new ShoppingCart());
			mCustomers.add(customer);
		}
	}

	/**
	 * Create fake items inside each store for the sake of example
	 */
	public void addFakeItemsToStore(){

		for (int i = 0; i <= 10; i++){
			Item item = new Item();
			item.setName(mFake.pokemon().name());
			item.setPrice(mFake.number().randomDouble(2, 10, 129));
			item.setStoreId(mId);
			mItems.add(item);
		}
	}

	public void enter(Customer customer){

		mCurrentCustomer = customer;
		mCustomers.add(mCurrentCustomer);

		displayAvailableActions();
	}

	public void exist(Customer customer){
		mCustomers.remove(customer);
		mCurrentCustomer.getShoppingCart().emptyCart();
		mCurrentCustomer = null;
		System.out.println("Please Come Back Later");
	}

	public void addToCart(ShoppingCart shoppingCart, Item item){
		shoppingCart.addItem(item);
		System.out.println("Item Has Been Added Successfully.");
	}

	public void removeFromCart(ShoppingCart shoppingCart, Item item){
		shoppingCart.removeItem(item);
		System.out.println("Item Has Been Removed Successfully.");
	}

	public void checkout(ShoppingCart shoppingCart){
		shoppingCart.emptyCart();
		System.out.println("Checkout Successfully.");
	}

	public void undoCheckout(ShoppingCart shoppingCart, List<Item> itemList){
		shoppingCart.setItems(itemList);
		System.out.println("Your Shopping Cart has items again.");
		System.out.println("Your Money will be refunded.");
	}

	@Override
	public String toString() {
		return mId + " | " + mName + " | " + mCustomers.size() + " | " + mItems.size();
	}

	public void display(){
		System.out.printf("%1s  %-15s   %-7d   %-6d%n", mId, mName, mCustomers.size(), mItems.size());
	}

	private void displayAvailableActions() {
		Invoker invoker = Invoker.getInstance();
		Scanner scanner = new Scanner(System.in);

		while (true){

			displayActions(invoker);

			int option = scanner.nextInt();

			switch (option){
				case 1:
					displayStoreItems();
					break;
				case 2:
					mCurrentCustomer.getShoppingCart().displayItems();
					break;
				case 3:
					addItemToCart(scanner, invoker);
					break;
				case 4:
					onCheckout(scanner, invoker);
					break;
				case 99:
					invoker.undo();
					break;
				case 0:
					ExitStoreCommand exitStoreCommand = new ExitStoreCommand(this, mCurrentCustomer);
					invoker.execute(exitStoreCommand);
					option = -1;
					break;
			}

			if (option == -1){
				break;
			}
		}
	}




	private void displayActions(Invoker invoker){
		System.out.println();
		System.out.println("Choose an action : ");

		System.out.println("1 - Display Items");
		System.out.println("2 - Display Shopping Cart Items");
		System.out.println("3 - Add Item To Cart");
		System.out.println("4 - Proceed To Checkout");
		if (invoker.hasUndo()){
			System.out.println("99 - To Undo Previous Command");
		}

		System.out.println("0 - Exit Store");
	}

	private void displayStoreItems() {
		System.out.printf("%1s  %-15s   %-7s   %-6s%n","ID", "Shop ID", "Name", "Price");
		System.out.println("---------------------------------------------------");


		for (Item item: mItems){
			item.display();
		}
	}

	private void addItemToCart(Scanner scanner, Invoker invoker) {
		System.out.println("Enter Item ID : ");
		displayStoreItems();

		int itemId = scanner.nextInt();
		Item selectedItem = null;

		for (Item item : mItems){
			if (item.getId() == itemId){
				selectedItem = item;
			}
		}

		if (selectedItem != null){
			AddToCartCommand addToCartCommand = new AddToCartCommand(this, mCurrentCustomer.getShoppingCart(), selectedItem);
			invoker.execute(addToCartCommand);
		}else{
			System.out.println("Sorry Item Is Not Found.");
		}
	}

	private void onCheckout(Scanner scanner, Invoker invoker) {
		if (mCurrentCustomer.getShoppingCart().getItems().isEmpty()){
			System.out.println("Your Shopping Cart is empty.");
			return;
		}
		System.out.println("Your Shopping Cart Items : ");
		mCurrentCustomer.getShoppingCart().displayItems();
		double totalPrice = mCurrentCustomer.getShoppingCart().getTotalPrice();
		System.out.println("Total Price : " + totalPrice);
		System.out.print("Are you sure you want to checkout: Yes/No");

//		consumes the leftover line before getting the user response
		scanner.nextLine();
		String option = scanner.nextLine();


		if (option.equals("Yes")){
			CheckoutCommand checkoutCommand = new CheckoutCommand(this, mCurrentCustomer.getShoppingCart());
			invoker.execute(checkoutCommand);
		}else if (option.equals("No")){
			System.out.println("Continue Your Shopping Experience");
		}

	}
}
