public class Movement {
    private String operationDescription;
    private Double income;
    private Double expense;

    public Movement(String operationDescription, Double income, Double expense) {
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public Double getIncome() {
        return income;
    }

    public Double getExpense() {
        return expense;
    }
}
