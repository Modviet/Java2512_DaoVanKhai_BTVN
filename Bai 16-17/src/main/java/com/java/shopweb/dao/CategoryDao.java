package com.java.shopweb.dao;

import com.java.shopweb.mapper.RowMapper;
import com.java.shopweb.model.dto.CategoryDTO;

import java.util.List;

public class CategoryDao extends BaseDao<CategoryDTO>{

    public List<CategoryDTO> findAll(){
        String sql = "SELECT id, name FROM categories WHERE parent_id IS NOT NULL ORDER BY name ASC";
        RowMapper<CategoryDTO> mapper = rs -> CategoryDTO.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
        return query(sql,mapper);
    }
}
