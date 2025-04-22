create database if not exists universidad;
use universidad;

create table Alumno
(
    id int not null auto_increment,
    nombre varchar(20),
    fecha_nacimiento date,
    CRAEST double,
    activo bool,
    tipo_alumno  ENUM('PREUNI','PREGRADO', 'POSTGRADO'),
    primary key(id)
);
