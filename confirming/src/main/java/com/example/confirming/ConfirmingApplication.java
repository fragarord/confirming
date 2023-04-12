package com.example.confirming;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.confirming.modelo.Proveedor;
import com.example.confirming.service.IProveedorService;
import com.example.confirming.service.ProveedorService;

@SpringBootApplication
public class ConfirmingApplication implements CommandLineRunner {
	
	@Autowired
	private IProveedorService proveedorService;
	
	public static void main(String[] args) {
		SpringApplication.run(ConfirmingApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if (validarDatosDeEntreda(args)) {			
			//ConfigurableApplicationContext context = SpringApplication.run(ConfirmingApplication.class, args);
	        //ProveedorService proveedorService = context.getBean(ProveedorService.class);
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("Dato de entrada. Cliente: " + args[0]);
	        Long idCliente = Long.parseLong(args[0]); // id_cliente a consultar
	        List<Proveedor> proveedores = proveedorService.obtenerProveedoresPorIdCliente(idCliente);
	        escribirListaProveedoresEnArchivo(proveedores);
		}
		System.out.println("!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!");
		System.out.println("Proceso finalizado");
		System.out.println("!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!");
		System.exit(0);
    }
	
	private static boolean  validarDatosDeEntreda(String[] args) {
				
		if (args == null || args.length < 1) {
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("Error. Lista de parámetros vacía.");
			
			return false;
		}
		// ID de cliente
		if (args[0].isEmpty()) {
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("Error. El dato de ID de cliente es obligatorio.");
			
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
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!");
			System.out.println("No se han encontrado proveedores para el cliente.");
			return;
		}
    	
        String rutaArchivo = "proveedores.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Escribir cabeceras de columnas
            writer.println(String.format("%12s %-50s %-13s %10s","ID proveedor", "Nombre", "Fecha de alta", "ID cliente"));
            // Escribir datos de proveedores
            for (Proveedor proveedor : proveedores) {
                writer.println(String.format("%12s %-50s %-13s %10s",
                		proveedor.getIdProveedor(),
                		proveedor.getNombre(),
                        proveedor.getFechaAlta(),
                		proveedor.getIdCliente()));
            }
            writer.flush();
            File archivo = new File(rutaArchivo);
            String rutaAbsoluta = archivo.getAbsolutePath();
            System.out.println("Lista de proveedores escrita en el archivo: " + rutaAbsoluta);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

}
