package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.ClinicTimeTableDAO;
import lk.pos.hospital.entity.ClinicTimeTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClinicTimeTableDAOImpl implements ClinicTimeTableDAO {

    @Override
    public boolean save(ClinicTimeTable entity) throws Exception {
        String sql="INSERT INTO clinictimetable VALUES(?,?)";
         return CrudUtil.updateQuery(sql,entity.getClinicId(),entity.getSheduleId())>0;
    }

    @Override
    public boolean updata(ClinicTimeTable entity) throws Exception {
        String sql="UPDATE clinictimetable SET  sceduleid=? WHERE clinicid=? ";
         return CrudUtil.updateQuery(sql,entity.getSheduleId(),entity.getClinicId())>0;
    }

    @Override
    public boolean delete(ClinicTimeTable object) throws Exception {
        String sql="DELETE FROM clinictimetable WHERE  clinicid=? AND sceduleid=?";
         return CrudUtil.updateQuery(sql,object.getClinicId(),object.getSheduleId())>0;
    }

    @Override
    public Optional<ClinicTimeTable> search(ClinicTimeTable object) throws Exception {
        String sql="SELECT * FROM clinictimetable WHERE  clinicid=? AND sceduleid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object.getClinicId(), object.getSheduleId());
        ClinicTimeTable clinicTimeTable=null;
        if (set.next()){
            clinicTimeTable=new ClinicTimeTable(set.getString(1),set.getString(2));
        }
          return Optional.ofNullable(clinicTimeTable);
    }

    @Override
    public Optional<List<ClinicTimeTable>> getALL() throws Exception {
        String sql="SELECT * FROM clinictimetable ";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<ClinicTimeTable>clinicTimeTables=new ArrayList<>();
        while (set.next()){
            clinicTimeTables.add(new ClinicTimeTable(set.getString(1),set.getString(2)));
        }
        return Optional.ofNullable(clinicTimeTables);
    }
}
