package com.example.confirming.repository;

import java.util.List;

import com.example.confirming.modelo.Proveedor;


public interface IProveedorRepository {
	
	List<Proveedor> findByIdCliente(Long idCliente);
}
