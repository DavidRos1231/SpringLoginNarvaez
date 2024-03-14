package mx.edu.utez.sda.david8cjwt.services;

import mx.edu.utez.sda.david8cjwt.entities.UserInfo;
import mx.edu.utez.sda.david8cjwt.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userInfoRepository.getUserInfoByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("usuario no encontrado")
                );
    }
}
