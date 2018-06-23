package top.travorzhu.teamanager.Entity.Tea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeaSmallRepository extends JpaRepository<TeaSmall,String> {
    int countByTeaBigIdAndSaledIsTrue(String teabigid);

    int countBySaledIsFalse();

    int countBySaledIsTrue();

    @Query(value = "select count(small.id) from tea_small as small WHERE TO_DAYS(NOW()) - TO_DAYS(small.sale_date) <= 30", nativeQuery = true)
    int countBySaledLastMonth();

    @Query(value = "select count(small.id) from tea_small as small,tea_big as big WHERE TO_DAYS(NOW()) - TO_DAYS(big.date) <= 30 and big.id=small.tea_big_id and big.factory_id=:id", nativeQuery = true)
    int countByDateLastMonthByFactoryId(@Param("id") int id);

    @Query(value = "select count(small.id) from tea_small as small,tea_big as big WHERE big.id=small.tea_big_id and big.factory_id = :id", nativeQuery = true)
    int countAllByFactoryId(@Param("id") int id);

    @Query(value = "select count(small.id) from tea_small as small,tea_big as big WHERE TO_DAYS(NOW()) - TO_DAYS(small.sale_date) <= 30 and big.id=small.tea_big_id and big.factory_id = :id", nativeQuery = true)
    int countBySaledLastMonthAndFactoryId(@Param("id") int id);

    @Query(value = "select count(small.id) from tea_small as small,tea_big as big WHERE small.saled=true and big.id=small.tea_big_id and big.factory_id = :id", nativeQuery = true)
    int countAllByFactoryIdAAndSaledIsTrue(@Param("id") int id);

    int countAllBySaledIsTrueAndSaleUserDetailId(int saleUserDetailId);

    @Query(value = "select count(small.id) from tea_small as small WHERE TO_DAYS(NOW()) - TO_DAYS(small.sale_date) <= 30 and small.sale_user_detail_id = :id", nativeQuery = true)
    int countAllBySaledIsTrueAndSaleUserDetailIdAndSaledLastMonth(@Param("id") int saleUserDetailId);
}
