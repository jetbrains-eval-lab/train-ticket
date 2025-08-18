package order.repository;

import order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author fdse
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Override
    Optional<Order> findById(String id);

    @Override
    ArrayList<Order> findAll();

    ArrayList<Order> findByAccountId(String accountId);

    ArrayList<Order> findByTravelDateAndTrainNumber(String travelDate,String trainNumber);
    
    @Query("SELECT o FROM Order o WHERE o.accountId = :accountId AND o.travelDate >= :startDate AND o.travelDate <= :endDate")
    ArrayList<Order> findByAccountIdAndTravelDateBetween(@Param("accountId") String accountId, 
                                                        @Param("startDate") String startDate, 
                                                        @Param("endDate") String endDate);

    @Override
    void deleteById(String id);
}
