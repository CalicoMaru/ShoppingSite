package com.calicomaru.ShoppingSite.repository;

import com.calicomaru.ShoppingSite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String Name);

    @Query(value = "SELECT * FROM product WHERE in_stock != false", nativeQuery = true)
    List<Product> findInStock();

    @Query(value = "SELECT * FROM product WHERE in_stock = false", nativeQuery = true)
    List<Product> findOutOfStock();

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE product SET in_stock = false WHERE id = :id"
            ,
            nativeQuery = true)
    void deleteProductHide(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE product SET in_stock = true WHERE id = :id"
            ,
            nativeQuery = true)
    void restoreProductHide(@Param("id") Integer id);

}
