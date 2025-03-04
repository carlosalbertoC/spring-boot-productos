package com.miempresa.miapp.services;

import com.miempresa.miapp.models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  // Marca la clase como un servicio de negocio
public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();

    public ProductoService() {
        productos.add(new Producto(1L, "Laptop", 1200.0));
        productos.add(new Producto(2L, "Mouse", 25.0));
        productos.add(new Producto(3L, "Teclado", 45.0));
    }

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public Producto obtenerPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Producto agregarProducto(Producto producto) {
        productos.add(producto);
        return producto;
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = obtenerPorId(id);
        if (producto != null) {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
        }
        return producto;
    }

    public boolean eliminarProducto(Long id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }
}
