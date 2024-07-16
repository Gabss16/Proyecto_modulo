Create table enfermera(
idEnfermara PRIMARY KEY,
nombreEnfermera varchar2(50) NOT NULL,
contrasena varchar2(250) NOT NULL
);

Create table paciente(
idPaciente PRIMARY KEY,
nombresPaciente varchar2(25) NOT NULL,
apellidosPaciente varchar2(25) NOT NULL,
edad INT NOT NULL,
enfermedad varchar2 (50) NOT NULL,
numeroHabitacion INT NOT  NULL,
numeroCama INT NOT NULL,
medicamentosAsignados varchar2(200) NOT NULL,
fechaIngreso varchar2(20) NOT NULL,
horaDeAplicacionDeMedicamentos varchar2(50) NOT NULL
);