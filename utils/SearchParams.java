package utils;

public class SearchParams {

    private boolean vidroEletrico = false;
    private boolean cambioAutomatico = false;
    private boolean arCondicionado = false;
    private boolean freioAbs = false;
    private boolean quatroPortas = false;
    private boolean direcaoHidrauliaca = false;
    private boolean portaMalaGrande = false;
    private boolean premium = false;

    public SearchParams() {
    }

    public boolean isVidroEletrico() {
        return vidroEletrico;
    }

    public void setVidroEletrico(boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public boolean isCambioAutomatico() {
        return cambioAutomatico;
    }

    public void setCambioAutomatico(boolean cambioAutomatico) {
        this.cambioAutomatico = cambioAutomatico;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isFreioAbs() {
        return freioAbs;
    }

    public void setFreioAbs(boolean freioAbs) {
        this.freioAbs = freioAbs;
    }

    public boolean isQuatroPortas() {
        return quatroPortas;
    }

    public void setQuatroPortas(boolean quatroPortas) {
        this.quatroPortas = quatroPortas;
    }

    public boolean isDirecaoHidrauliaca() {
        return direcaoHidrauliaca;
    }

    public void setDirecaoHidrauliaca(boolean direcaoHidrauliaca) {
        this.direcaoHidrauliaca = direcaoHidrauliaca;
    }

    public boolean isPortaMalaGrande() {
        return portaMalaGrande;
    }

    public void setPortaMalaGrande(boolean portaMalaGrande) {
        this.portaMalaGrande = portaMalaGrande;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
