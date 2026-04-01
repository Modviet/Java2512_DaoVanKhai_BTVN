package com.java.shopweb.dao;

import com.java.shopweb.mapper.RowMapper;
import com.java.shopweb.model.dto.ColorDTO;

import java.util.List;

public class ColorDao extends BaseDao<ColorDTO> {

    public List<ColorDTO> findAll(){
        String sql = "SELECT id,color_code,hex_code FROM colors ORDER BY color_code ASC";
        RowMapper<ColorDTO> mapper = rs -> ColorDTO.builder()
                .id(rs.getInt("id"))
                .colorCode(rs.getString("color_code"))
                .hexCode(rs.getString("hex_code"))
                .build();
        return query(sql,mapper);
    }
}
