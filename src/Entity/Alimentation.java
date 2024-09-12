package Entity;

import java.time.LocalDate;

public class Alimentation extends Consommation{
    private Double poids;
    private  TypeAliment typeAliment;

    public Alimentation(Double value, LocalDate startDate, LocalDate endDate, int typeConsommation, Double poids, TypeAliment typeAliment) {
        super(value, startDate, endDate,  typeConsommation);
        this.poids = poids;
        this.typeAliment = typeAliment;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public TypeAliment getTypeAliment() {
        return typeAliment;
    }

    public void setTypeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
    }

    @Override
    public Double calculImpact() {
        return null;
    }
}
