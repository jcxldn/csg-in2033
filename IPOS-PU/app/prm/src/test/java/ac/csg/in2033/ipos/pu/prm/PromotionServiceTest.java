package ac.csg.in2033.ipos.pu.prm;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionServiceTest {

    @Test
    void testRecordCampaignClick() {
        PromotionManager manager = new PromotionManager();
        PromotionServiceImpl service = new PromotionServiceImpl(manager);

        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        manager.addPromotion(promo);

        service.recordCampaignClick("TestPromo");
        service.recordCampaignClick("TestPromo");

        assertEquals(2, promo.getCampaignClicks());
    }

    @Test
    void testRecordItemAdded() {
        PromotionManager manager = new PromotionManager();
        PromotionServiceImpl service = new PromotionServiceImpl(manager);

        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.1);
        manager.addPromotion(promo);

        service.recordItemAdded("TestPromo", "item1", 4);

        assertEquals(4, promo.getItemAddedCount().get("item1"));
    }

    @Test
    void testRecordItemPurchased() {
        PromotionManager manager = new PromotionManager();
        PromotionServiceImpl service = new PromotionServiceImpl(manager);

        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.1);
        manager.addPromotion(promo);

        service.recordItemPurchased("TestPromo", "item1", 2);

        assertEquals(2, promo.getItemPurchasedCount().get("item1"));
    }

    @Test
    void testGetDiscountFromActivePromotion() {
        PromotionManager manager = new PromotionManager();
        PromotionServiceImpl service = new PromotionServiceImpl(manager);

        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.25);
        manager.addPromotion(promo);

        double discount = service.getDiscount("item1");

        assertEquals(0.25, discount);
    }

    @Test
    void testGetDiscountReturnsZeroIfNoActivePromotion() {
        PromotionManager manager = new PromotionManager();
        PromotionServiceImpl service = new PromotionServiceImpl(manager);

        double discount = service.getDiscount("item1");

        assertEquals(0.0, discount);
    }
}