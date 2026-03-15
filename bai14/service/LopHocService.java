package com.example.bai14.service;

import com.example.bai14.dao.LopHocDao;
import com.example.bai14.entity.LopHoc;

import java.util.List;

public class LopHocService {
    public void showAllLopHoc(){
        LopHocDao lopHocDao = new LopHocDao();
        List<LopHoc> lopHocs = lopHocDao.getAllClasses();
        for(LopHoc lopHoc : lopHocs){
            System.out.println("Ma lop : "+lopHoc.getMaLop()+" , Ten lop : "+lopHoc.getTenLop());
        }
    }
}
