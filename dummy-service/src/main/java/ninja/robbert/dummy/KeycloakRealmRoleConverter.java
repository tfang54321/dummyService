package ninja.robbert.dummy;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	
	
    public static final String REALM_ACCESS = "realm_access";
    public static final String ROLES = "roles";
    public static final String CLIENTNAME = "employee-service";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String RESOURCE_ACCESS = "resource_access";
    public static final String ACCOUNT = "account";

	@SuppressWarnings("unchecked")
	@Override
	   public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get(RESOURCE_ACCESS);
        Map<String, Object> account = (Map<String, Object>) realmAccess.get(CLIENTNAME);
        Object roleObjes =account.get(ROLES);
        
        Collection<GrantedAuthority> abc =((List<String>)account.get(ROLES)).stream()
        .map(roleName -> ROLE_PREFIX + roleName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
        return abc;
//        return ((List<String>)account.get(ROLES)).stream()
//            .map(roleName -> ROLE_PREFIX + roleName)
//            .map(SimpleGrantedAuthority::new)
//            .collect(Collectors.toList());
    }

}
