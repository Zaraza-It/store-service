package nocast.storeservice.cart.repository;

import com.querydsl.core.types.Predicate;
import nocast.storeservice.cart.persistence.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<CartItem, Long>, QuerydslPredicateExecutor<CartItem> {
    Page<CartItem> findAllByCartUserId(Long userid, Pageable pageable);
    Page<CartItem> findAllByCartUserId(Long userid, Pageable pageable, Predicate predicate);
    Optional<CartItem> findByIdAndCartUserId(Long id, Long userid);
}
