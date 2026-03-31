package org.example.nordicStew;

public class DoubleVenison extends AdditiveDecorator {
    private static final int ADDITIONAL_PRICE = 20;
    private static final String ADDITION_NAME = "Двойная порция оленины";

    public DoubleVenison(Dish dish) {
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
