package Entity;

import java.time.LocalDate;

public class Consommation {
    private int value;
    private LocalDate StartDate;
    private LocalDate EndDate;

    public Consommation(int value, LocalDate startDate, LocalDate endDate) {
        this.value = value;
        StartDate = startDate;
        EndDate = endDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
