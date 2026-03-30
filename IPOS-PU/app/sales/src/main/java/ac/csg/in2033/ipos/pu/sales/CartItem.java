package ac.csg.in2033.ipos.pu.sales;

// CartItem wraps a Product and tracks how many of it the customer wants.
// This is separate from the nested CartItem inside Cart.java.
// This version works directly with the Product class so the cart can access all product details.
public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.quantity = quantity;
    }

    // uses the discounted price if one has been set, otherwise just the regular price
    public double getEffectiveUnitPrice() {
        double discounted = product.getDiscountedPrice();
        double regular = product.getPrice();
        if (discounted < regular) {
            return discounted;
        }
        return regular;
    }

    public double getLineTotal() {
        return getEffectiveUnitPrice() * quantity;
    }

    public String getProductId() {
        return String.valueOf(product.getId());
    }

    public String getProductName() {
        return product.getName();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s  x%d  @ £%.2f  = £%.2f",
                product.getId(),
                product.getName(),
                quantity,
                getEffectiveUnitPrice(),
                getLineTotal());
    }
}
