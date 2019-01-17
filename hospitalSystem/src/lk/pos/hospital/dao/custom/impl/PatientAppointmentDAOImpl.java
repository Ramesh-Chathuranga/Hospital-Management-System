package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.PatientAppointmentDAO;
import lk.pos.hospital.entity.PatientApointment;
import lk.pos.hospital.entity.PatientApointment_FK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientAppointmentDAOImpl implements PatientAppointmentDAO {

    @Override
    public boolean save(PatientApointment entity) throws Exception {
        System.out.println(1);
        String sql="INSERT INTO patientAppointment ( doctorid, patientid, sceduleid) VALUES (?,?,?)";
        System.out.println(2);
        return CrudUtil.updateQuery(sql,entity.getPatientApointment_fk().getDoctorId(),entity.getPatientApointment_fk().getPatientId(),entity.getPatientApointment_fk().getSceduleId()) >0;
    }

    @Override
    public boolean updata(PatientApointment entity) throws Exception {
        String sql="UPDATE  patientAppointment SET doctorid=? AND patientid=? AND sceduleid=?  WHERE apointmentId=?";
        return CrudUtil.updateQuery(sql,entity.getPatientApointment_fk().getDoctorId(),entity.getPatientApointment_fk().getPatientId(),entity.getPatientApointment_fk().getSceduleId(),entity.getAppointmentId()) >0;
    }

    @Override
    public boolean delete(PatientApointment_FK object) throws Exception {
        String sql="DELETE FROM  patientAppointment WHERE doctorid=? AND patientid=? AND sceduleid=?";
        return CrudUtil.updateQuery(sql,object.getDoctorId(),object.getPatientId(),object.getSceduleId())>0;
    }

    @Override
    public Optional<PatientApointment> search(PatientApointment_FK object) throws Exception {
        String sql="SELECT * FROM  patientAppointment WHERE doctorid=? AND patientid=? AND sceduleid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object.getDoctorId(), object.getPatientId(), object.getSceduleId());
        PatientApointment apointment=null;
        if (set.next()){
            apointment=new PatientApointment(set.getInt(1),new PatientApointment_FK(set.getString(2),set.getString(3),set.getString(4)));
        }
        return Optional.ofNullable(apointment);
    }

    @Override
    public Optional<List<PatientApointment>> getALL() throws Exception {
        String sql="SELECT * FROM  patientAppointment ";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<PatientApointment>patientApointments=new ArrayList<>();
        while (set.next()){
            patientApointments.add(new PatientApointment(set.getInt(1),new PatientApointment_FK(set.getString(2),set.getString(3),set.getString(4))));
        }
        return Optional.ofNullable(patientApointments);
    }
}
