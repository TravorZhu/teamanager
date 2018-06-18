package top.travorzhu.teamanager.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole,Integer> {
    UserRole findByName(String name);
    UserRole findById(int id);
}
