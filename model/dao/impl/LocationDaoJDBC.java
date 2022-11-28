package model.dao.impl;

import database.DbException;
import database.Provider;
import model.dao.LocationDao;
import model.entities.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoJDBC implements LocationDao {

    private final Connection conn;

    public LocationDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Location obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO tb_location "
                            + "(id_unit, id_user, id_car, date_out, return_date, quant_days, value_final) "
                            + "VALUES " + "(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getIdUnit());
            st.setInt(2, obj.getIdUser());
            st.setInt(3, obj.getIdCar());
            st.setDate(4, new java.sql.Date(obj.getDateOut().getTime()));
            st.setDate(5, new java.sql.Date(obj.getReturnDate().getTime()));
            st.setInt(6, obj.getQuantityDays());
            st.setDouble(7, obj.getFinalValue());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                Provider.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Provider.closeStatement(st);
        }
    }

    @Override
    public void update(Location obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE  tb_location SET \n" +
                    " id_unit = ?, \n" +
                    " id_user = ?, \n" +
                    " id_car = ?, \n" +
                    " date_out = ?, \n" +
                    " return_date = ?, \n" +
                    " quant_days = ?, \n" +
                    " value_final = ?, \n" +
                    "WHERE id_loc = ?;"
            );

            st.setInt(1, obj.getIdUnit());
            st.setInt(2, obj.getIdUser());
            st.setInt(3, obj.getIdCar());
            st.setDate(4, (Date) obj.getDateOut());
            st.setDate(5, (Date) obj.getReturnDate());
            st.setInt(6, obj.getQuantityDays());
            st.setDouble(7, obj.getFinalValue());
            st.setInt(8, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Provider.closeStatement(st);
        }
    }

    @Override
    public List<Location> findCondition(int idUser) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT tb_location.*" + "WHERE tb_location.id_user = ?");

            st.setInt(1, idUser);
            rs = st.executeQuery();

            List<Location> list = new ArrayList<>();
            while (rs.next()) {

                Location obj = instantiateLocation(rs);
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

    @Override
    public List<Location> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM tb_location");

            rs = st.executeQuery();

            List<Location> list = new ArrayList<>();

            while (rs.next()) {
                Location obj = instantiateLocation(rs);
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

    private Location instantiateLocation(ResultSet rs) throws SQLException {
        Location obj = new Location();
        obj.setId(rs.getInt("id_loc"));
        obj.setIdUnit(rs.getInt("id_unit"));
        obj.setIdUser(rs.getInt("id_user"));
        obj.setIdCar(rs.getInt("id_car"));
        obj.setDateOut(rs.getDate("date_out"));
        obj.setReturnDate(rs.getDate("return_date"));
        obj.setQuantityDays(rs.getInt("quant_days"));
        obj.setFinalValue(rs.getDouble("value_final"));

        return obj;
    }
}
