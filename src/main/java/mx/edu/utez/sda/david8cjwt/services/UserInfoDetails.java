package mx.edu.utez.sda.david8cjwt.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sda.david8cjwt.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoDetails implements UserDetails {
    private String name;
    private String password;
    private boolean nonLocked;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UserInfo userInfo){
        name=userInfo.getUsername();
        password=userInfo.getPassword();
        nonLocked=userInfo.isNonLocked();
        authorities = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return nonLocked;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
