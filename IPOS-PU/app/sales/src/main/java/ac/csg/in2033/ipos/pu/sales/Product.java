package ac.csg.in2033.ipos.pu.sales;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageURL;

    public Product(int id, String name, String description, double price, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}