
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Movements {
    private static ArrayList<Movement> movements;

    public Movements(String pathMovementsCsv) {
        movements = new ArrayList<>();
        try {
            List<String> strings = Files.readLines(new File(pathMovementsCsv), Charset.defaultCharset());
            strings.remove(0);
            strings.stream()
                    .map(s -> s.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", 8))
                    .forEach(Movements::addToLists);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    private static void addToLists(String[] strings) {
        String information = strings[5];
        String income = strings[6];
        String expense = strings[7];
        income = income.replaceAll("\"", "").replaceAll(",", ".");
        expense = expense.replaceAll("\"", "").replaceAll(",", ".");
        movements.add(new Movement(information,
                Double.parseDouble(income),
                Double.parseDouble(expense)));
    }

    public double getExpenseSum() {
        return movements.stream().map(Movement::getExpense).mapToDouble(num -> num).sum();
    }

    public double getIncomeSum() {
        return movements.stream().map(Movement::getIncome).mapToDouble(num -> num).sum();
    }

    public ArrayList<String> getListOfExpenses() {
        ArrayList<String> result = new ArrayList<>();
        TreeMap<String, Double> expenses = new TreeMap<>();
        movements.forEach(movement -> {
            Double expense = movement.getExpense();
            if (expense != 0) {
                String organization = movement.getOperationDescription()
                        .split(" +", 2)[1]
                        .split(" {3,}")[0];
                if (organization.contains("\\")) organization = organization.substring(organization.indexOf("\\"));
                if (organization.contains("/")) organization = organization.substring(organization.indexOf("/"));
                if (!expenses.containsKey(organization)) {
                    expenses.put(organization, expense);
                } else {
                    expenses.replace(organization, expenses.get(organization) + expense);
                }
            }
        });
        expenses.forEach((organization, amount) -> result.add(String.format("%s —> %.2f руб.", organization, amount)));
        return result;
    }
}
