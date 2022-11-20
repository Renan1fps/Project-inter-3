package model.dao.impl;

import database.DbException;
import database.Provider;
import model.dao.UnitDao;
import model.entities.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDaoJDBC implements UnitDao {

    private final Connection conn;

    public UnitDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Unit> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM tb_unit");

            rs = st.executeQuery();

            List<Unit> list = new ArrayList<>();

            while (rs.next()) {
                Unit obj = instantiateUnit(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Provider.closeStatement(st);
            Provider.closeResultSet(rs);
        }
    }

    private Unit instantiateUnit(ResultSet rs) throws SQLException {
        Unit obj = new Unit();
        obj.setId(rs.getInt("id_unit"));
        obj.setName(rs.getString("name"));
        obj.setUnitPhone(rs.getString("phoneUni"));
        obj.setAddress(rs.getString("adress"));
        obj.setCity(rs.getString("city"));
        obj.setState(rs.getString("state"));
        obj.setNumber(rs.getInt("number"));
        return obj;
    }
}
