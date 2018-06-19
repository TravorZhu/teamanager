package top.travorzhu.teamanager.Entity.Tea;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeaSmallRepository extends JpaRepository<TeaSmall,String> {
    int countByTeaBigIdAndSaledIsTrue(String teabigid);
}
