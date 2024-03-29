package de.telran.eshopproject.repository;

import de.telran.eshopproject.model.Product;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository {

    Optional<Product> findProductById(long id);

    @Query(value = "select p from Product p where LOWER(p.b_dressType) LIKE LOWER(CONCAT('%', :type, '%'))")
    List<Product> findProductByB_DressType(@Param("type") String type);

    @Query(value = "select p from Product p where LOWER(p.a_sex)=lower(:sex) order by p.e_arrival desc ")
    Page<Product> findProductByA_sex(@Param("sex") String aex, Pageable paging);

    @Query(value = "select p from Product p where LOWER(p.e_arrival)=LOWER(:arrival) and LOWER(p.a_sex)=LOWER(:sex)")
    List<Product> findProductsByE_ArrivalAndA_Sex(@Param("arrival") String arrival, @Param("sex") String sex);
}
