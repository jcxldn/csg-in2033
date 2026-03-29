package ac.csg.in2033.ipos.pu.prm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PromotionManager {

    private List<Promotion> promotions = new ArrayList<>();

    // CREATE
    public boolean addPromotion(Promotion newPromo) {
        for (Promotion p : promotions) {
            if (isOverlapping(p, newPromo)) {
                System.out.println("Conflict: overlapping campaign");
                return false;
            }
        }
        promotions.add(newPromo);
        return true;
    }

    // OVERLAP CHECK
    private boolean isOverlapping(Promotion p1, Promotion p2) {
        return !(p1.getEndDate().isBefore(p2.getStartDate()) ||
                p2.getEndDate().isBefore(p1.getStartDate()));
    }

    // VIEW ACTIVE
    public List<Promotion> getActivePromotions() {
        List<Promotion> active = new ArrayList<>();
        for (Promotion p : promotions) {
            if (p.isActive()) {
                active.add(p);
            }
        }
        return active;
    }

    // DELETE
    public void deletePromotion(String name) {
        promotions.removeIf(p -> p.getName().equals(name));
    }

    // TERMINATE EARLY
    public void terminatePromotion(String name) {
        for (Promotion p : promotions) {
            if (p.getName().equals(name)) {
                p.setEndDate(LocalDateTime.now());
            }
        }
    }

    // MODIFY DATES
    public void updateDates(String name, LocalDateTime newStart, LocalDateTime newEnd) {
        for (Promotion p : promotions) {
            if (p.getName().equals(name)) {
                p.setStartDate(newStart);
                p.setEndDate(newEnd);
            }
        }
    }

    public List<Promotion> getAllPromotions() {
        return promotions;
    }
}