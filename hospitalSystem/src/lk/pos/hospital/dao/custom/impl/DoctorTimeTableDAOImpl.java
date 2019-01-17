package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.DoctorTimeTableDAO;
import lk.pos.hospital.entity.DoctorTimeTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorTimeTableDAOImpl implements DoctorTimeTableDAO {

    @Override
    public boolean save(DoctorTimeTable entity) throws Exception {
        String sql="INSERT INTO doctortimetable VALUES(?,?)";
        return CrudUtil.updateQuery(sql,entity.getDoctorId(),entity.getScheduleId())>0;
    }

    @Override
    public boolean updata(DoctorTimeTable entity) throws Exception {
        String sql="UPDATE doctortimetable SET WHERE  doctorid=? AND sceduleid=?";
        return CrudUtil.updateQuery(sql,entity.getDoctorId(),entity.getScheduleId())>0;
    }

    @Override
    public boolean delete(DoctorTimeTable object) throws Exception {
        String sql="DELETE FROM doctortimetable WHERE doctorid=? AND sceduleid=?";
        return CrudUtil.updateQuery(sql,object.getDoctorId(),object.getScheduleId())>0;
    }

    @Override
    public Optional<DoctorTimeTable> search(DoctorTimeTable object) throws Exception {
        String sql="SELECT * FROM doctortimetable WHERE  doctorid=? AND sceduleid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object.getDoctorId(), object.getScheduleId());
        DoctorTimeTable doctorTimeTable=null;
        if (set.next()){
            doctorTimeTable=new DoctorTimeTable(set.getString(1),set.getString(2));
        }
        return Optional.ofNullable(doctorTimeTable);
    }

    @Override
    public Optional<List<DoctorTimeTable>> getALL() throws Exception {
        String sql="SELECT * FROM doctortimetable ";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<DoctorTimeTable>doctorTimeTables=new ArrayList<>();
        while (set.next()){
            doctorTimeTables.add(new DoctorTimeTable(set.getString(1),set.getString(2)));
        }
        return Optional.ofNullable(doctorTimeTables);
    }
}
