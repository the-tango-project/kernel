package org.apeiron.kernel.service.mapper;

import java.util.*;
import java.util.stream.Collectors;
import org.apeiron.kernel.domain.Authority;
import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.service.dto.AdminUserDto;
import org.apeiron.kernel.service.dto.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity {@link User} and its Dto called {@link UserDto}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as
 * MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
public class DefaultUserMapper implements UserMapper {

    public List<UserDto> usersToUserDtos(List<User> users) {
        return users.stream().filter(Objects::nonNull).map(this::userToUserDto).toList();
    }

    public UserDto userToUserDto(User user) {
        return new UserDto(user);
    }

    public List<AdminUserDto> usersToAdminUserDtos(List<User> users) {
        return users.stream().filter(Objects::nonNull).map(this::userToAdminUserDto).toList();
    }

    public AdminUserDto userToAdminUserDto(User user) {
        return new AdminUserDto(user);
    }

    public List<User> userDtosToUsers(List<AdminUserDto> userDtos) {
        return userDtos.stream().filter(Objects::nonNull).map(this::userDtoToUser).toList();
    }

    public User userDtoToUser(AdminUserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setImageUrl(userDto.getImageUrl());
            user.setCreatedBy(userDto.getCreatedBy());
            user.setCreatedDate(userDto.getCreatedDate());
            user.setLastModifiedBy(userDto.getLastModifiedBy());
            user.setLastModifiedDate(userDto.getLastModifiedDate());
            user.setActivated(userDto.isActivated());
            user.setLangKey(userDto.getLangKey());
            Set<Authority> authorities = this.authoritiesFromStrings(userDto.getAuthorities());
            user.setAuthorities(authorities);
            return user;
        }
    }

    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesAsString != null) {
            authorities = authoritiesAsString
                    .stream()
                    .map(string -> {
                        Authority auth = new Authority();
                        auth.setName(string);
                        return auth;
                    })
                    .collect(Collectors.toSet());
        }

        return authorities;
    }

    public User userFromId(String id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    public UserDto toDtoId(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        return userDto;
    }

    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    public Set<UserDto> toDtoIdSet(Set<User> users) {
        if (users == null) {
            return Collections.emptySet();
        }

        Set<UserDto> userSet = new HashSet<>();
        for (User userEntity : users) {
            userSet.add(this.toDtoId(userEntity));
        }

        return userSet;
    }

    @Named("login")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    public UserDto toDtoLogin(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        return userDto;
    }

    @Named("loginSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    public Set<UserDto> toDtoLoginSet(Set<User> users) {
        if (users == null) {
            return Collections.emptySet();
        }

        Set<UserDto> userSet = new HashSet<>();
        for (User userEntity : users) {
            userSet.add(this.toDtoLogin(userEntity));
        }

        return userSet;
    }
}
