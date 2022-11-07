package model.entities;


import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String marca;
	private String modelo;
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

	public Car() {
	}

	public Car(int id, String marca, String modelo, int ano, String cor, double valor, boolean vidroEletrico,
			boolean cambioAutomatico, boolean arCondicionado, boolean freioAbs, boolean quatroPortas,
			boolean direcaoHidrauliaca, boolean portaMalaGrande, boolean premium) {
		this.id = id;
		this.marca = marca;
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
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return this.marca;
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

	public void setMarca(String marca) {
		this.marca = marca;
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

	@Override
	public String toString() {
		return "Car{" + "id=" + id + ", marca='" + marca + '\'' + ", modelo='" + modelo + '\'' + ", ano=" + ano
				+ ", cor='" + cor + '\'' + ", valor=" + valor + ", vidroEletrico=" + vidroEletrico
				+ ", cambioAutomatico=" + cambioAutomatico + ", arCondicionado=" + arCondicionado + ", freioAbs="
				+ freioAbs + ", quatroPortas=" + quatroPortas + ", direcaoHidrauliaca=" + direcaoHidrauliaca
				+ ", portaMalaGrande=" + portaMalaGrande + ", premium=" + premium + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car) o;
		return Objects.equals(id, car.id) && Objects.equals(marca, car.marca) && Objects.equals(modelo, car.modelo)
				&& Objects.equals(ano, car.ano) && Objects.equals(cor, car.cor) && Objects.equals(valor, car.valor)
				&& Objects.equals(vidroEletrico, car.vidroEletrico)
				&& Objects.equals(cambioAutomatico, car.cambioAutomatico)
				&& Objects.equals(arCondicionado, car.arCondicionado) && Objects.equals(freioAbs, car.freioAbs)
				&& Objects.equals(quatroPortas, car.quatroPortas)
				&& Objects.equals(direcaoHidrauliaca, car.direcaoHidrauliaca)
				&& Objects.equals(portaMalaGrande, car.portaMalaGrande) && Objects.equals(premium, car.premium);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marca, modelo, ano, cor, valor, vidroEletrico, cambioAutomatico, arCondicionado,
				freioAbs, quatroPortas, direcaoHidrauliaca, portaMalaGrande, premium);
	}
}

