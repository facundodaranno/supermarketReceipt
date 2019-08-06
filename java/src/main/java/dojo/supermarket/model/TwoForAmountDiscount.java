package dojo.supermarket.model;

public class TwoForAmountDiscount extends Discount {
    public TwoForAmountDiscount(Product p, double argument, double quantity,
            double unitPrice) {
        super(p, "2 for " + argument,
                getDiscountN(
                    quantity,
                    unitPrice,
                    getTotal(argument, quantity, unitPrice)
                )
        );
    }

    private static double getTotal(double argument, double quantity,
            double unitPrice) {
        return argument * (quantity/2)  + quantity % 2 * unitPrice;
    }

    private static double getDiscountN(double quantity, double unitPrice,
            double total) {
        return unitPrice * quantity - total;
    }

}
