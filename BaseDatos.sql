-- Creación de las bases de datos
CREATE DATABASE clienteservicio;
CREATE DATABASE cuentaservicio;

-- Conéctate a la base de datos clienteservicio
\c clienteservicio;

-- Table: clientes
CREATE TABLE clientes (
    clienteid BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255),
    edad INT NOT NULL,
    identificacion VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    contraseña VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);

-- Conéctate a la base de datos cuentaservicio
\c cuentaservicio;

-- Table: cuentas
CREATE TABLE cuentas (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(255) NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT NOT NULL
);

-- Table: movimientos
CREATE TABLE movimientos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);