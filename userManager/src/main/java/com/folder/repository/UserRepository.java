package com.folder.repository;

import com.folder.mapper.UserRowMapper;
import com.folder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM users ORDER BY id DESC";
        return jdbcTemplate.query(sql,new UserRowMapper());
    }

    public User findById(Integer id){
        String sql = "SELECT * FROM users WHERE id =?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper() , id);
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql,new UserRowMapper(),email);
        return users.isEmpty() ? null : users.get(0);
    }

    public void insert(User user){
        String sql = "INSERT INTO users (full_name,email,password_hash,birthday,gender,role,avatar)"+
                "VALUES(?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                user.getFullName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getBirthday(),
                user.getGender(),
                user.getRole(),
                user.getAvatar());
    }

    public void update(User user){
        String sql = "UPDATE users SET full_name=?,birthday=?,gender=?,role=?,avatar=?,updated_at=NOW() WHERE id=?";

        jdbcTemplate.update(sql,
                user.getFullName(),
                user.getBirthday(),
                user.getGender(),
                user.getRole(),
                user.getAvatar(),
                user.getId());
    }

    public void updatePassword(Integer id,String passwordHash){
        String sql = "UPDATE users SET password_hash=?, updated_at=NOW() WHERE id=?";
        jdbcTemplate.update(sql,passwordHash,id);
    }

    public void delete(Integer id){
        String sql = "DELETE FROM users WHERE id =?";
        jdbcTemplate.update(sql,id);
    }
}
