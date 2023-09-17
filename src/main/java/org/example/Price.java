package org.example;

public class Price {
    private int price;
    private int hour;

    public Price(int price, int hour){
        this.hour=hour;
        this.price=price;
    }
    public int getHour() {
        return hour;
    }

    public int getPrice() {
        return price;
    }

    public void printHour(){
        System.out.print(String.format("%02d", this.getHour()) + "-" + String.format("%02d", this.getHour()+1));
    }
}
