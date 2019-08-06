package dojo.supermarket.model;

public class ThreeForTwo extends Discount {
    public ThreeForTwo(Product p, double quantity, double unitPrice) {
        super(p, "3 for 2", quantity * unitPrice - (((quantity / 3) * 2 * unitPrice) + quantity % 3 * unitPrice));
    }
}
