package com.example.confirming.service;

import java.util.List;

import com.example.confirming.modelo.Proveedor;

public interface IProveedorService {
	
	public List<Proveedor> obtenerProveedoresPorIdCliente(Long idCliente);

}
