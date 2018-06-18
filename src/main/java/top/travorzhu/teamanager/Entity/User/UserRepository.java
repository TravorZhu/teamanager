package top.travorzhu.teamanager.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUserDetail, Integer> {
    MyUserDetail findByUserName(String userName);
}
