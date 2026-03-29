package ac.csg.in2033.ipos.pu.prm;

public class PromotionServiceImpl implements PromotionService {

    private PromotionManager manager;

    public PromotionServiceImpl(PromotionManager manager) {
        this.manager = manager;
    }

    @Override
    public void recordCampaignClick(String campaignName) {
        for (Promotion p : manager.getAllPromotions()) {
            if (p.getName().equals(campaignName)) {
                p.recordCampaignClick();
            }
        }
    }

    @Override
    public void recordItemAdded(String campaignName, String productId, int quantity) {
        for (Promotion p : manager.getAllPromotions()) {
            if (p.getName().equals(campaignName)) {
                p.recordItemAdded(productId, quantity);
            }
        }
    }

    @Override
    public void recordItemPurchased(String campaignName, String productId, int quantity) {
        for (Promotion p : manager.getAllPromotions()) {
            if (p.getName().equals(campaignName)) {
                p.recordItemPurchased(productId, quantity);
            }
        }
    }

    @Override
    public double getDiscount(String productId) {
        for (Promotion p : manager.getActivePromotions()) {
            double discount = p.getDiscount(productId);
            if (discount > 0) return discount;
        }
        return 0;
    }
}