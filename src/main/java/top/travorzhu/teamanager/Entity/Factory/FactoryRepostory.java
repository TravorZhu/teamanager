package top.travorzhu.teamanager.Entity.Factory;

import org.springframework.data.jpa.repository.JpaRepository;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;

public interface FactoryRepostory extends JpaRepository<Factory,Integer>{
    Factory findByMyUserDetail(MyUserDetail myUserDetail);
}
