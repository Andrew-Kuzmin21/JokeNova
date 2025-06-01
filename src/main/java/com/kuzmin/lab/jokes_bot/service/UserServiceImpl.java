package com.kuzmin.lab.jokes_bot.service;

import com.kuzmin.lab.jokes_bot.model.User;
import com.kuzmin.lab.jokes_bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
