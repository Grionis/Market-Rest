package com.test.market.persistence.crud;

import com.test.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //@Query( value = "SELECT * FROM productos where id_categoria = ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategory);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int amountStock, boolean state);
}
