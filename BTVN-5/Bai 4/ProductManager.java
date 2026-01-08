package com.example;

public class ProductManager {
    private Product[] storage = new Product[100];
    private int count = 0;

    public boolean themSanPham(Product newPorduct){
        if(count == 100){
            System.out.println("Kho chua hien tai da day hang !");
            return false;
        }
        storage[count++] = newPorduct;
        return true;

    }

    public boolean kiemTraMaHang(String id){
       for(int i = 0;i<count ; i++){
           if(storage[i].getId().equalsIgnoreCase(id)){
               return true;
           }
       }
       return false;
    }

    public void showAll(){
        for(int i =0;i<count ;i++){
            System.out.println("Toan bo kho hang : "+storage[i]);
        }
    }

    public Product sanPhamGiaRe(){
        if(count == 0){
            return null;
        }

        Product spGiaRe = storage[0];
        for (int i = 1;i<count;i++){
            if(storage[i].getPrice() < spGiaRe.getPrice()){
                spGiaRe = storage[i];
            }
        }
        return spGiaRe;
    }

    public double tongGiaTriKhoHang(){
        double total = 0;
        for(int i = 0;i<count ;i++){
            total += storage[i].getTotal();
        }
        return total;
    }

    public boolean capNhatSanPham(String key,Product newData){
        for(int i = 0;i < count ;i++){
            if(storage[i].getId().equalsIgnoreCase(key)
             || storage[i].getName().toLowerCase().contains(key.toLowerCase())){

                storage[i].setName(newData.getName());
                storage[i].setPrice(newData.getPrice());
                storage[i].setQuantity(newData.getQuantity());
                return true;
            }
        }
        return false;
    }

    public boolean xoaSanPham(String id){
        for(int i = 0;i<count;i++){
            if(storage[i].getId().equalsIgnoreCase(id)){
                for(int j = 1;j<count -1;j++){
                    storage[j] = storage[j+1];
                }
                storage[--count] = null;
                return true;
            }
        }
        return false;
    }

    public void sapXepSanPham(){
        for (int i = 0;i<count-1;i++){
            for (int j = i+1;j<count;j++){
                if(storage[i].getPrice() < storage[j].getPrice()){
                    Product temp = storage[i];
                    storage[i] = storage[j];
                    storage[j] = temp;
                }
            }
        }
    }
}
