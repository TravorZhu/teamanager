package top.travorzhu.teamanager.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNRoleRepository extends JpaRepository<UserEntityRole,Integer> {
    UserEntityRole findByMyUserDetailId(int myUserDetailId);
}
