package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.NewPassword;
import com.Api.HealthPlus.Payload.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public List<UserDto> getAllUser();
    public UserDto getUserById(int userId);
    public UserDto getUserByEmail(String email);
    public UserDto updatePassword(NewPassword newPassword);
    public UserDto updateUserData(UserDto userDto,int userId);

}
