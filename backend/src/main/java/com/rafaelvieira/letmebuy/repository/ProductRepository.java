package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rafae
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = """
			SELECT DISTINCT tb_product.id, tb_product.name
			FROM tb_product
			INNER JOIN tb_product_category ON tb_product_category.product_id = tb_product.id
			WHERE (:categoryIds IS NULL OR tb_product_category.category_id IN :categoryIds)
			AND (LOWER(tb_product.name) LIKE LOWER(CONCAT('%',:name,'%')))
			ORDER BY tb_product.name
			""",
            countQuery = """
			SELECT COUNT(*) FROM (
			SELECT DISTINCT tb_product.id, tb_product.name
			FROM tb_product
			INNER JOIN tb_product_category ON tb_product_category.product_id = tb_product.id
			WHERE (:categoryIds IS NULL OR tb_product_category.category_id IN :categoryIds)
			AND (LOWER(tb_product.name) LIKE LOWER(CONCAT('%',:name,'%')))
			) AS tb_result
			""")
    Page<ProductProjection> searchProducts(List<Long> categoryIds, String name, Pageable pageable);

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj.id IN :productIds ORDER BY obj.name")
    List<Product> searchProductsWithCategories(List<Long> productIds);

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:nome% AND cat IN :categories")
    Page<Product> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,
            @Param("categories") List<Category> categories, Pageable pageRequest);

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
            + "(COALESCE(:categories) IS NULL OR cats IN :categories) AND "
            + "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))) ")
    Page<Product> find(List<Category> categories, String name, Pageable pageable);

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :products")
	void findProductsWithCategories(List<Product> products);
}
