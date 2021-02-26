package com.simple.twitter.service.security;

import com.simple.twitter.config.jwt.JwtProvider;
import com.simple.twitter.enums.UserRole;
import com.simple.twitter.exception.BadRequestException;
import com.simple.twitter.exception.ResourceNotFoundException;
import com.simple.twitter.mappers.UserMapper;
import com.simple.twitter.model.User;
import com.simple.twitter.model.dto.user.AuthenticationToken;
import com.simple.twitter.model.dto.user.AuthenticationUser;
import com.simple.twitter.model.dto.user.RegistrationUserDto;
import com.simple.twitter.model.dto.user.UserDto;
import com.simple.twitter.repository.UserRepository;
import com.simple.twitter.service.UserServiceSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceSecurityImpl implements UserServiceSecurity {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(RegistrationUserDto newUser) {
        if(userRepository.findUserByUsername(newUser.getUsername())==null) {

            User user = User.builder()
                    .email(newUser.getEmail())
                    .userRole(UserRole.ROLE_USER.name())
                    .username(newUser.getUsername())
                    .deleteCheck(true)
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .build();

            log.info("Save new user {}",user);
            userRepository.save(user);
            return userMapper.toUserDto(user);
        }
          throw new BadRequestException("Username : "+
                  newUser.getUsername()+" was founded in database ");
    }

    private User findByUsernameAndPassword(String username, String password) {
        User findUser = userRepository.findUserByUsername(username);
        if (findUser != null) {
            if (passwordEncoder.matches(password, findUser.getPassword())) {
                return findUser;
            }
            else {
                throw new BadRequestException("Password : "+
                        password+" is invalid ");
            }
        }
        throw new ResourceNotFoundException("Username "
                +username+" not founded");
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public AuthenticationToken authorization(AuthenticationUser request) {
        User userFind = findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(userFind.getDeleteCheck()) {
            String token = jwtProvider.generateToken(userFind.getUsername());
            return new AuthenticationToken(token);
        }
        throw new BadRequestException("User with username : "
                +userFind.getUsername()+" was delete");
    }

    @Override
    public UserDto deleteUser(UserDto user) {
        User userDelete = userRepository.findUserById(user.getId());

        if(userDelete!=null){

            userDelete.setDeleteCheck(false);
            userDelete.setDateDelete(LocalDate.now());
            return userMapper.toUserDto(userRepository.save(userDelete));

        }

        throw new ResourceNotFoundException("User id : "+user.getId()+" not founded ");
    }


    @Override
    public UserDto reestablishUser(UserDto userDto) {

        User findUser = userRepository.findUserByUsername(userDto.getUsername());

        if(findUser!=null){
            deleteOneUser(findUser);
        }
        throw new ResourceNotFoundException("User with username : "
                +userDto.getUsername()+" not founded");
    }

    private UserDto deleteOneUser(User findUser){

     log.info("Date deleted user {}",findUser.getDateDelete());
     if(findUser.getDateDelete().getMonth().plus(6).getValue()>LocalDate.now().getMonth().getValue()) {

         findUser.setDeleteCheck(true);
         findUser.setDateDelete(null);

         return userMapper.toUserDto(userRepository.save(findUser));
     }
     else {
         throw new BadRequestException("Date not valid"+findUser.getDateDelete().getMonth());
      }
    }

    @Override
    public UserDto deleteCurrentUser(String username) {

        User findUser = userRepository.findUserByUsername(username);

        if(findUser!=null){
            deleteOneUser(findUser);
        }
        throw new ResourceNotFoundException("User with username : "
                +username+" not founded");
    }

    @Override
    public UserDto saveManager(RegistrationUserDto registrationUserDto) {
        if(userRepository.findUserByUsername(registrationUserDto.getUsername())==null) {

            User user = User.builder()
                    .email(registrationUserDto.getEmail())
                    .userRole(UserRole.ROLE_MANAGER.name())
                    .username(registrationUserDto.getUsername())
                    .deleteCheck(true)
                    .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                    .build();

            log.info("Save new manager {}",user);
            userRepository.save(user);
            return userMapper.toUserDto(user);
        }
        throw new BadRequestException("Username : "+
                registrationUserDto.getUsername()+" was founded in database ");
    }


}
