package Entity;

import java.time.LocalDate;

public class Logement extends Consommation{

    private TypeEnergie typeEnergie;
    private Double consommationEnergie;


    public Logement(int value, LocalDate startDate, LocalDate endDate, TypeEnergie typeEnergie, Double consommationEnergie) {
        super(value, startDate, endDate);
        this.typeEnergie = typeEnergie;
        this.consommationEnergie = consommationEnergie;
    }

    public TypeEnergie getTypeEnergie() {
        return typeEnergie;
    }

    public void setTypeEnergie(TypeEnergie typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    public Double getConsommationEnergie() {
        return consommationEnergie;
    }

    public void setConsommationEnergie(Double consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    @Override
    public Double calculImpact() {
        return null;
    }
}
