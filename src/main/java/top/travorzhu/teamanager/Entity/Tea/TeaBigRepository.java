package top.travorzhu.teamanager.Entity.Tea;

import org.springframework.data.jpa.repository.JpaRepository;
import top.travorzhu.teamanager.Entity.Factory.Factory;

import java.util.List;

public interface TeaBigRepository extends JpaRepository<TeaBig,String>{
    List<TeaBig> findAllByFactory(Factory factory);
}
