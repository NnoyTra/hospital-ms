package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.entity.Appointment;
import com.nnoi.app.hospital_ms.exceptions.CreatedAppointmentNotPresentException;
import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import com.nnoi.app.hospital_ms.producer.AppointmentEventPublisher;
import com.nnoi.app.hospital_ms.repository.AppointmentRepository;
import com.nnoi.app.hospital_ms.util.validator.CreateAppointmentValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private AppointmentEventPublisher appointmentEventPublisher;

    public List<Appointment> getAppointmentList() {
        return appointmentRepository.getAllAppointments();
    }

    public void publishAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRequest == null || appointmentRequest.getCreateAppointment() == null)
            throw new CreatedAppointmentNotPresentException();
        CreateAppointmentValidator.validate(appointmentRequest.getCreateAppointment());

        //If no exception at this point means the message is ready to be published
    }


}
