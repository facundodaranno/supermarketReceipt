package dojo.supermarket.model;

public class PercentDiscount extends Discount {
    public PercentDiscount(Product product, double argument, double quantity, double unitPrice) {
        super(product, argument + "% off", quantity * unitPrice * argument / 100.0);
    }
}
