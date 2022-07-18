package com.test.market.persistence;

import com.test.market.domain.Product;
import com.test.market.domain.repository.ProductRepository;
import com.test.market.persistence.crud.ProductoCrudRepository;
import com.test.market.persistence.entity.Producto;
import com.test.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository  implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {

        List<Producto> productos = productCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);

        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity, boolean state) {

        Optional<List<Producto>> productos = productCrudRepository.findByCantidadStockLessThanAndEstado(quantity, state);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProduct){
        productCrudRepository.deleteById(idProduct);
    }

}
