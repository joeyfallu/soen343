package Product;

public class monitor extends product{
    private int size;

    public monitor(int id, String model, double weight, double price, String brand, int size){
        super(id, model, weight, price, brand);
        this.size = size;
    }

    public monitor(){
        super();
        this.size = 0;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getModel() + " price = " + getPrice() + " brand = " + getBrand() + " dimensions = " + getSize();
        return x;
    }

    public boolean equals(monitor p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getSize() == (p.getSize()));

    }

    public int getSize(){
        return size;
    }

    public void setSize(int newSize){
        this.size = newSize;
    }


}
