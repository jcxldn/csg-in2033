package ac.csg.in2033.ipos.pu.prm;

public class TestPromotions {
    public static void main(String[] args) {
        // 1. Create the promotions table
        PromotionDatabase.createTable();

        // 2. Add some promotions
        PromotionDatabase.insertPromotion("Eid Sale", "20% off everything", 20);
        PromotionDatabase.insertPromotion("Ramadan Special", "15% off sweets", 15);

        // 3. Print all promotions
        System.out.println("All promotions:");
        for (String p : PromotionDatabase.getPromotions()) {
            System.out.println(p);
        }

        // 4. Delete a promotion
        PromotionDatabase.deletePromotion(1);

        // 5. Print again to see it removed
        System.out.println("\nAfter deleting ID=1:");
        for (String p : PromotionDatabase.getPromotions()) {
            System.out.println(p);
        }
    }
}