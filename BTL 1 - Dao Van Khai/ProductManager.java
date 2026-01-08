package com.example.BTL1;

public class ProductManager {

    private Product[][] storage = new Product[5][20];
    private int[] counts = new int[5];


    //Chuc nang 1 : Them san pham vao danh muc
    public String addProduct(int categorytId, Product product){
        StringBuilder sb = new StringBuilder();

        if(categorytId < 0 || categorytId > 4){
            return "Danh muc khong hop le";
        }

        if(counts[categorytId] == 20){
            return "Danh muc da day , Khong the them san pham";
        }

        for(int i = 0;i<counts[categorytId];i++){
            if(storage[categorytId][i].equals(product)){
                return "San pham da ton tai trong danh muc";
            }
        }

        storage[categorytId][counts[categorytId]] = product;
        counts[categorytId]++;

        sb.append("Da them san pham : ")
                .append(product.getName())
                .append(" vao danh muc ")
                .append(categorytId);

        return sb.toString();
    }

    //Chuc nang 2 : Tim kiem san pham theo ten
    public void searchByName(String keyword){
        keyword = keyword.toLowerCase();
        boolean found = false;

        for(int i= 0;i<5;i++){
            for(int j = 0;j<counts[i];i++){
                if(storage[i][j].getName().toLowerCase().contains(keyword)){
                    System.out.println(storage[i][j]);
                    found = true;
                }
            }
        } if(found != true){
            System.out.println("Khong tim thay san pham phu hop");
        }

    }

    //Chuc nang 3 : Tim kiem san pham theo ID
    public void deleteById(int id){
        for(int c = 0;c < 5;c++){
            for(int i =0 ;i<counts[c];i++){
                if(storage[c][i].getId() == id){

                    for( int k = i;k<counts[c]-1;k++){
                        storage[c][k] = storage[c][k+1];
                    }

                    storage[c][counts[c]-1] = null;
                    counts[c]--;

                    System.out.println("Da xoa thanh cong san pham va ID la: "+id);
                    return;
                }
            }
        }
        System.out.println("Khong thim thay san pham.");
    }

    //Chuc nang 4 : Sap xep theo gia giam dan
    public void sortByPriceDesc(){
        int total = 0;
        for(int i = 0;i<5;i++){
            total += counts[i];
        }

        if(total == 0){
            System.out.println("Kho hang trong.");
            return;
        }

        Product[] temp = new Product[total];
        int index = 0;

        for(int i = 0 ;i<5;i++){
            for(int j = 0;j<counts[i];j++){
                temp[index++] = storage[i][j];
            }
        }

        mergeSort(temp,0,temp.length-1);

        for(Product p : temp){
            System.out.println(p);
        }
    }

    // Ham sap xep cho mang 2 chieu :
    private void mergeSort(Product[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(Product[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Product[] L = new Product[n1];
        Product[] R = new Product[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].getPrice() >= R[j].getPrice()) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Chuc nang 5 : Thong ke san pham trong kho hang va in ra danh muc co gia tri lon nhat

    public void staticic(){
        double[] totals = new double[5];
        int maxIndex = 0;

        for(int i = 0;i<5;i++){
            for(int j = 0;j<counts[i];j++){
                totals[i] += storage[i][j].getPrice() * storage[i][j].getQuantity();
            }

            if(totals[i] > totals[maxIndex]){
                maxIndex = i;
            }
        }

        System.out.println("Danh muc | Tong gia tri ");
        for(int i = 0;i<5;i++){
            System.out.println(i+"         |  "+(long) totals[i]);
        }

        System.out.println("Danh muc co gia tri lon nhat : "+maxIndex);
    }
}
