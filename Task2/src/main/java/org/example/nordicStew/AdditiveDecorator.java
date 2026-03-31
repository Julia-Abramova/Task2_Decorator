package org.example.nordicStew;

public abstract class AdditiveDecorator implements Dish {
    private Dish wrappedDish;

    public AdditiveDecorator(Dish dish) {
        this.wrappedDish = dish;
    }

    @Override
    public String getDescription() {
        return wrappedDish.getDescription();
    }

    @Override
    public int getPrice() {
        return wrappedDish.getPrice();
    }
}
