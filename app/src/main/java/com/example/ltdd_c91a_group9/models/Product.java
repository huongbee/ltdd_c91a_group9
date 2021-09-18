package com.example.ltdd_c91a_group9.models;

import com.example.ltdd_c91a_group9.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private long id;
    private String name;
    private int image;
    private double price;
    private SaleOff saleOff;
    private int count;

    public Product(long id, String name, int image, double price, SaleOff saleOff, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.saleOff = saleOff;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SaleOff getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(SaleOff saleOff) {
        this.saleOff = saleOff;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static List<Product> getDataMock(){
        return new ArrayList<>(Arrays.asList(
                new Product(1,"iPhone 12 Pro Max 256GB", R.drawable.hinhiphone12,37490000,new SaleOff(1 , "Sale cho điện thoai iphone" , 5) , 1),
                new Product(2,"Xiaomi Mi 11 Lite", R.drawable.hinhxiaomi11,7990000,new SaleOff(6 , "Sale cho điện thoai xiaomi" , 1) , 1),
                new Product(3,"Samsung Galaxy Note 20", R.drawable.hinhsamsunggalaxynote20,15990000,new SaleOff(4 , "Sale cho điện thoai samsung" , 6) , 1),
                new Product(4,"Vivo V21 5G", R.drawable.hinhvivov21,9990000,new SaleOff(5 , "Sale cho điện thoai vivo" , 3) , 1),
                new Product(5,"Realme 8 Pro", R.drawable.hinhrealme8,8690000,new SaleOff(3 , "Sale cho điện thoai realme" , 2) , 1),
                new Product(6,"Xiami Redme 9T", R.drawable.hinhxiaomiredme9t,4290000,new SaleOff(3 , "Sale cho điện thoai realme" , 2) , 1),
                new Product(7,"Oppo A74 5G", R.drawable.hinhoppoa74,7490000,new SaleOff(2 , "Sale cho điện thoai oppo" , 0) , 1),
                new Product(8,"Samsung Galaxy A32", R.drawable.hinhsamsunggalaxya32,6490000,new SaleOff(4 , "Sale cho điện thoai samsung" , 6) , 1)

        ));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", saleOff=" + saleOff +
                ", count=" + count +
                '}';
    }
}
