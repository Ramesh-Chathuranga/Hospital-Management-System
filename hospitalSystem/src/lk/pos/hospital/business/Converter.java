package lk.pos.hospital.business;

import lk.pos.hospital.dto.*;
import lk.pos.hospital.entity.*;

import java.nio.channels.Channel;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static <T extends SuperDTO>T getDTO(SuperEntity entity){
        if (entity instanceof Ward){
            Ward ward= (Ward) entity;
            return (T) new WardDTO(ward.getWardId(),ward.getWardName());
        }else if (entity instanceof Bed){
            Bed bed= (Bed) entity;
            return (T) new BedDTO(((Bed) entity).getBesID(),bed.getWardID(),bed.getPatientID());
        }else if(entity instanceof Schedule){
            Schedule schedule=(Schedule)entity;
            return (T) new ScheduleDTO(schedule.getsId(),schedule.getScheduleName(),schedule.getDay(),schedule.getDate(),schedule.getStartTime(),schedule.getEndTime());
        }else  if(entity instanceof Clinic){
            Clinic clinic= (Clinic) entity;
            return (T) new ClinicDTO(clinic.getcId(),clinic.getClinicName(),clinic.getFieldID(),clinic.getClinicCharge());
        }else if (entity instanceof ClinicTimeTable){
            ClinicTimeTable ctt= (ClinicTimeTable) entity;
            return (T) new  ClinicTimeTableDTO(ctt.getClinicId(),ctt.getSheduleId());
        }else if(entity instanceof SpecificField){
            SpecificField field= (SpecificField) entity;
            return (T) new SpecialFieldDTO(field.getfId(),field.getFieldName(),field.getWardId());
        }else  if (entity instanceof CustomEntity){
            CustomEntity custom= (CustomEntity) entity;
            return (T) new CustomDTO(custom.getCid(),custom.getClinicName(),custom.getFieldId(),custom.getField(),custom.getClinicCharge(),custom.getSceduleid());
        }else if(entity instanceof Patient){
            Patient patient= (Patient) entity;
            return (T) new PatientDTO(patient.getPid(),patient.getNic(),patient.getFullName(),patient.getAddress(),patient.getTelephoneNO(),patient.getEmail(),patient.getGender());
        }else if (entity instanceof HospitalAdmit){
            HospitalAdmit admit= (HospitalAdmit) entity;
            return (T) new HospitalAdmitDTO(admit.getPatientId(),admit.getWardId(),admit.getDate(),((HospitalAdmit) entity).getDischargedate());
        }else if (entity instanceof CustomEntityTwo){
            CustomEntityTwo two= (CustomEntityTwo) entity;
            return (T) new CustomDTOTwo(two.getPid(),two.getPname(),two.getWid(),two.getBid(),two.getDate(),two.getDischarchdate());
        }else if(entity instanceof Doctor){
            Doctor doctor= (Doctor) entity;
            return (T) new DoctorDTO(doctor.getDrId(),doctor.getNic(),doctor.getMedId(),doctor.getFullName(),doctor.getAddress(),doctor.getTelephone(),doctor.getEmail(),doctor.getWardId(),doctor.getFieldId(),doctor.getGender());
        }else if(entity instanceof DoctorTimeTable){
            DoctorTimeTable dt= (DoctorTimeTable) entity;
            return (T) new DoctorTimeTableDTO(dt.getDoctorId(),dt.getScheduleId());
        }else if(entity instanceof PatientDetail){
            PatientDetail patient= (PatientDetail) entity;
            return (T) new PatientDetailDTO(patient.getPatientDetail_pk().getPatientId(),patient.getPatientDetail_pk().getClinicID(),patient.getNote());
        }else if(entity instanceof Custom_L){
            Custom_L cu= (Custom_L) entity;
            return (T) new Custom_LDTO(cu.getpId(),cu.getPname(),cu.getPnic(),cu.getSfID(),cu.getSf(),cu.getDrID(),cu.getdName(),cu.getdNic(),cu.getDate(),cu.getSid());
        }else if(entity instanceof PatientApointment){
            PatientApointment pa= (PatientApointment) entity;
            return (T) new AppointmentDTO(pa.getAppointmentId(),pa.getPatientApointment_fk().getDoctorId(),pa.getPatientApointment_fk().getPatientId(),pa.getPatientApointment_fk().getSceduleId());
        }
    return null;
    }

    public static <T extends  SuperEntity>T getEntity(SuperDTO dto){
        if (dto instanceof WardDTO){
            WardDTO wardDTO= (WardDTO) dto;
            return (T) new Ward(wardDTO.getWardId(),wardDTO.getWardName());
        }else if (dto instanceof BedDTO){
            BedDTO bedDTO= (BedDTO) dto;
            return (T) new Bed(bedDTO.getBedID(),bedDTO.getWardID(),bedDTO.getPatientID());
        }else  if(dto instanceof ScheduleDTO){
            ScheduleDTO scheduleDTO= (ScheduleDTO) dto;
            return (T)new Schedule(scheduleDTO.getsId(),scheduleDTO.getScheduleName(),scheduleDTO.getDay(),scheduleDTO.getDate(),scheduleDTO.getStartTime(),scheduleDTO.getEndTime());
        }else if (dto instanceof ClinicDTO){
            ClinicDTO clinicDTO= (ClinicDTO) dto;
            return (T) new Clinic(clinicDTO.getcId(),clinicDTO.getClinicName(),clinicDTO.getFieldID(),clinicDTO.getClinicCharge());
        }else if(dto instanceof ClinicTimeTableDTO){
            ClinicTimeTableDTO tableDTO= (ClinicTimeTableDTO) dto;
            return (T) new ClinicTimeTable(tableDTO.getClinicId(),tableDTO.getSheduleId());
        }else if(dto instanceof SpecialFieldDTO){
            SpecialFieldDTO fieldDTO= (SpecialFieldDTO) dto;
            return (T) new SpecificField(fieldDTO.getfId(),fieldDTO.getFieldName(),fieldDTO.getWardId());
        }else if(dto instanceof CustomDTO){
            CustomDTO custom= (CustomDTO) dto;
            return (T) new CustomEntity(custom.getCid(),custom.getClinicName(),custom.getFieldId(),custom.getField(),custom.getClinicCharge(),custom.getSceduleid());
        }else if (dto instanceof PatientDTO){
            PatientDTO patientDTO= (PatientDTO) dto;
            return (T) new Patient(patientDTO.getPid(),patientDTO.getNic(),patientDTO.getFullName(),patientDTO.getAddress(),patientDTO.getTelephoneNO(),patientDTO.getEmail(),patientDTO.getGender());
        }else if(dto instanceof HospitalAdmitDTO){
            HospitalAdmitDTO dto1= (HospitalAdmitDTO) dto;
            return (T) new HospitalAdmit(dto1.getPatientId(),dto1.getWardId(),dto1.getDate(),dto1.getDischargedate());
        }else if (dto instanceof CustomDTOTwo){
            CustomDTOTwo two= (CustomDTOTwo) dto;
            return (T) new CustomEntityTwo(two.getPid(),two.getPname(),two.getWid(),two.getBid(),two.getDate(),two.getDischarchdate());
        }else if (dto instanceof DoctorDTO){
            DoctorDTO doctorDTO= (DoctorDTO) dto;
            return (T) new Doctor(doctorDTO.getDrId(),doctorDTO.getNic(),doctorDTO.getMedId(),doctorDTO.getFullName(),doctorDTO.getAddress(),doctorDTO.getTelephone(),doctorDTO.getEmail(),doctorDTO.getWardId(),doctorDTO.getFieldId(),doctorDTO.getGender());
        }else if(dto instanceof DoctorTimeTableDTO){
            DoctorTimeTableDTO dt= (DoctorTimeTableDTO) dto;
            return (T) new DoctorTimeTable(dt.getDoctorId(),dt.getScheduleId());
        }else if (dto instanceof  PatientDetailDTO){
            PatientDetailDTO pd= (PatientDetailDTO) dto;
            return (T) new PatientDetail(new PatientDetail_PK(pd.getPatientId(),pd.getClinicId()),pd.getNote());
        }else if(dto instanceof Custom_LDTO){
            Custom_LDTO cu= (Custom_LDTO) dto;
            return (T) new Custom_L(cu.getpId(),cu.getPname(),cu.getPnic(),cu.getSfID(),cu.getSf(),cu.getDrID(),cu.getdName(),cu.getdNic(),cu.getDate(),cu.getSid());
        }else if(dto instanceof AppointmentDTO){
            AppointmentDTO a= (AppointmentDTO) dto;
            return (T) new PatientApointment(a.getApid(),new PatientApointment_FK(a.getDoctorId(),a.getPatientId(),a.getSceduleId()));
        }
        return null;
    }

    public static <T extends SuperDTO>List<T> getDtoList(List<? extends SuperEntity>entityList){
      return entityList.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity>List<T> getEntityList(List<? extends SuperDTO>dtoList){
       return dtoList.stream().map(Converter::<T>getEntity).collect(Collectors.toList());
    }
}
