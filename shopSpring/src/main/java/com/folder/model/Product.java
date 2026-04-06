package com.folder.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Product {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Timestamp createdAt;
    private Integer createdBy;
    private Timestamp updatedAt;
    private Integer updatedBy;

    private String categoryName;
}
