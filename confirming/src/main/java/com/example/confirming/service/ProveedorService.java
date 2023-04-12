package com.example.confirming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.confirming.modelo.Proveedor;
import com.example.confirming.repository.IProveedorRepository;

@Service
public class ProveedorService implements IProveedorService{
	
	@Autowired
    private IProveedorRepository proveedorRepository;

	@Override
	@Transactional(readOnly = true)
    public List<Proveedor> obtenerProveedoresPorIdCliente(Long idCliente) {
        // Utilizar el repositorio para obtener la lista de proveedores por idCliente
        return proveedorRepository.findByIdCliente(idCliente);
    }
}
