package data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "shop_name", length = 30, nullable = false)
    private String shop_name;
    @Column(name = "shop_description")
    private String shop_description;
    @OneToMany(targetEntity=Buyer.class)
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

    @OneToMany(mappedBy="id")
    public Set getBuyers() {
        return buyers;
    }

    public void setBuyers(Set buyers) {
        this.buyers = buyers;
    }
}

