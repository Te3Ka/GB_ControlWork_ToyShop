public class Toy {
    private int id;
    private String name;
    private int quantity;
    private double percent;

    public Toy(int id, String name, int quantity, double percent) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPercent() {
        return percent;
    }

    public void setDecreaseQuantity() {
        quantity--;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
