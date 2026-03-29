package ac.csg.in2033.ipos.pu.prm;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionManagerTest {

    @Test
    void testAddPromotionSuccess() {
        PromotionManager manager = new PromotionManager();

        Promotion promo = new Promotion(
                "Promo1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5)
        );

        assertTrue(manager.addPromotion(promo));
    }

    @Test
    void testOverlappingPromotionFails() {
        PromotionManager manager = new PromotionManager();

        Promotion p1 = new Promotion(
                "P1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5)
        );

        Promotion p2 = new Promotion(
                "P2",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(6)
        );

        manager.addPromotion(p1);
        boolean result = manager.addPromotion(p2);

        assertFalse(result);
    }

    @Test
    void testGetActivePromotions() {
        PromotionManager manager = new PromotionManager();

        Promotion activePromo = new Promotion(
                "Active",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );

        Promotion inactivePromo = new Promotion(
                "Inactive",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2)
        );

        manager.addPromotion(activePromo);
        manager.addPromotion(inactivePromo);

        assertEquals(1, manager.getActivePromotions().size());
    }

    @Test
    void testDeletePromotion() {
        PromotionManager manager = new PromotionManager();

        Promotion promo = new Promotion(
                "DeleteMe",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1)
        );

        manager.addPromotion(promo);
        manager.deletePromotion("DeleteMe");

        assertEquals(0, manager.getAllPromotions().size());
    }

    @Test
    void testTerminatePromotion() {
        PromotionManager manager = new PromotionManager();

        Promotion promo = new Promotion(
                "TerminateMe",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5)
        );

        manager.addPromotion(promo);
        manager.terminatePromotion("TerminateMe");

        assertFalse(promo.isActive());
    }
}