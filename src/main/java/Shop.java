import java.util.HashSet;
import java.util.Set;

public class Shop {
    private Long id;
    private String shop_name;
    private String shop_description;
    private Set buyers = new HashSet();

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_description() {
        return shop_description;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }

    public Set getBuyers() {
        return buyers;
    }

    public void setBuyers(Set buyers) {
        this.buyers = buyers;
    }
}

