package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.HospitalAdmitDAO;
import lk.pos.hospital.entity.HospitalAdmit;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HospitalAdmitDAOImpl implements HospitalAdmitDAO {

    @Override
    public boolean save(HospitalAdmit entity) throws Exception {
        String sql="INSERT INTO hospitalAdmit (patientid,wardid,date) VALUES (?,?,?);";
        return CrudUtil.updateQuery(sql,entity.getPatientId(),entity.getWardId(),entity.getDate())>0;
    }

    @Override
    public boolean updata(HospitalAdmit entity) throws Exception {
        String sql="UPDATE hospitalAdmit SET  wardid=?,date=?,dischargedate=? WHERE  patientid=?";
        return  CrudUtil.updateQuery(sql,entity.getWardId(),entity.getDate(),entity.getDischargedate(),entity.getPatientId())>0;
    }

    @Override
    public boolean delete(HospitalAdmit object) throws Exception {
        String sql="DELETE FROM hospitalAdmit WHERE wardid=? AND patientid=?";
        return CrudUtil.updateQuery(sql,object.getWardId(),object.getPatientId())>0;
    }

    @Override
    public Optional<HospitalAdmit> search(HospitalAdmit object) throws Exception {
        String sql="SELECT * FROM hospitalAdmit WHERE wardid=? AND patientid=? AND date=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object.getWardId(), object.getPatientId(),object.getDate());
        HospitalAdmit hospitalAdmit=null;
        if (set.next()){
            hospitalAdmit=new HospitalAdmit(set.getString(1),set.getString(2),set.getDate(3).toLocalDate(),set.getDate(4).toLocalDate());
        }
        return Optional.ofNullable(hospitalAdmit);
    }

    @Override
    public Optional<List<HospitalAdmit>> getALL() throws Exception {
        String sql="SELECT * FROM hospitalAdmit ";
        List<HospitalAdmit>hospitalAdmits=new ArrayList<>();
        ResultSet set = CrudUtil.exequteQuery(sql);
        while (set.next()) {
            if (set.getDate(4) == null) {
                hospitalAdmits.add(new HospitalAdmit(set.getString(1), set.getString(2), set.getDate(3).toLocalDate(), null));
            } else{
                hospitalAdmits.add(new HospitalAdmit(set.getString(1), set.getString(2), set.getDate(3).toLocalDate(), set.getDate(4).toLocalDate()));
            }
        }

        return Optional.ofNullable(hospitalAdmits);
    }
}
