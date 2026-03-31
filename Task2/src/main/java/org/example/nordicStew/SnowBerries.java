package org.example.nordicStew;

public class SnowBerries extends AdditiveDecorator {
    private static final int ADDITIONAL_PRICE = 5;
    private static final String ADDITION_NAME = "Снежные ягоды";

    public SnowBerries(Dish dish) {
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
