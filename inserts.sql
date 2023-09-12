-- Insertar datos en la tabla Banco
INSERT INTO Banco (idBanco, nombreBanco) VALUES (1000,'BBVA');
INSERT INTO Banco (idBanco, nombreBanco) VALUES (2000,'Banamex');
INSERT INTO Banco (idBanco, nombreBanco) VALUES (3000,'HSBC');

-- Insertar datos en la tabla Servicios
INSERT INTO Servicios (descripcion, Banco_idBanco) VALUES ('Agua', 1000);
INSERT INTO Servicios (descripcion, Banco_idBanco) VALUES ('Luz', 1000);
INSERT INTO Servicios (descripcion, Banco_idBanco) VALUES ('Internet', 3000);

-- Insertar datos en la tabla Usuario
INSERT INTO Usuario (nombre, aPaterno, aMaterno, Banco_idBanco) VALUES ('Juan', 'González', 'Pérez', 1000);
INSERT INTO Usuario (nombre, aPaterno, aMaterno, Banco_idBanco) VALUES ('María', 'López', 'Rodríguez', 1000);
INSERT INTO Usuario (nombre, aPaterno, aMaterno, Banco_idBanco) VALUES ('Carlos', 'Martínez', 'Sánchez', 2000);

select * from Usuario u 
-- Insertar datos en la tabla Cuenta
INSERT INTO Cuenta (saldo, Banco_idBanco, Usuario_idUsuario) VALUES (1500.00, 1000, 1);
INSERT INTO Cuenta (saldo, Banco_idBanco, Usuario_idUsuario) VALUES (2000.00, 1000, 2);
INSERT INTO Cuenta (saldo, Banco_idBanco, Usuario_idUsuario) VALUES (3000.00, 2000, 3);

select * from Cuenta c 

-- Insertar datos en la tabla Tarjeta
INSERT INTO Tarjeta (noTarjeta, fechaExpiracion, CVV, NIP, emisor, Cuenta_noCuenta, Cuenta_idBanco, Cuenta_idUsuario) VALUES ('0123456789012345', '2025-12-31', 123, 4567, 'VS', 1, 1000, 1);
INSERT INTO Tarjeta (noTarjeta, fechaExpiracion, CVV, NIP, emisor, Cuenta_noCuenta, Cuenta_idBanco, Cuenta_idUsuario) VALUES ('0000000000000000', '2024-10-31', 456, 7890, 'MC', 2, 1000, 2);
INSERT INTO Tarjeta (noTarjeta, fechaExpiracion, CVV, NIP, emisor, Cuenta_noCuenta, Cuenta_idBanco, Cuenta_idUsuario) VALUES ('9876543210987654', '2026-03-31', 789, 1234, 'AE', 3, 2000, 3);

select * from Tarjeta t 

select * from Movimientos


SELECT c.noCuenta
	,b.idBanco
	,b.nombreBanco
	,u.idUsuario
	,u.nombre
	,u.aPaterno
	,u.aMaterno
FROM Cuenta c
	,Tarjeta t
	,Banco b
	,Usuario u
WHERE c.Banco_idBanco = b.idBanco
	AND c.Usuario_idUsuario = u.idUsuario
	AND c.noCuenta = 456789
select c.noCuenta, b.idBanco, b.nombreBanco, u.idUsuario, u.nombre, u.aPaterno, u.aMaterno  from Cuenta c, Banco b, Usuario u  where c.Banco_idBanco = b.idBanco and c.Usuario_idUsuario = u.idUsuario and c.noCuenta = "+noTarjetaCuenta+" 