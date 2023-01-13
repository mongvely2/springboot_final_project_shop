package com.example.shop.repository;

import com.example.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//    쿼리 커스터마이징 코드
    @Modifying
    @Query(value = "update ProductEntity p set p.productHits = p.productHits+1 where p.id=:paramId")
    void updateHits(@Param("paramId") Long id);
}
