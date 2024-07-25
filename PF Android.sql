Create table enfermera(
idEnfermera varchar2(50) PRIMARY KEY,
nombreEnfermera varchar2(50) NOT NULL,
correoEnfermera varchar2(50)NOT NULL,
contrasena varchar2(250) NOT NULL,
telefonoEnfermera varchar2(50)NOT NULL
);

Create table paciente(
idPaciente Varchar2(50) PRIMARY KEY,
idEnfermera Varchar2(50) NOT NULL,
nombresPaciente varchar2(25) NOT NULL,
apellidosPaciente varchar2(25) NOT NULL,
edad INT NOT NULL,
enfermedad varchar2 (50) NOT NULL,
numeroHabitacion INT NOT  NULL,
numeroCama INT NOT NULL,
medicamentosAsignados varchar2(200) NOT NULL,
fechaIngreso varchar2(20) NOT NULL,
horaDeAplicacionDeMedicamentos varchar2(50) NOT NULL,
CONSTRAINT fk_idEnfermera FOREIGN KEY (idEnfermera) REFERENCES enfermera(idEnfermera)
);

select * from paciente;

drop table paciente;