package org.apeiron.kernel.service;

import java.util.List;
import java.util.Set;

import org.apeiron.kernel.domain.Authority;
import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.service.dto.AdminUserDto;
import org.apeiron.kernel.service.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    public Mono<User> activateRegistration(String key);

    public Mono<User> completePasswordReset(String newPassword, String key);

    public Mono<User> requestPasswordReset(String mail);

    public Mono<User> registerUser(AdminUserDto userDTO, String password);

    public Mono<User> createUser(AdminUserDto userDTO);

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Mono<AdminUserDto> updateUser(AdminUserDto userDTO);

    public Mono<Void> deleteUser(String login);

    /**
     * Update basic information (first name, last name, email, language) for the
     * current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     * @return a completed {@link Mono}.
     */
    public Mono<Void> updateUser(String firstName, String lastName, String email, String langKey, String imageUrl);

    /**
     * Update ahtorities for a user.
     *
     * @param login           the login of user.
     * @param autoritiesToAdd the list of autorities to add
     * @return a completed {@link Mono}.
     */
    public Mono<User> updateUserAutorities(String login, List<Authority> autoritiesToAdd);

    public Mono<Void> changePassword(String currentClearTextPassword, String newPassword);

    public Flux<AdminUserDto> getAllManagedUsers(Pageable pageable);

    public Flux<UserDto> getAllPublicUsers(Pageable pageable);

    public Mono<Long> countManagedUsers();

    public Mono<User> getUserWithAuthoritiesByLogin(String login);

    public Mono<User> getUserWithAuthorities();

    /**
     * Gets a list of all the authorities.
     * 
     * @return a list of all the authorities.
     */
    public Flux<String> getAuthorities();

    public void removeNotActivatedUsers();

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    public Flux<User> removeNotActivatedUsersReactively();

    /**
     * Returns the user from an OAuth 2.0 login or resource server with JWT.
     * Synchronizes the user in the local repository.
     *
     * @param authToken the authentication token.
     * @return the user from the authentication.
     */
    public Mono<AdminUserDto> getUserFromAuthentication(AbstractAuthenticationToken authToken);

    /**
     * Return new user
     * 
     * @param user
     * @return El usuaro actualizado o guardado
     */
    public Mono<User> saveUserOrUpdateAutorities(User user);

    /**
     * Return Remove autorities
     * 
     * @param user
     * @param authoritiesToRemove
     * @return El usuario con las autoridades removidas
     */
    public Mono<User> removeAuthorities(User user, Set<Authority> authoritiesToRemove);
}
