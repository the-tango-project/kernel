package org.apeiron.kernel.security;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

/**
 * Utility class for Spring Security.
 * FIXME: Refactorizar para que no dependa del cvu y otros propiedades
 */
public final class SecurityUtils {

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    public static final String AUTHORITIES_KEY = "auth";

    public static final String CLAIMS_NAMESPACE = "https://www.jhipster.tech/";
    public static final String CLAIMS_NAMESPACE_ROLES = CLAIMS_NAMESPACE + "roles";

    private static final String CVU_KEY = "cvu";
    private static final String PREFERRED_USERNAME_KEY = "preferred_username";

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Mono<String> getCurrentUserLogin() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> Mono.justOrEmpty(extractPrincipal(authentication)));
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }

    /**
     * Recupera el CVU del usuario actual.
     *
     * @return CVU del usuario (en caso de ser de MIIC), null en otro caso.
     */
    public static Mono<String> getCurrentCvu() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> Mono.justOrEmpty(extractCvu(authentication)));
    }

    private static String extractCvu(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else {
            var principal = authentication.getPrincipal();

            if (principal instanceof Jwt) {
                Map<String, Object> claims = ((Jwt) principal).getClaims();
                if (claims.containsKey(CVU_KEY)) {
                    return (String) claims.get(CVU_KEY);
                }
            } else if (principal instanceof DefaultOidcUser) {
                Map<String, Object> attributes = ((DefaultOidcUser) principal).getAttributes();
                if (attributes.containsKey(CVU_KEY)) {
                    return (String) attributes.get(CVU_KEY);
                }
            }
            return null;
        }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static Mono<Boolean> isAuthenticated() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .map(authorities -> authorities.stream().map(GrantedAuthority::getAuthority)
                        .noneMatch(AuthoritiesConstants.ANONYMOUS::equals));
    }

    /**
     * Checks if the current user has any of the authorities.
     *
     * @param authorities the authorities to check.
     * @return true if the current user has any of the authorities, false otherwise.
     */
    public static Mono<Boolean> hasCurrentUserAnyOfAuthorities(String... authorities) {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .map(authorityList -> authorityList
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(authority -> Arrays.asList(authorities).contains(authority)));
    }

    /**
     * Checks if the current user has none of the authorities.
     *
     * @param authorities the authorities to check.
     * @return true if the current user has none of the authorities, false
     *         otherwise.
     */
    public static Mono<Boolean> hasCurrentUserNoneOfAuthorities(String... authorities) {
        return hasCurrentUserAnyOfAuthorities(authorities).map(result -> !result);
    }

    /**
     * Checks if the current user has a specific authority.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static Mono<Boolean> hasCurrentUserThisAuthority(String authority) {
        return hasCurrentUserAnyOfAuthorities(authority);
    }

    public static List<GrantedAuthority> extractAuthorityFromClaims(Map<String, Object> claims) {
        return mapRolesToGrantedAuthorities(getRolesFromClaims(claims));
    }

    @SuppressWarnings("unchecked")
    private static Collection<String> getRolesFromClaims(Map<String, Object> claims) {
        if (claims.containsKey("groups")) {
            return (Collection<String>) claims.get("groups");
        } else if (claims.containsKey("roles")) {
            return (Collection<String>) claims.get("roles");
        } else if (claims.containsKey(CLAIMS_NAMESPACE_ROLES)) {
            return (Collection<String>) claims.get(CLAIMS_NAMESPACE_ROLES);
        } else if (claims.containsKey("realm_access")) {
            return getRolesFromClaims((Map<String, Object>) claims.get("realm_access"));
        } else {
            return new ArrayList<>();
        }
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(Collection<String> roles) {
        return roles.stream().filter(role -> role.startsWith("ROLE_")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
