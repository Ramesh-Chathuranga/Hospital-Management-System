package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.entity.CustomEntity;
import lk.pos.hospital.entity.CustomEntityTwo;
import lk.pos.hospital.entity.Custom_L;
import lk.pos.hospital.entity.Schedule;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Optional<List<CustomEntity>> getClinc() throws Exception{
        String sql="SELECT c.cid,c.clinicName,c.fieldId,sF.fieldName,c.clinicCharge,cTT.sceduleid FROM clinic AS c LEFT JOIN  clinicTimeTable AS cTT on c.cid = cTT.clinicid LEFT JOIN specificField sF on c.fieldId = sF.fid;";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<CustomEntity>entities =getAllOf(set);
//        List<CustomEntity>entities=new ArrayList<>();
//        while (set.next()){
//             entities.add(new CustomEntity(set.getString(1),
//                                           set.getString(2),
//                                           set.getString(3),
//                                           set.getString(4),
//                                           set.getDouble(5),
//                                           set.getString(6)));
//        }
        return Optional.of(entities);
    }

    @Override
    public Optional<List<CustomEntityTwo>> getHospitalAdmitDetails() throws Exception {
        String sql="SELECT p.pid,p.fullName,w.wid,b.bedid,hA.date,hA.dischargedate FROM patient AS p LEFT JOIN bed b on p.pid = b.patientid RIGHT JOIN hospitalAdmit hA on p.pid = hA.patientid LEFT JOIN ward w on hA.wardid = w.wid";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<CustomEntityTwo>getall=new ArrayList<>();
        while (set.next()){
            if (set.getDate(6) == null) {
                getall.add(new CustomEntityTwo(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getDate(5).toLocalDate(),null));
            }else {
                getall.add(new CustomEntityTwo(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getDate(5).toLocalDate(),set.getDate(6).toLocalDate()));
            }

        }
        return Optional.of(getall);
    }

    @Override
    public Optional<List<CustomEntity>> getCustomEntityByWidOFField(String wid) throws Exception {
        String sql="SELECT c.cid,c.clinicName,c.fieldId,sF.fieldName,c.clinicCharge,cTT.sceduleid FROM clinic AS c LEFT JOIN  clinicTimeTable AS cTT on c.cid = cTT.clinicid LEFT JOIN specificField sF on c.fieldId = sF.fid WHERE sF.wardId=?";
        ResultSet set = CrudUtil.exequteQuery(sql,wid);
        List<CustomEntity>entities =getAllOf(set);

        return Optional.ofNullable(entities);
    }

    @Override
    public Optional<List<Schedule>> getScheduleSOfDoctor(String did) throws Exception {
        String sql="SELECT * FROM doctorTimeTable As dt LEFT JOIN schedule s on dt.sceduleid = s.sid WHERE dt.doctorid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, did);
        List<Schedule> list=new ArrayList<>();
        while (set.next()){
           list.add(new Schedule(set.getString(3),set.getString(4),set.getString(5),set.getDate(6).toLocalDate(),set.getTime(7).toLocalTime(),set.getTime(8).toLocalTime()));
        }

        return Optional.ofNullable(list);
    }

    @Override
    public Optional<List<Custom_L>> getAllPatient() throws Exception {
        String sql="SELECT p.pid, p.fullName,p.nic,sF.fid,sF.fieldName,d.drid,d.fullName,d.nic,s.date,s.sid FROM patientDetail AS pD LEFT JOIN patient p on pD.patientid = p.pid LEFT JOIN clinic c on pD.clinicid = c.cid LEFT JOIN specificField sF on c.fieldId = sF.fid LEFT JOIN doctor d on sF.fid = d.fieldId LEFT JOIN clinicTimeTable cTT on c.cid = cTT.clinicid LEFT JOIN schedule s on cTT.sceduleid = s.sid";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<Custom_L>list =new ArrayList<>();
        while (set.next()){
          list.add(new Custom_L(set.getString(1),set.getString(2),
                  set.getString(3),set.getString(4),set.getString(5),
                  set.getString(6),set.getString(7),set.getString(8),set.getDate(9).toLocalDate(),set.getString(10)));
        }
        return Optional.ofNullable(list);
    }

    private List<CustomEntity> getAllOf(ResultSet set)throws Exception{
        List<CustomEntity>entities=new ArrayList<>();
        while (set.next()){
              entities.add(new CustomEntity(set.getString(1),
                                            set.getString(2),
                                            set.getString(3),
                                            set.getString(4),
                                            set.getDouble(5),
                                            set.getString(6)));
        }
        return entities;
    }
}
