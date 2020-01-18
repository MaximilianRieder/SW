package de.othr.sw.quickstart.dtos;

public class RiskResponseDto {
    Risikostufe risikostufe;

    public RiskResponseDto() {
    }

    public RiskResponseDto(Risikostufe risikostufe) {
        this.risikostufe = risikostufe;
    }

    public Risikostufe getRisikostufe() {
        return risikostufe;
    }

    public void setRisikostufe(Risikostufe risikostufe) {
        this.risikostufe = risikostufe;
    }
}
