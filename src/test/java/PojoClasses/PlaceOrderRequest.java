package PojoClasses;

import java.util.List;

public class PlaceOrderRequest {

    private List<Orders> orders;

    public List<Orders>  getOrders(){
        return  orders;

    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
