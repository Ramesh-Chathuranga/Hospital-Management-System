package lk.pos.hospital.dao;

import lk.pos.hospital.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static PreparedStatement getPreparedStatement(String sql, Object...srt) throws Exception {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);
        int i=1;
        for (Object object : srt) {
            pstm.setObject(i++,object);
        }
        return pstm;
    }

    public static int updateQuery(String sql,Object...srt) throws Exception {
        return getPreparedStatement(sql, srt).executeUpdate();
    }

    public static ResultSet exequteQuery(String sql, Object...srt) throws Exception {
        return getPreparedStatement(sql, srt).executeQuery();
    }
}
