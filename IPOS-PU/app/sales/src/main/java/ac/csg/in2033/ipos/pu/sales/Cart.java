package ac.csg.in2033.ipos.pu.sales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping cart for the IPOS-PU online portal.
 *
 * Customers can add items from the merchant's catalogue, update quantities,
 * remove items, and see a running total. This is the implementation for the
 * AddToCart use case under IPOS-PU-SALES.
 */
public class Cart {

    /**
     * A single line item in the cart — one product plus how many the customer wants.
     */
    public static class CartItem {

        private final String productId;
        private final String productName;
        private final double unitPrice;
        private int quantity;

        public CartItem(String productId, String productName, double unitPrice, int quantity) {
            if (productId == null || productId.isBlank()) {
                throw new IllegalArgumentException("Product ID cannot be empty.");
            }
            if (unitPrice < 0) {
                throw new IllegalArgumentException("Unit price cannot be negative.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be at least 1.");
            }

            this.productId   = productId;
            this.productName = productName;
            this.unitPrice   = unitPrice;
            this.quantity    = quantity;
        }

        public String getProductId()   { return productId;   }
        public String getProductName() { return productName; }
        public double getUnitPrice()   { return unitPrice;   }
        public int    getQuantity()    { return quantity;    }

        /** The line total: unit price × quantity. */
        public double getLineTotal() {
            return unitPrice * quantity;
        }

        /** Used internally when updating the quantity of an existing item. */
        void setQuantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be at least 1.");
            }
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format(
                "[%s] %s  x%d  @ £%.2f  = £%.2f",
                productId, productName, quantity, unitPrice, getLineTotal()
            );
        }
    }
    

    private final List<CartItem> items = new ArrayList<>();

    /**
     * Adds a product to the cart.
     *
     * If the product is already in the cart, its quantity is increased rather
     * than adding a duplicate line — which is the natural thing to expect.
     *
     * @param productId   unique catalogue ID of the product
     * @param productName human-readable name shown to the customer
     * @param unitPrice   price per unit in GBP
     * @param quantity    how many units to add (must be ≥ 1)
     * @throws IllegalArgumentException if quantity < 1 or unitPrice < 0
     */
    public void addItem(String productId, String productName, double unitPrice, int quantity) {
        // Look for this product already sitting in the cart
        for (CartItem existing : items) {
            if (existing.getProductId().equals(productId)) {
                existing.setQuantity(existing.getQuantity() + quantity);
                return;
            }
        }

        // Not found — add it as a new line
        items.add(new CartItem(productId, productName, unitPrice, quantity));
    }

    /**
     * Updates the quantity of an item already in the cart.
     * Pass 0 (or use {@link #removeItem}) to take it out entirely.
     *
     * @param productId unique catalogue ID of the product to update
     * @param newQuantity the replacement quantity (must be ≥ 1)
     * @throws IllegalArgumentException if the product isn't in the cart
     */
    public void updateQuantity(String productId, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(productId);
            return;
        }
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
        throw new IllegalArgumentException("Product not found in cart: " + productId);
    }

    /**
     * Removes an item from the cart completely.
     *
     * @param productId unique catalogue ID of the product to remove
     * @throws IllegalArgumentException if the product isn't in the cart
     */
    public void removeItem(String productId) {
        boolean removed = items.removeIf(item -> item.getProductId().equals(productId));
        if (!removed) {
            throw new IllegalArgumentException("Product not found in cart: " + productId);
        }
    }

    /**
     * Empties the cart — useful if the session times out or the customer starts over.
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Returns an unmodifiable view of all items currently in the cart.
     * Use this to display the cart contents in the GUI.
     */
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /** @return true if the cart has no items in it. */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /** @return number of distinct product lines in the cart. */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates the total cost of everything in the cart before any discounts or tax.
     *
     * @return grand total in GBP
     */
    public double getSubtotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }

    /**
     * Applies a promotional discount to the subtotal.
     * This is intended for use with IPOS-PU-PRM promotion campaigns.
     *
     * @param discountPercent discount rate between 0 and 100
     * @return the amount saved (not the discounted total)
     * @throws IllegalArgumentException if the rate is outside 0–100
     */
    public double calculateDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100 percent.");
        }
        return getSubtotal() * (discountPercent / 100.0);
    }

    /**
     * Convenience: subtotal minus a given discount amount.
     *
     * @param discountAmount the pound value to subtract (e.g. from {@link #calculateDiscount})
     * @return total after discount, never negative
     */
    public double getTotalAfterDiscount(double discountAmount) {
        return Math.max(0.0, getSubtotal() - discountAmount);
    }

    /**
     * Checks whether a given product is already sitting in the cart.
     *
     * @param productId unique catalogue ID to check
     * @return true if the cart contains at least one unit of this product
     */
    public boolean containsProduct(String productId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Cart is empty.";
        }
        StringBuilder sb = new StringBuilder("Cart contents:\n");
        for (CartItem item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append(String.format("  Subtotal: £%.2f", getSubtotal()));
        return sb.toString();
    }
}
