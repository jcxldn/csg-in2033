package ac.csg.in2033.ipos.pu.prm;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    @Test
    void testPromotionIsActive() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );

        assertTrue(promo.isActive());
    }

    @Test
    void testPromotionIsNotActive() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2)
        );

        assertFalse(promo.isActive());
    }

    @Test
    void testAddProductAndDiscount() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.15);

        assertEquals(0.15, promo.getDiscount("item1"));
    }

    @Test
    void testDefaultDiscountIsZero() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        assertEquals(0.0, promo.getDiscount("unknownItem"));
    }

    @Test
    void testCampaignClickCounter() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.recordCampaignClick();
        promo.recordCampaignClick();

        assertEquals(2, promo.getCampaignClicks());
    }

    @Test
    void testItemAddedCounter() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.1);
        promo.recordItemAdded("item1", 3);

        assertEquals(3, promo.getItemAddedCount().get("item1"));
    }

    @Test
    void testItemPurchasedCounter() {
        Promotion promo = new Promotion(
                "TestPromo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        promo.addProduct("item1", 0.1);
        promo.recordItemPurchased("item1", 5);

        assertEquals(5, promo.getItemPurchasedCount().get("item1"));
    }
}