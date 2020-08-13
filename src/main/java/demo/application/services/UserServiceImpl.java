package demo.application.services;

import demo.application.UserRegistrationDto;
import demo.application.entities.Role;
import demo.application.entities.User;
import demo.application.entities.UserPermissions;
import demo.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Long saveUser(UserRegistrationDto userRegistrationDto) {
        User user = User.builder().isEnabled(true)
                .name(userRegistrationDto.getName()).password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .mobileNumber(userRegistrationDto.getMobileNo()).email(userRegistrationDto.getEmailId())
                .address(userRegistrationDto.getAddress()).roles(Arrays.asList(new Role(UserPermissions.ROLE_MEMBER)))
                .build();

        user = userRepository.save(user);

        if (user.getId() != null) {
            return user.getId();
        } else {
            throw new RuntimeException("Error saving user");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Username or password invalid");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getRoles());
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }
}
