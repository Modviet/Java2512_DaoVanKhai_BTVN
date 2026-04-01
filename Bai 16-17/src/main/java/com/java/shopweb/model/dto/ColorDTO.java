package com.java.shopweb.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorDTO {

    private int id;
    private String colorCode;
    private String hexCode;
}
