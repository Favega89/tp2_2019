# Ahorcado

## Base de datos
```sql
create table Register (
	id int unique not null primary key auto_increment,
	winner_name	varchar(30) not null,
	word	varchar(30) not null,
	fecha		date not null
);
```

### Diferencia entre implementar Runnable y extender de Thread
Implementar Runnable y extender de Thread realizan la misma tarea por detras solo que implementando de Runnable tenemos la posibilidad de a su vez extender de otra clase padre
que contenga logica necesaria para el desarrollo, es necesario hacer un @Override del metodo run(), extendiendo de Thread hacemos el run dentro de la clase,
es preferible Implementar Runnable para no limitar el extends dado que en Java no existe la herencia multiple.

### Ciclo de vida de un Thread
El thread empieza cuando se instancia con un new hasta que el mismo realiza un start, una vez que arrancó pasa a estado Runnable, se considera Runnable
cuando esta realizando la tarea, puede suceder que estemos utilizando multiples Threads por lo que aparece un nuevo estado Waiting, es aquel Thread que se
encuentra a la espera de que se libere el recurso compartido y se informe al mismo mediante un notify y luego una vez finalizado pasa a Terminated ya sea porque
finalizo o porque se interrumpio el mismo.

### Metodos de Thread
start : invoca al metodo run() que da comienzo a la ejecucion
yield : cede rendimiento de un hilo a otro hilo.
sleep : blockea el thread durante el tiempo enviado por parametro expresado en milisegundos
join  : el thread al cual se realiza el metodo invoca el run() del segundo hilo bloqueandolo los milisegundos pasados por parametro
