package ac.csg.in2033.ipos.pu.prm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionServiceImplTest {

    private PromotionManager manager;
    private PromotionServiceImpl service;
    private Promotion promo;

    @BeforeEach
    void setUp() {
        manager = new PromotionManager();
        service = new PromotionServiceImpl(manager);

        promo = new Promotion(
                "TestPromo",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.2);
        manager.addPromotion(promo);
    }

    @Test
    void testRecordCampaignClick() {
        service.recordCampaignClick("TestPromo");
        service.recordCampaignClick("TestPromo");

        assertEquals(2, promo.getCampaignClicks());
    }

    @Test
    void testRecordItemAdded() {
        service.recordItemAdded("TestPromo", "item1", 3);

        assertEquals(3, promo.getItemAddedCount().get("item1"));
    }

    @Test
    void testRecordItemPurchased() {
        service.recordItemPurchased("TestPromo", "item1", 5);

        assertEquals(5, promo.getItemPurchasedCount().get("item1"));
    }

    @Test
    void testGetDiscountFromActivePromotion() {
        double discount = service.getDiscount("item1");

        assertEquals(0.2, discount);
    }

    @Test
    void testGetDiscountReturnsZeroIfNoMatch() {
        double discount = service.getDiscount("unknownItem");

        assertEquals(0.0, discount);
    }
}