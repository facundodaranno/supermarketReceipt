package dojo.supermarket.model;

public class DiscountFactory {

    DiscountFactory(){

    }

    public Discount create(Product product,
            Offer offer,
            double quantity,
            double unitPrice){
        //NullPattern
        Discount discount = new EmptyDiscount(product);
        int quantityAsInt = (int) quantity;

        //Factory
        if (offer.offerType == SpecialOfferType.TwoForAmount && quantityAsInt >= 2) {
            discount = new TwoForAmountDiscount(product, offer.argument, quantity, unitPrice);
        }
        if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
            discount = new ThreeForTwo(product, quantity, unitPrice);
        }
        if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
            discount = new PercentDiscount(product, offer.argument, quantity, unitPrice);
        }
        if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
            discount = new FiveForAmountDiscount(product, offer.argument, quantity, unitPrice);
        }

        return discount;
    }
}
