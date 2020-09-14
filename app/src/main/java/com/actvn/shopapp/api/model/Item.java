package com.actvn.shopapp.api.model;

public class Item {
    private int id;
    private int name;
    private int cost;
    private int imge;

    public Item(int id, int name, int cost, int imge) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.imge = imge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getImge() {
        return imge;
    }

    public void setImge(int imge) {
        this.imge = imge;
    }
}
