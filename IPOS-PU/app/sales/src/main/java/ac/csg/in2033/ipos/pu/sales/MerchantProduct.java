package ac.csg.in2033.ipos.pu.sales;

// Links a Product to the merchant who is selling it on IPOS-PU.
// Each merchant has their own stock of a product at potentially their own price, which is why this is separate from Product itself.
// Product just holds the base info.
public class MerchantProduct {

    private final String merchantId;
    private final String merchantName;
    private final Product product;
    private int stockAvailable;

    public MerchantProduct(String merchantId, String merchantName, Product product, int stockAvailable) {
        if (merchantId == null || merchantId.isBlank()) {
            throw new IllegalArgumentException("Merchant ID cannot be empty.");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (stockAvailable < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.product = product;
        this.stockAvailable = stockAvailable;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public Product getProduct() {
        return product;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        if (stockAvailable < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.stockAvailable = stockAvailable;
    }

    // reduces stock when a purchase goes through, returns false if not enough stock
    public boolean reduceStock(int quantity) {
        if (quantity > stockAvailable) {
            return false;
        }
        stockAvailable -= quantity;
        return true;
    }

    public boolean hasStock(int quantity) {
        return stockAvailable >= quantity;
    }

    // convenience method so caller doesn't need to go through getProduct() every time
    public String getProductName() {
        return product.getName();
    }

    public int getProductId() {
        return product.getId();
    }

    @Override
    public String toString() {
        return String.format("Merchant: %s | Product: [%d] %s | Stock: %d",
                merchantName,
                product.getId(),
                product.getName(),
                stockAvailable);
    }
}
