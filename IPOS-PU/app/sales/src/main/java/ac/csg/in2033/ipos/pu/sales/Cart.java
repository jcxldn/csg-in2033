package ac.csg.in2033.ipos.pu.sales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping cart for the IPOS-PU online portal.
 * Customers can add items from the merchant's catalogue, update quantities,
 * remove items, and see a running total. This is the implementation for the
 * AddToCart use case under IPOS-PU-SALES.
 */

public class Cart {

    public static class CartItem {

        private final String productId;
        private final String productName;
        private final double unitPrice;
        private int quantity;
        private final boolean isMedicalGood;

        public CartItem(String productId, String productName, double unitPrice, int quantity, boolean isMedicalGood) {
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
            this.isMedicalGood = isMedicalGood;
        }

        public String getProductId()   { return productId;   }
        public String getProductName() { return productName; }
        public double getUnitPrice()   { return unitPrice;   }
        public int    getQuantity()    { return quantity;    }
        public boolean isMedicalGood()  { return isMedicalGood; }

        public double getLineTotal() {
            return unitPrice * quantity;
        }

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


    public void addItem(String productId, String productName, double unitPrice, int quantity, boolean isMedicalGood) {
        for (CartItem existing : items) {
            if (existing.getProductId().equals(productId)) {
                existing.setQuantity(existing.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItem(productId, productName, unitPrice, quantity, isMedicalGood));
    }

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


    public void removeItem(String productId) {
        boolean removed = items.removeIf(item -> item.getProductId().equals(productId));
        if (!removed) {
            throw new IllegalArgumentException("Product not found in cart: " + productId);
        }
    }


    public void clearCart() {
        items.clear();
    }


    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    public double getSubtotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }


    public double calculateDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100 percent.");
        }
        return getSubtotal() * (discountPercent / 100.0);
    }


    public double getTotalAfterDiscount(double discountAmount) {
        return Math.max(0.0, getSubtotal() - discountAmount);
    }

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
