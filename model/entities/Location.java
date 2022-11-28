package model.entities;

import java.util.Date;
import java.util.Objects;

public class Location {

    private int id;
    private int idUnit;
    private int idUser;
    private int idCar;
    private Date dateOut;
    private Date returnDate;
    private int quantityDays;
    private Double finalValue;

    public Location(int id, int idUnit, int idUser, int idCar, Date dateOut, Date returnDate, int quantityDays, Double finalValue) {
        this.id = id;
        this.idUnit = idUnit;
        this.idUser = idUser;
        this.idCar = idCar;
        this.dateOut = dateOut;
        this.returnDate = returnDate;
        this.quantityDays = quantityDays;
        this.finalValue = finalValue;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantityDays() {
        return quantityDays;
    }

    public void setQuantityDays(int quantityDays) {
        this.quantityDays = quantityDays;
    }

    public Double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Double finalValue) {
        this.finalValue = finalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && idUnit == location.idUnit && idUser == location.idUser && idCar == location.idCar && quantityDays == location.quantityDays && Objects.equals(dateOut, location.dateOut) && Objects.equals(returnDate, location.returnDate) && Objects.equals(finalValue, location.finalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUnit, idUser, idCar, dateOut, returnDate, quantityDays, finalValue);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", idUnit=" + idUnit +
                ", idUser=" + idUser +
                ", idCar=" + idCar +
                ", dateOut=" + dateOut +
                ", returnDate=" + returnDate +
                ", quantityDays=" + quantityDays +
                ", finalValue=" + finalValue +
                '}';
    }
}
