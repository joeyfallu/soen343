package Product;

public class tv extends product{
    private int dimensions;

    public tv(int id, String model, double weight, double price, String brand, int dimensions){
        super(id, model, weight, price, brand);
        this.dimensions = dimensions;
    }

    public tv(){
        super();
        this.dimensions = 0;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getModel() + " price = " + getPrice() + " brand = " + getBrand() + " dimensions = " + getDimensions();
        return x;
    }

    public boolean equals(tv p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getDimensions() ==(p.getDimensions()));

    }

    public int getDimensions(){
        return dimensions;
    }

    public void setDimensions(int newDimensions){
        this.dimensions = newDimensions;
    }


}
