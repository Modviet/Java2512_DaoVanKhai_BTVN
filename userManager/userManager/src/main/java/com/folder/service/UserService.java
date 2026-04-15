package com.folder.service;

import com.folder.model.User;
import com.folder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id);
    }

    public Map<String,String> create(User user,String password){
        Map<String,String> errors = new HashMap<>();

        if(user.getEmail() == null || user.getEmail().isEmpty()){
            errors.put("email","Email is required");
        } else if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email","Invalid email format");
        } else if(userRepository.findByEmail(user.getEmail()) != null){
            errors.put("email","Email already exists");
        }

        if(user.getFullName() == null || user.getFullName().isEmpty()){
            errors.put("fullName","Full name is required");
        }

        if(password == null || password.isEmpty()){
            errors.put("password","Password is required");
        }

        if(!errors.isEmpty()){
            return errors;
        }

        user.setPasswordHash(md5(password));

        if(user.getRole() == null || user.getRole().isEmpty()){
            user.setRole("USER");
        }
        userRepository.insert(user);
        return errors;

    }

    public Map<String,String> update(Integer id , User user,String password){
        Map<String,String> errors = new HashMap<>();

        User user1 = userRepository.findById(id);
        if(user1 == null){
            errors.put("general","User not found");
            return errors;
        }

        if(user.getFullName() == null || user.getFullName().isEmpty()){
            errors.put("fullName","Full name is required");
        }

        if(!errors.isEmpty()){
            return errors;
        }

        user.setId(id);
        userRepository.update(user);

        if(password != null && !password.isEmpty()){
            userRepository.updatePassword(id,md5(password));
        }
        return errors;
    }

    public void delete(Integer id){
        userRepository.delete(id);
    }

    public List<User> findByRole(String role){
        return userRepository.findByRole(role);
    }



    private String md5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1,messageDigest);
            return no.toString(16);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
