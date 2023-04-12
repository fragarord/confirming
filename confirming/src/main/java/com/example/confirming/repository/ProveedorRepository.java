package com.example.confirming.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.confirming.modelo.Proveedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ProveedorRepository implements IProveedorRepository{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> findByIdCliente(Long idCliente) {
		
		String sql = "select * from proveedores where id_cliente = " + idCliente;
        Query query = em.createNativeQuery(sql);
        List<Object[]> resultados = query.getResultList();
		
        List<Proveedor> listaProveedores = resultados.stream().map(elemento -> {
            Proveedor proveedor = new Proveedor();

            Long idProveedor = ((Number) elemento[0]).longValue();
            String nombre = (String) elemento[1];
            Date fecha = (Date) elemento[2];
            Long cliente = ((Number) elemento[3]).longValue();

            proveedor.setIdProveedor(idProveedor);
            proveedor.setNombre(nombre);
            proveedor.setFechaAlta(fecha);
            proveedor.setIdCliente(cliente);

            return proveedor;
        }).collect(Collectors.toList());

        
        return listaProveedores;
	}

}
