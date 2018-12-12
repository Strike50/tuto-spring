package com.gfi.springit.service;

import com.gfi.springit.domain.User;
import com.gfi.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService;

    public UserService(RoleService roleService, UserRepository userRepository, BCryptPasswordEncoder encoder, MailService mailService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.mailService = mailService;
    }

    public User register(User user) {
        //take the password from the form and encode
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);

        //confirm password
        user.setConfirmPassword(secret);

        //assign a role to this user
        user.addRole(roleService.findByName("ROLE_USER"));

        //set an activation code
        user.setActivationCode(UUID.randomUUID().toString());

        //set disabled
        user.setEnabled(false);

        //save user
        save(user);

        //send the activation email
        sendActivationEmail(user);

        //return the user
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveUsers(User... users){
        for(User user : users) {
            logger.info("Saving User : " + user.getEmail());
            userRepository.save(user);
        }
    }

    public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user){
        mailService.sendWelcomeEmail(user);
    }

    public Optional<User> findByEmailAndActivationCode(String email,String activationCode){
        return userRepository.findByEmailAndActivationCode(email,activationCode);
    }
}
