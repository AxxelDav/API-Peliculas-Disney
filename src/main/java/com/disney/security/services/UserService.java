package com.disney.security.services;

import com.disney.security.entities.User;
import com.disney.security.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean existByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public void save(User user){
        userRepository.save(user);
    }

}
