DROP TABLE IF EXISTS proveedores;

CREATE TABLE proveedores (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  fecha_alta DATE,
  id_cliente INT
);

INSERT INTO proveedores (nombre, fecha_alta, id_cliente) VALUES
('Coca-cola', '01/01/2023', 5),
('Pepsi', '02/02/2023', 5),
('Redbull', '03/03/2023', 6);
