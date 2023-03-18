package com.melvin.blogapi.Service;

import com.melvin.blogapi.Entity.UserEntity;
import com.melvin.blogapi.Exceptions.UserNotFoundException;
import com.melvin.blogapi.Model.User;
import com.melvin.blogapi.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{


     private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public User findUser(Integer id) {
        User user = new User();
        UserEntity userEntity = unWrap(userRepository.findById(id),id);

        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    public User findUser(String username) {
        User user = new User();
        UserEntity userEntity = unWrap(Optional.ofNullable(userRepository.findByUsername(username)),404);

        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    public UserEntity unWrap(Optional<UserEntity> userEntity ,Integer id){
        if(userEntity.isPresent()){
            return userEntity.get();
        }else {
            throw new UserNotFoundException(id);
        }
    }
}
