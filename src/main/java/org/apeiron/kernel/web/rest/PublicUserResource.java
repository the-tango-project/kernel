package org.apeiron.kernel.web.rest;

import java.util.ArrayList;
import java.util.List;
import org.apeiron.kernel.domain.Authority;
import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.security.AuthoritiesConstants;
import org.apeiron.kernel.service.UserService;
import org.apeiron.kernel.service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class PublicUserResource {

    private final Logger log = LoggerFactory.getLogger(PublicUserResource.class);

    private final UserService userService;

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    public PublicUserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code GET /users} : get all users with only the public informations - calling this are allowed for anyone.
     *
     * @param request a {@link ServerHttpRequest} request.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    public Mono<ResponseEntity<Flux<UserDto>>> getAllPublicUsers(
        ServerHttpRequest request,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get all public User names");

        return userService
            .countManagedUsers()
            .map(total -> new PageImpl<>(new ArrayList<>(), pageable, total))
            .map(page -> PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.fromHttpRequest(request), page))
            .map(headers -> ResponseEntity.ok().headers(headers).body(userService.getAllPublicUsers(pageable)));
    }

    /**
     * {@code PUT /users/{login}/authorities} : update user authorities
     *
     * @param request  a {@link ServerHttpRequest} request.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         all users.
     */
    @PreAuthorize("hasRole('" + AuthoritiesConstants.ADMIN + "')")
    @PutMapping("/users/{login}/authorities")
    public Mono<ResponseEntity<User>> updateUserAuthorities(
        ServerHttpRequest request,
        @PathVariable(value = "login", required = true) final String login,
        @RequestBody List<Authority> authorities
    ) {
        log.debug("REST request to update User Authorities");

        return userService.updateUserAutorities(login, authorities).map(result -> ResponseEntity.ok().body(result));
    }

    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
    @GetMapping("/authorities")
    public Mono<List<String>> getAuthorities() {
        return userService.getAuthorities().collectList();
    }
}