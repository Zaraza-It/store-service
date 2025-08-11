package nocast.storeservice.product.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import nocast.storeservice.product.persistence.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findByIdAndActive(Long id, boolean isActive);

    @NonNull
    Page<Product> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    Page<Product> findById(Long id, Pageable pageable);
}
