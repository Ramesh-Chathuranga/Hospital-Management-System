package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.PatientDAO;
import lk.pos.hospital.dao.custom.PatientDetailDAO;
import lk.pos.hospital.entity.Patient;
import lk.pos.hospital.entity.PatientDetail;
import lk.pos.hospital.entity.PatientDetail_PK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDetailDAOImpl implements PatientDetailDAO {

    @Override
    public boolean save(PatientDetail entity) throws Exception {
        String sql="INSERT INTO patientdetail VALUES(?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getPatientDetail_pk().getPatientId(),entity.getPatientDetail_pk().getClinicID(),entity.getNote()) >0;
    }

    @Override
    public boolean updata(PatientDetail entity) throws Exception {
        String sql="UPDATE patientdetail SET note=? WHERE  patientid=? AND clinicid=?";
        return CrudUtil.updateQuery(sql,entity.getNote(),entity.getPatientDetail_pk().getPatientId(),entity.getPatientDetail_pk().getClinicID()) >0;
    }

    @Override
    public boolean delete(PatientDetail_PK object) throws Exception {
        String sql="DELETE FROM patientdetail WHERE patientid=? AND clinicid=?";
        return CrudUtil.updateQuery(sql,object.getPatientId(),object.getClinicID())>0;
    }

    @Override
    public Optional<PatientDetail> search(PatientDetail_PK object) throws Exception {
        String sql="SELECT * FROM patientdetail WHERE  patientid=? AND clinicid=?";
        ResultSet resultSet = CrudUtil.exequteQuery(sql, object.getPatientId(), object.getClinicID());
        PatientDetail patientDetail=null;
        if(resultSet.next()){
            if(resultSet.getString(3)==null){
                patientDetail=new PatientDetail(new PatientDetail_PK(resultSet.getString(1),resultSet.getString(2)),null);
            }else {
                patientDetail=new PatientDetail(new PatientDetail_PK(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(3));
            }

        }
        return Optional.ofNullable(patientDetail);
    }

    @Override
    public Optional<List<PatientDetail>> getALL() throws Exception {
        String sql="SELECT * FROM patientdetail ";
        ResultSet resultSet = CrudUtil.exequteQuery(sql);
        List<PatientDetail> list = new ArrayList<>();
        while (resultSet.next()){
            if(resultSet.getString(3)==null){
                list.add(new PatientDetail(new PatientDetail_PK(resultSet.getString(1),resultSet.getString(2)),null));
            }else {
                list.add(new PatientDetail(new PatientDetail_PK(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(3)));
            }
        }
        return Optional.ofNullable(list);
    }
}
