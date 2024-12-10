package gryta.jan.PAI_testjwt.service;
import java.util.ArrayList;

import gryta.jan.PAI_testjwt.model.UserDao;
import gryta.jan.PAI_testjwt.model.UserDto;
import gryta.jan.PAI_testjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import
        org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDao user = userDao.findByUsername(username);
        if (user == null) {
            throw new
                    UsernameNotFoundException("User not found with username: " +
                    username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),new ArrayList<>());
    }

    public UserDto save(UserDto user) {
        UserDao newUser = new UserDao();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        userDao.save(newUser);
        UserDao dao =  userDao.findByUsername(user.getUsername());
        UserDto dto = new UserDto();
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        return  dto;
    }
}