package com.clinic.service;

import com.clinic.entities.Appointment;
import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private  AppointmentRepository repository;
    @Autowired
    private PatientService patientService;


    @Autowired
    private DoctorService doctorService;

    public List<Appointment> getAppointments() {
        return repository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {

        return repository.save(appointment);
    }

    public List<Appointment> findByMedicoAndFechaHora(Doctor doctor, LocalDateTime dateTime) {
        return repository.findByDoctorAndAppointmentDate(doctor, dateTime);

    }

    public boolean existsByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime appointmentDateTime) {
        return repository.existsByDoctorAndAppointmentDate(doctor,appointmentDateTime);
    }


    public Appointment scheduleAppointment(String patientName,String  doctorName, LocalDateTime appointmentDateTime)  throws Exception {
        Patient patient = patientService.findPatientByName(patientName);
        Doctor doctor = doctorService.findDoctorByName(doctorName);

        if (repository.existsByDoctorAndAppointmentDate(doctor, appointmentDateTime)) {
            throw new Exception("El médico ya tiene una cita programada para este horario.");
        }
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDateTime);
        return repository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId){
        return repository.findByPatientId(patientId);
    }
    public List<Appointment> getAppointmentsByDoctor(Long doctorId){
        return repository.findByDoctorId(doctorId);
    }


    public Appointment findById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Boolean deleteAppointment(Long id){
      try{
        repository.deleteById(id);
        return true;
      }catch(Exception e){
        return false;
      }
    }
}
