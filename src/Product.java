public class Product {

    private String ID;
    private String name;
    private String description;
    private Double cost;

    public Product(String name, String description, String ID, Double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String toCSV() {
        return this.ID + ", " + this.name + ", " + this.description + ", " + this.cost;
    }
}