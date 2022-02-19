package com.mvl;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoffeeMachine {
    enum Action {
        BUY, FILL, TAKE, REMAINING, EXIT, PASS
    }

    enum State {
        CHOOSING_ACTION, CHOOSING_COFFEE, FILLING
    }

    enum Coffee {
        ESPRESSO, LATTE, CAPPUCCINO
    }

    enum FillingStage {
        FILLING_WATER, FILLING_MILK, FILLING_COFFEE_BEANS, FILLING_DISPOSABLE_CUPS
    }

    private final List<String> actionNamesList = Arrays.stream(Action.values())
            .map(Enum::name)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;
    private State machineState = State.CHOOSING_ACTION;
    private FillingStage fillingStage = FillingStage.FILLING_WATER;
    private boolean isActive = true;

    public CoffeeMachine() {
        printActionList();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (coffeeMachine.isActive())
            coffeeMachine.doAction(input.nextLine());
    }

    public boolean isActive() {
        return isActive;
    }

    private void printActionList() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public void doAction(String input) {
        switch (machineState) {
            case CHOOSING_ACTION:
                Action action = determineAction(input);
                switch (action) {
                    case BUY:
                        System.out.println("What do you want to buy? 1 - espresso, " +
                                "2 - latte, 3 - cappuccino, back - to main menu:");
                        machineState = State.CHOOSING_COFFEE;
                        break;
                    case FILL:
                        machineState = State.FILLING;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                        break;
                    case TAKE:
                        take();
                        break;
                    case REMAINING:
                        printCoffeeMachineInfo();
                        break;
                    case EXIT:
                        isActive = false;
                        break;
                    case PASS:
                        System.out.println("Check if the command you entered is correct");
                        break;
                }
                break;
            case CHOOSING_COFFEE:
                if (input.matches("[1-3]")) {
                    int coffeeIndex = Integer.parseInt(input) - 1;
                    buy(Coffee.values()[coffeeIndex]);
                    machineState = State.CHOOSING_ACTION;
                } else if (input.contains("back")) {
                    machineState = State.CHOOSING_ACTION;
                    System.out.println("Back to main menu");
                } else {
                    System.out.println("Impossible variant. Chose one of the following: 1 - espresso, " +
                            "2 - latte, 3 - cappuccino, back - to main menu: ");
                }
                break;
            case FILLING:
                if (input.matches("\\d+"))
                    fill(Integer.parseInt(input));
                else
                    System.out.println("Required number");
                break;
        }
        if (machineState.equals(State.CHOOSING_ACTION) && isActive)
            printActionList();
    }

    private void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }

    private void fill(int number) {
        switch (fillingStage) {
            case FILLING_WATER:
                water += number;
                fillingStage = FillingStage.FILLING_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case FILLING_MILK:
                milk += number;
                fillingStage = FillingStage.FILLING_COFFEE_BEANS;
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case FILLING_COFFEE_BEANS:
                coffeeBeans += number;
                fillingStage = FillingStage.FILLING_DISPOSABLE_CUPS;
                System.out.println("Write how many disposable cups of coffee you want to add:");
                break;
            case FILLING_DISPOSABLE_CUPS:
                disposableCups += number;
                fillingStage = FillingStage.FILLING_WATER;
                machineState = State.CHOOSING_ACTION;
                break;
        }
    }

    private Action determineAction(String action) {
        action = action.toUpperCase(Locale.ROOT);
        if (actionNamesList.contains(action))
            return Action.valueOf(action);
        else
            return Action.PASS;
    }

    private void buy(Coffee typeOfCoffee) {
        if (isEnoughResourcesForMakingCoffee(typeOfCoffee)) {
            switch (typeOfCoffee) {
                case ESPRESSO: // espresso
                    water -= 250;
                    coffeeBeans -= 16;
                    disposableCups--;
                    money += 4;
                    break;
                case LATTE: // latte
                    water -= 350;
                    milk -= 75;
                    coffeeBeans -= 20;
                    disposableCups--;
                    money += 7;
                    break;
                case CAPPUCCINO: // cappuccino
                    water -= 200;
                    milk -= 100;
                    coffeeBeans -= 12;
                    disposableCups--;
                    money += 6;
                    break;
            }
        }
    }

    private void printCoffeeMachineInfo() {
        System.out.printf("The coffee machine has:\n" +
                        "%d ml of water \n" +
                        "%d ml of milk \n" +
                        "%d g of coffee beans \n" +
                        "%d disposable cups \n" +
                        "$%d of money \n",
                water,
                milk,
                coffeeBeans,
                disposableCups,
                money);
    }

    private boolean isEnoughResourcesForMakingCoffee(Coffee typeOfCoffee) {
        if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        boolean isEnoughWater = true;
        boolean isEnoughMilk = true;
        boolean isEnoughCoffeeBeans = true;

        switch (typeOfCoffee) {
            case ESPRESSO: // espresso
                if (water < 250)
                    isEnoughWater = false;
                if (coffeeBeans < 16)
                    isEnoughCoffeeBeans = false;
                break;
            case LATTE: // latte
                if (water < 350)
                    isEnoughWater = false;
                if (milk < 75)
                    isEnoughMilk = false;
                if (coffeeBeans < 20)
                    isEnoughCoffeeBeans = false;
                break;
            case CAPPUCCINO: // cappuccino
                if (water < 200)
                    isEnoughWater = false;
                if (milk < 100)
                    isEnoughMilk = false;
                if (coffeeBeans < 12)
                    isEnoughCoffeeBeans = false;
                break;
        }
        if (isEnoughWater && isEnoughMilk && isEnoughCoffeeBeans) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
        System.out.print("Sorry, not enough ");
        if (!isEnoughWater)
            System.out.println("water!");
        if (!isEnoughMilk)
            System.out.println("milk!");
        if (!isEnoughCoffeeBeans)
            System.out.println("coffee beans!");
        return false;
    }
}
