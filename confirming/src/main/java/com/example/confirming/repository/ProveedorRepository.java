package com.example.confirming.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.confirming.modelo.Proveedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProveedorRepository implements IProveedorRepository{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> findByIdCliente(Long idCliente) {
		return em.createQuery("from proveedores where id_cliente = " + idCliente).getResultList();
	}

}
