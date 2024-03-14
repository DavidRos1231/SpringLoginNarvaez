package mx.edu.utez.sda.david8cjwt.repositories;

import mx.edu.utez.sda.david8cjwt.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    public Optional<UserInfo> getUserInfoByUsername(String username);
}
