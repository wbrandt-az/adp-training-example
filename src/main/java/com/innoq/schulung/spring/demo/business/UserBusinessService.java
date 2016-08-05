package com.innoq.schulung.spring.demo.business;

import com.innoq.schulung.spring.demo.domain.User;
import com.innoq.schulung.spring.demo.framework.BusinessLayerComponent;
import com.innoq.schulung.spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@BusinessLayerComponent
public class UserBusinessService {
    private UserRepository userRepository;

    @Autowired
    public UserBusinessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Cacheable("default")
    public List<User> findByStatus(String status) {
        System.out.println("in User Business service find By Status");
        return userRepository.findByStatus(status);
    }

    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @CacheEvict(value = "default", key = "#status")
    public void changeUserStatus(Long userId, String status) {
        User user = userRepository.getOne(userId);
        user.setStatus(status);
    }
}
