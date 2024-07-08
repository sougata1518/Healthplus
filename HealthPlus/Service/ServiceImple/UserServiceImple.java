package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.User;
import com.Api.HealthPlus.Exception.ResourseAlreadyExit;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Exception.UserEmailNotFoundException;
import com.Api.HealthPlus.Payload.NewPassword;
import com.Api.HealthPlus.Payload.UserClientResponse;
import com.Api.HealthPlus.Payload.UserDto;
import com.Api.HealthPlus.Repositary.UserRepositary;
import com.Api.HealthPlus.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if(userRepositary.findByEmail(user.getEmail()) != null){
            throw new ResourseAlreadyExit("email id",user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveData = userRepositary.save(user);
        UserDto userData = modelMapper.map(saveData, UserDto.class);
        return userData;
    }



    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepositary.findAll();
        List<UserDto> userDtoList = userList
                .stream()
                .map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "User Id", userId));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepositary.findByEmail(email);
        if(user == null){
            throw new UserEmailNotFoundException("User","User Email",email);
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto updatePassword(NewPassword newPassword) {
        User user = userRepositary.findByEmail(newPassword.getEmail());
        user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
        User save = userRepositary.save(user);
        UserDto updateData = modelMapper.map(save, UserDto.class);
        return updateData;
    }

    @Override
    public UserDto updateUserData(UserDto userDto, int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "User Id", userId));
        User updatedData = modelMapper.map(userDto, User.class);
        user.setName(updatedData.getName());
        user.setEmail(updatedData.getEmail());
        user.setPhone(updatedData.getPhone());
        user.setAddress(updatedData.getAddress());
        user.setCity(updatedData.getCity());
        user.setState(updatedData.getState());
        user.setPin(updatedData.getPin());
        User updatedUserData = userRepositary.save(user);
        UserDto updatedUserDtoData = modelMapper.map(updatedUserData, UserDto.class);
        return updatedUserDtoData;
    }
}
