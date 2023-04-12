package com.example.confirming;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.confirming.modelo.Proveedor;
import com.example.confirming.service.ProveedorService;

@SpringBootApplication
public class ConfirmingApplication {
	
	public static void main(String[] args) {
		
		if (validarDatosDeEntreda(args)) {
			ConfigurableApplicationContext context = SpringApplication.run(ConfirmingApplication.class, args);
	        ProveedorService proveedorService = context.getBean(ProveedorService.class);
	        Long idCliente = 5L; // id_cliente a consultar
	        List<Proveedor> proveedores = proveedorService.obtenerProveedoresPorIdCliente(idCliente);
	        escribirListaProveedoresEnArchivo(proveedores);
		}
		System.out.println("Proceso finalizado");
		System.exit(0);
	}
	
	private static boolean  validarDatosDeEntreda(String[] args) {
		
		if (args == null || args.length < 1) {
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("Error. Lista de parámetros vacía.");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			
			return false;
		}
		// ID de cliente
		if (args[0].isEmpty()) {
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("Error. El dato de ID de cliente es obligatorio.");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			
			return false;
			
			// TODO Validar el formato y tipo de los datos de entrada
			
		}
		
		return true;
	}
	
	private static List<Proveedor> obtenerProveedoresPorIdCliente(Long idCliente) {
		
		ProveedorService proveedorService = new ProveedorService();
		return proveedorService.obtenerProveedoresPorIdCliente(idCliente);
	}
	
	private static void escribirListaProveedoresEnArchivo(List<Proveedor> proveedores) {
		
		if (proveedores == null || proveedores.size() < 1) {
			System.out.print("No se han encontrado proveedores para el cliente.");
			return;
		}
    	
        String rutaArchivo = "proveedores.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Escribir cabeceras de columnas
            writer.println("ID proveedor\tNombre\tFecha de alta\tID cliente");
            // Escribir datos de proveedores
            for (Proveedor proveedor : proveedores) {
                writer.println(proveedor.getIdProveedor() + "\t" + proveedor.getNombre() + "\t"
                        + proveedor.getFechaAlta() + "\t" + proveedor.getIdCliente());
            }
            System.out.println("Lista de proveedores escrita en el archivo: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

}
