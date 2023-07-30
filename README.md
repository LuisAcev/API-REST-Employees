Nombre: Turnos Rotativos.

Tipo: API 

Puerto del servidor: 8080

La aplicación consta de: 
-2 controladores:
            -EmpeladoController para controlar la entidad Empleado y su repository   EmpledoRepository.
                -En EmpeladoController están las peticiones tipo GET, POST y DELETE.
                
                -Un RequestMapping("/empleado") 

                -En la entidad Empleado se implementaron validaciones sombre campos vacíos, caracteres adiciones o prohibidos, expresiones regulares además de campos autocompletados según requerimiento.

            -ConceptoController para controlar la entidad Concepto y su repository ConceptoRepository. 
                -En ConceptoController están las peticiones tipo GET.

                -@RequestMapping("/concepto")

                -En la entidad Concepto se implementaron validaciones sombre campos vacíos, caracteres adiciones o prohibidos, expresiones regulares además de campos autocompletados según requerimiento.


-Para el manejo de las excepciones y personalización de las mimas se tiene la carpeta exceptions la cual tiene el manejador principal CustomExceptionHandler donde está la clase CustomExceptionHandler que extiende de ResponseEntityExceptionHandler. Igualmente, en CustomExceptionHandler están los métodos: 
    handlBussinesException para el código de error 409 (CONFLICT)
    foundBussinesException para el código de error 404 (NOT_FOUND) 
    BadRequestException para el código de error	 400 (BAD_REQUEST)

las pruebas se realizaron con la aplicación Postman. Para las solicitudes (GET, POST y DELETE), se apunta a la URL http://localhost:8080/empleado/ correspondiente a EmpeladoController y su entidad Empleado y la URL http://localhost:8080/concepto/ para ConceptoController con su  entidad Concepto.
