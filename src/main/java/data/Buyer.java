package data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "surname", length = 30, nullable = false)
    private String surname;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "patronymic", length = 30)
    private String patronymic;
    @Column(name = "gender", length = 10, nullable = false)
    private String gender;
    @Column(name = "nation", length = 30, nullable = false)
    private String nation;
    @Column(name = "height", nullable = false)
    private Integer height;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "phone_number", length = 15, nullable = false)
    private String phone_number;
    @Column(name = "post_code", length = 10, nullable = false)
    private String post_code;
    @Column(name = "country", length = 30, nullable = false)
    private String country;
    @Column(name = "region", length = 30)
    private String region;
    @Column(name = "district", length = 30)
    private String district;
    @Column(name = "city", length = 30, nullable = false)
    private String city;
    @Column(name = "street", length = 30, nullable = false)
    private String street;
    @Column(name = "building", length = 10, nullable = false)
    private String building;
    @Column(name = "flat")
    private Integer flat;
    @Column(name = "credit_card", length = 16)
    private String credit_card;
    @Column(name = "bank_account", length = 34)
    private String bank_account;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name="shop", referencedColumnName = "id")
    private Shop shop;

    public Buyer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
/*
    public data.Shop getShop() {
        return shop;
    }

    public void setShops(data.Shop shop) {
        this.shop = shop;
    }*/

    @Override
    public String toString() {
        return "data.Buyer{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthday=" + birthday +
                ", phone_number='" + phone_number + '\'' +
                ", post_code='" + post_code + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", credit_card='" + credit_card + '\'' +
                ", bank_account='" + bank_account + '\'' +
                ", shop=" + shop +
                '}';
    }
}

