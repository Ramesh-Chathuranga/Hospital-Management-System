package lk.pos.hospital.business.custom;

import lk.pos.hospital.business.SuperBO;
import lk.pos.hospital.dto.*;
import lk.pos.hospital.entity.PatientDetail;

import java.util.List;

public interface PatientBO extends SuperBO {
    public  boolean savePatient(PatientDTO object)throws Exception;
    public  boolean updatePatient(PatientDTO object)throws Exception;
    public  boolean deletePatient(String object)throws Exception;
    public  PatientDTO searchPatient(String object)throws Exception;
    public List<PatientDTO> getAllPatient()throws Exception;
    public String getPatientRegisterID()throws Exception;
    public boolean savePatientDetail(PatientDetailDTO dto,AppointmentDTO aDTO)throws Exception;
    public boolean deletePatientDetail(PatientDetailDTO dto)throws Exception;
    public boolean updatePatientDetail(PatientDetailDTO dto)throws Exception;
    public PatientDetailDTO searchPatientDetail(PatientDetailDTO dto)throws Exception;
    public List<Custom_LDTO> getForPatienDetailTable()throws Exception;
    public List<AppointmentDTO> getAllApointment()throws Exception;
    public List<PatientDetailDTO> getAllPatientDetail()throws  Exception;
}
