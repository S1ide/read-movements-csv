import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(;;){
            System.out.println("Введите абсолютный путь до файла формата .csv:");
            String input = scanner.nextLine();
            Movements movements = new Movements(input);
            System.out.printf("Сумма расходов: %.2f руб\n", movements.getExpenseSum());
            System.out.printf("Сумма доходов: %.2f руб\n", movements.getIncomeSum());
            System.out.println("Сумма расходов по организациям:");
            movements.getListOfExpenses()
                    .forEach(System.out::println);
        }
    }
}
