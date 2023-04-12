DROP TABLE IF EXISTS proveedores;

CREATE TABLE proveedores (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  fecha_alta DATE,
  id_cliente INT
);

INSERT INTO proveedores (nombre, fecha_alta, id_cliente) VALUES
('Coca-cola', '2023/01/14', 5),
('Pepsi', '2023/02/15', 5),
('Redbull', '2023/03/16', 6);
