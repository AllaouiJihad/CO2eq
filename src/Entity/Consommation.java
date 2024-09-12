package Entity;

import java.time.LocalDate;

public abstract class Consommation {
    private Double value;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private User user;
    private int typeConsommation;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTypeConsommation() {
        return typeConsommation;
    }

    public void setTypeConsommation(int typeConsommation) {
        this.typeConsommation = typeConsommation;
    }

    public Consommation(Double value, LocalDate startDate, LocalDate endDate,  int typeConsommation) {
        this.value = value;
        StartDate = startDate;
        EndDate = endDate;
        this.typeConsommation = typeConsommation;
    }

    public abstract Double calculImpact();

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return "Consommation{" +
                "value=" + value +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                '}';
    }
}
