package store.model;

import store.dao.CustomerDao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Customer {


    private int id;
    private String name;
    private Integer age;
    private Integer budget;
    private List<Item> wantToBuy;

    private String token;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlAttribute
    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @XmlElement(name = "item")
    public List<Item> getWantToBuy() {
        return wantToBuy;
    }

    public void setWantToBuy(List<Item> wantToBuy) {
        this.wantToBuy = wantToBuy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
