package org.example.nordicStew;

public class FireSause extends AdditiveDecorator {
    private static final int ADDITIONAL_PRICE = 10;
    private static final String ADDITION_NAME = "Огненный соус";

    public FireSause(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return wrappedDish.getDescription() + " + " + ADDITION_NAME;
    }

    @Override
    public int getPrice() {
        return wrappedDish.getPrice() + ADDITIONAL_PRICE;
    }
}
