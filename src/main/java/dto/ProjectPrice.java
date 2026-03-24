package dto;

public class ProjectPrice {
    private int id;
    private double price;

    public ProjectPrice(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return "ProjectPrice{id=" + id + ", price=" + price + "}";
    }
}
