package ac.csg.in2033.ipos.pu.prm;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Promotion {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // productId -> discount
    private Map<String, Double> productDiscounts = new HashMap<>();

    // Counters
    private int campaignClicks = 0;
    private Map<String, Integer> itemAddedCount = new HashMap<>();
    private Map<String, Integer> itemPurchasedCount = new HashMap<>();

    public Promotion(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // ===== LOGIC =====

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    public void addProduct(String productId, double discount) {
        productDiscounts.put(productId, discount);
        itemAddedCount.put(productId, 0);
        itemPurchasedCount.put(productId, 0);
    }

    public double getDiscount(String productId) {
        return productDiscounts.getOrDefault(productId, 0.0);
    }

    // ===== COUNTERS =====

    public void recordCampaignClick() {
        campaignClicks++;
    }

    public void recordItemAdded(String productId, int quantity) {
        itemAddedCount.put(productId,
                itemAddedCount.getOrDefault(productId, 0) + quantity);
    }

    public void recordItemPurchased(String productId, int quantity) {
        itemPurchasedCount.put(productId,
                itemPurchasedCount.getOrDefault(productId, 0) + quantity);
    }

    // ===== GETTERS =====

    public String getName() { return name; }

    public LocalDateTime getStartDate() { return startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public int getCampaignClicks() { return campaignClicks; }

    public Map<String, Double> getProductDiscounts() { return productDiscounts; }

    public Map<String, Integer> getItemAddedCount() { return itemAddedCount; }

    public Map<String, Integer> getItemPurchasedCount() { return itemPurchasedCount; }

    // ===== SETTERS (for modify feature) =====

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}