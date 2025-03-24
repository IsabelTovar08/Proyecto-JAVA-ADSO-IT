CREATE TABLE cargo (
    cargo_id INT AUTO_INCREMENT PRIMARY KEY,
    cargo_nombre VARCHAR(100) NOT NULL
);

CREATE TABLE metodoPago (
    metodo_pago_id INT AUTO_INCREMENT PRIMARY KEY,
    metodo_pago_nombre VARCHAR(100) NOT NULL
);

CREATE TABLE categoria (
    categoria_id INT AUTO_INCREMENT PRIMARY KEY,
    categoria_nombre VARCHAR(100) NOT NULL
);

CREATE TABLE servicios (
    servicio_id INT AUTO_INCREMENT PRIMARY KEY,
    servicio_nombre VARCHAR(100) NOT NULL,
    precio_servicio DECIMAL(10,2) NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);


CREATE TABLE sucursal (
    sucursal_id INT AUTO_INCREMENT PRIMARY KEY,
    sucursal_nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(10) NOT NULL
);

CREATE TABLE persona (
    persona_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    email VARCHAR(150) NOT NULL,
    direccion VARCHAR(150) NOT NULL
);

CREATE TABLE empleados (
    empleado_id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL,
    cargo_id INT NOT NULL,
    sucursal_id INT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id),
    FOREIGN KEY (cargo_id) REFERENCES cargo(cargo_id)
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)
);

CREATE TABLE clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id)
);


CREATE TABLE reserva (
    reserva_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    precio_sin_descuento DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) NOT NULL,
    metodo_pago_id INT NOT NULL,
    sucursal_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (metodo_pago_id) REFERENCES metodoPago(metodo_pago_id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)

);

CREATE TABLE detalleReserva (
    detalle_reserva_id INT AUTO_INCREMENT PRIMARY KEY,
    reserva_id INT NOT NULL,
    servicio_id INT NOT NULL,
    empleado_id INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    hora_reserva TIME NOT NULL,
    precio_sin_descuento DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) NOT NULL,
    precio_final DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES reserva(reserva_id),
    FOREIGN KEY (servicio_id) REFERENCES servicios(servicio_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id)
);

