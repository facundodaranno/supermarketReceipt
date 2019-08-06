package dojo.supermarket.model;

public class EmptyDiscount extends Discount {
    public EmptyDiscount(Product product) {
        super(product,
                "No discount for "+product.getName(),
                0);
    }
}
