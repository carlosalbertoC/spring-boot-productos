package com.miempresa.miapp.repositories;

import com.miempresa.miapp.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
