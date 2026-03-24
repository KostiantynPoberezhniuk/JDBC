package dto;

public class MaxSalaryWorker {
    private String name;
    private double salary;

    public MaxSalaryWorker(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "MaxSalaryWorker{name=" + name + ", salary=" + salary + "}";
    }
}
