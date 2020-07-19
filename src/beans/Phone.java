package beans;

/**
 * @author azzhu
 * @create 2020-07-15 11:37:38
 */
public class Phone {
    private int id;
    private String phoneName;   //phone_name
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneName='" + phoneName + '\'' +
                ", price=" + price +
                '}';
    }
}
