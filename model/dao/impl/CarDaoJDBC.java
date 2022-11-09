package model.dao.impl;

import database.DbException;
import database.Provider;
import model.dao.CarDao;
import model.entities.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoJDBC implements CarDao {

    private Connection conn;

    public CarDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Car obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO tb_car "
                            + "(modelo, ano, cor, valor, vidro_eletrico, cambio_automatico, ar_condicionado, freio_abs, quatro_portas, direcao_hidraulica, porta_mala_grande, premium) "
                            + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getModelo());
            st.setInt(2, obj.getAno());
            st.setString(3, obj.getCor());
            st.setDouble(4, obj.getValor());
            st.setBoolean(5, obj.getVidroEletrico());
            st.setBoolean(6, obj.getCambioAutomatico());
            st.setBoolean(7, obj.getArCondicionado());
            st.setBoolean(8, obj.getFreioAbs());
            st.setBoolean(9, obj.getQuatroPortas());
            st.setBoolean(10, obj.getDirecaoHidrauliaca());
            st.setBoolean(11, obj.getPortaMalaGrande());
            st.setBoolean(12, obj.getPremium());

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
    public void update(Car obj) {

    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM tb_car WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Provider.closeStatement(st);
        }

    }

    @Override
    public List<Car> findCondition(Car obj) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT *" + "FROM tb_car ");

            rs = st.executeQuery();

            List<Car> list = new ArrayList<>();

            while (rs.next()) {
                Car obj = instantiateCar(rs);
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
    public List<Car> finOne() {
        return null;
    }

    private Car instantiateCar(ResultSet rs) throws SQLException {
        Car obj = new Car();
        obj.setId(rs.getInt("id_car"));
        obj.setModelo(rs.getString("modelo"));
        obj.setId(rs.getInt("ano"));
        obj.setModelo(rs.getString("cor"));
        obj.setValor(rs.getDouble("valor"));
        obj.setVidroEletrico(rs.getBoolean("vidro_eletrico"));
        obj.setCambioAutomatico(rs.getBoolean("cambio_automatico"));
        obj.setArCondicionado(rs.getBoolean("ar_condicionado"));
        obj.setFreioAbs(rs.getBoolean("freio_abs"));
        obj.setQuatroPortas(rs.getBoolean("quatro_portas"));
        obj.setDirecaoHidrauliaca(rs.getBoolean("direcao_hidraulica"));
        obj.setPortaMalaGrande(rs.getBoolean("porta_mala_grande"));
        obj.setPremium(rs.getBoolean("premium"));

        return obj;
    }
}
