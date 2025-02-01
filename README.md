Proyecto java generado en la version 17
usa como base de datos h2.
version de Spring 3.5.0-SNAPSHOT
Usa OpenAPI 3.3.2 para la documentacion de la api

para poder acceder a la documentacion de la api por favor acceder al siguiente enlace
http://localhost:8080/swagger-ui.html

se debe crear un paciente y un medico para poder programar una cita
se debe crear una cita para poder consultar las citas por nombre de cliente y por medico
debe existir una cita para poder ser eliminada de la base de datos.
adicionalmente hay otros metodos para hacer consulta aparte de los solicitados

************************************************************************************************
Crear Doctor: METODO POST http://localhost:8080/Doctor/createDoctor
{
"id": 12456,
"name" :"andres",
"lastName":"rodriguez",
"speciality" :"cardiologo"
}
************************************************************************************************
Crear Paciente: METODO POST http://localhost:8080/patients
{
"id": 106177657,
"name" : "joana",
"lastName": "cebcollazos",
"email": "test@gmail.com",
"phone": "3162788888",
"address": "callefalsa123",
"city": "cali"

}
************************************************************************************************
Crear Cita: METODO POST http://localhost:8080/Cita/createCita
{

     "patientName": "joana",
      "doctorName": "andres",
      "dateTime" : "2025-02-12T10:00:00"
}
************************************************************************************************

Consulta Doctor: METODO GET http://localhost:8080/Doctor/andres
Consulta Paciente: METODO GET http://localhost:8080/patients/findByName/joana
Consulta Cita por nombre de paciente: METODO GET http://localhost:8080/api/appointments/patient/getAppointmentsByPatient/joana
consulta Cita por nombre de Danada: METODO GET http://localhost:8080/api/appointments/getAppointmentsByDoctor/andres

Eliminar Cita por id: METODO DELETE http://localhost:8080/api/appointments/deleteAppointment/1

************************************************************************************************
En el archivo adjunto de postman se
encuentra la colleccion(client) de peticiones
donde se podran realizar las acciones solicitadas.
************************************************************************************************
