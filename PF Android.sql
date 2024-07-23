Create table enfermera(
idEnfermera varchar2(50) PRIMARY KEY,
nombreEnfermera varchar2(50) NOT NULL,
correoEnfermera varchar2(50)NOT NULL,
contrasena varchar2(250) NOT NULL,
telefonoEnfermera varchar2(50)NOT NULL
);


CREATE TABLE paciente (
    idPaciente VARCHAR2(50) PRIMARY KEY,
    nombresPaciente VARCHAR2(25) NOT NULL,
    apellidosPaciente VARCHAR2(25) NOT NULL,
    edad INT NOT NULL,
    fechaIngreso VARCHAR2(20) NOT NULL,
    idHabitacion VARCHAR2(50),
    idEnfermedad VARCHAR2(50),
    FOREIGN KEY (idHabitacion) REFERENCES habitacion(idHabitacion),
    FOREIGN KEY (idEnfermedad) REFERENCES enfermedad(idEnfermedad)
);

CREATE TABLE habitacion (
    idHabitacion VARCHAR2(50) PRIMARY KEY,
    numeroHabitacion INT NOT NULL,
    numeroCama INT NOT NULL
);

CREATE TABLE enfermedad (
    idEnfermedad VARCHAR2(50) PRIMARY KEY,
    nombreEnfermedad VARCHAR2(50) NOT NULL
);

CREATE TABLE medicamento (
    idMedicamento VARCHAR2(50) PRIMARY KEY,
    nombreMedicamento VARCHAR2(50) NOT NULL
);

CREATE TABLE paciente_medicamento (
    idPaciente VARCHAR2(50),
    idMedicamento VARCHAR2(50),
    horaDeAplicacionDeMedicamentos VARCHAR2(50) NOT NULL,
    PRIMARY KEY (idPaciente, idMedicamento),
    FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente),
    FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento)
);