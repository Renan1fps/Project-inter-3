package model.entities;


import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private double valor;
    private boolean vidroEletrico;
    private boolean cambioAutomatico;
    private boolean arCondicionado;
    private boolean freioAbs;
    private boolean quatroPortas;
    private boolean direcaoHidrauliaca;
    private boolean portaMalaGrande;
    private boolean premium;
    private Unit unit;
    private String imageUrl;

    public Car() {
    }

    public Car(int id, String modelo, int ano, String cor, String placa, double valor, boolean vidroEletrico,
               boolean cambioAutomatico, boolean arCondicionado, boolean freioAbs, boolean quatroPortas,
               boolean direcaoHidrauliaca, boolean portaMalaGrande, boolean premium) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.valor = valor;
        this.vidroEletrico = vidroEletrico;
        this.cambioAutomatico = cambioAutomatico;
        this.arCondicionado = arCondicionado;
        this.freioAbs = freioAbs;
        this.quatroPortas = quatroPortas;
        this.direcaoHidrauliaca = direcaoHidrauliaca;
        this.portaMalaGrande = portaMalaGrande;
        this.premium = premium;
        this.placa = placa;
    }

    public Car(int i, String modelo, int ano, String cor, String placa, int valor, boolean vidroEletrico, boolean cambioAutomatico, boolean arCondicionado, boolean freioABS, boolean quatroPortas, boolean direcaoHidraulica, boolean portaMalaGrande, boolean premium, String imageUrl) {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getVidroEletrico() {
        return vidroEletrico;
    }

    public void setVidroEletrico(boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public boolean getCambioAutomatico() {
        return cambioAutomatico;
    }

    public void setCambioAutomatico(boolean cambioAutomatico) {
        this.cambioAutomatico = cambioAutomatico;
    }

    public boolean getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean getFreioAbs() {
        return freioAbs;
    }

    public void setFreioAbs(boolean freioAbs) {
        this.freioAbs = freioAbs;
    }

    public boolean getQuatroPortas() {
        return quatroPortas;
    }

    public void setQuatroPortas(boolean quatroPortas) {
        this.quatroPortas = quatroPortas;
    }

    public boolean getDirecaoHidrauliaca() {
        return direcaoHidrauliaca;
    }

    public void setDirecaoHidrauliaca(boolean direcaoHidrauliaca) {
        this.direcaoHidrauliaca = direcaoHidrauliaca;
    }

    public boolean getPortaMalaGrande() {
        return portaMalaGrande;
    }

    public void setPortaMalaGrande(boolean portaMalaGrande) {
        this.portaMalaGrande = portaMalaGrande;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean getPremium() {
        return this.premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && ano == car.ano && Double.compare(car.valor, valor) == 0 && vidroEletrico == car.vidroEletrico && cambioAutomatico == car.cambioAutomatico && arCondicionado == car.arCondicionado && freioAbs == car.freioAbs && quatroPortas == car.quatroPortas && direcaoHidrauliaca == car.direcaoHidrauliaca && portaMalaGrande == car.portaMalaGrande && premium == car.premium && Objects.equals(modelo, car.modelo) && Objects.equals(placa, car.placa) && Objects.equals(cor, car.cor) && Objects.equals(unit, car.unit) && Objects.equals(imageUrl, car.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, placa, ano, cor, valor, vidroEletrico, cambioAutomatico, arCondicionado, freioAbs, quatroPortas, direcaoHidrauliaca, portaMalaGrande, premium, unit, imageUrl);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", ano=" + ano +
                ", cor='" + cor + '\'' +
                ", valor=" + valor +
                ", vidroEletrico=" + vidroEletrico +
                ", cambioAutomatico=" + cambioAutomatico +
                ", arCondicionado=" + arCondicionado +
                ", freioAbs=" + freioAbs +
                ", quatroPortas=" + quatroPortas +
                ", direcaoHidrauliaca=" + direcaoHidrauliaca +
                ", portaMalaGrande=" + portaMalaGrande +
                ", premium=" + premium +
                ", unit=" + unit +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

