# Coffee Machine
Project for JetBrains Academy

####About
The machine works with typical products: coffee, milk, sugar, and plastic cups; if it runs out of something, it shows a notification. You can get three types of coffee: espresso, cappuccino and latte. Since nothing’s for free, it also collects the money.


####Example 
Get information about coffee machine:

	Write action (buy, fill, take, remaining, exit): 
	>remaining
	
	The coffee machine has:
	400 ml of water
	540 ml of milk
	120 g of coffee beans
	9 disposable cups
	$550 of money

Buy cofee

	Write action (buy, fill, take, remaining, exit): 
	>buy
	
	What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: 
	2
	I have enough resources, making you a coffee!
	
	Write action (buy, fill, take, remaining, exit): 
	>remaining
	
	The coffee machine has:
	50 ml of water
	465 ml of milk
	100 g of coffee beans
	8 disposable cups
	$557 of money

Replenish the stock of coffee machine ingredients

	Write action (buy, fill, take, remaining, exit): 
	>fill
	
	Write how many ml of water you want to add: 
	>1000
	Write how many ml of milk you want to add: 
	>0
	Write how many grams of coffee beans you want to add: 
	>0
	Write how many disposable cups of coffee you want to add: 
	>0
	
	Write action (buy, fill, take, remaining, exit): 
	>remaining
	
	The coffee machine has:
	1050 ml of water
	465 ml of milk
	100 g of coffee beans
	8 disposable cups
	$557 of money

Take money from the coffee machine

	Write action (buy, fill, take, remaining, exit): 
	>take
	
	I gave you $557
	
	Write action (buy, fill, take, remaining, exit): 
	>remaining
	
	The coffee machine has:
	1050 ml of water
	465 ml of milk
	100 g of coffee beans
	8 disposable cups
	$0 of money



Finish the coffee machine

	Write action (buy, fill, take, remaining, exit): 
	>exit