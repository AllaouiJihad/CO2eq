package Entity;

import java.time.LocalDate;

public class Transport extends Consommation{
    private double distance;
    private TypeVehicule typeVehicule;

    public Transport(int value, LocalDate startDate, LocalDate endDate, double distance, TypeVehicule typeVehicule) {
        super(value, startDate, endDate);
        this.distance = distance;
        this.typeVehicule = typeVehicule;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @Override
    public Double calculImpact() {
        return null;
    }
}
