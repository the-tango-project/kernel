package org.apeiron.kernel.service.mapper;

import java.util.*;
import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.service.dto.AdminUserDto;
import org.apeiron.kernel.service.dto.UserDto;

public interface UserMapper {

    public List<UserDto> usersToUserDtos(List<User> users);

    public UserDto userToUserDto(User user);

    public List<AdminUserDto> usersToAdminUserDtos(List<User> users);

    public AdminUserDto userToAdminUserDto(User user);

    public List<User> userDtosToUsers(List<AdminUserDto> userDtos);

    public User userDtoToUser(AdminUserDto userDto);

    public User userFromId(String id);

    public UserDto toDtoId(User user);

    public Set<UserDto> toDtoIdSet(Set<User> users);

    public UserDto toDtoLogin(User user);

    public Set<UserDto> toDtoLoginSet(Set<User> users);

}
