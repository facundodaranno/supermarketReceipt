package dojo.supermarket.model;

public class FiveForAmountDiscount extends Discount {
    public FiveForAmountDiscount(Product p, double argument, double quantity,
            double unitPrice) {
        super(p, "5 for " + argument, getDiscountTotal(argument, quantity, unitPrice));
    }

    private static double getDiscountTotal(double argument, double quantity,
            double unitPrice) {
        return unitPrice * quantity - (argument * (quantity/5) + quantity % 5 * unitPrice);
    }

}
