package registration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationManager manager = new RegistrationManager();
        String[] events = {
                "Web Design Workshop",
                "JavaScript Quiz",
                "IT Career Talk"
        };

        boolean running = true;
        while (running) {
            System.out.println("\n--- Меню регистрации на мероприятия ---");
            System.out.println("1. Add registration");
            System.out.println("2. Show all registrations");
            System.out.println("3. Find registration by ID");
            System.out.println("4. Delete registration by ID");
            System.out.println("5. Show registrations for an event");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Пожалуйста, введите числовое значение для выбора пункта меню.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Full name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Student group: ");
                    String studentGroup = scanner.nextLine();

                    System.out.println("Select event:");
                    for (int i = 0; i < events.length; i++) {
                        System.out.println((i + 1) + ". " + events[i]);
                    }
                    System.out.print("Choice (1-3): ");
                    String eventChoiceStr = scanner.nextLine();
                    int eventChoice;
                    try {
                        eventChoice = Integer.parseInt(eventChoiceStr.trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Выбор мероприятия должен быть числом от 1 до 3.");
                        break;
                    }

                    if (eventChoice < 1 || eventChoice > 3) {
                        System.out.println("Ошибка: Неверный выбор мероприятия. Выберите от 1 до 3.");
                        break;
                    }

                    String selectedEvent = events[eventChoice - 1];
                    manager.addRegistration(fullName, email, studentGroup, selectedEvent);
                    break;

                case 2:
                    manager.displayAll();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    String idStr = scanner.nextLine();
                    try {
                        int id = Integer.parseInt(idStr.trim());
                        Registration reg = manager.findById(id);
                        if (reg != null) {
                            System.out.println(reg);
                        } else {
                            System.out.println("Registration not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: ID должен быть числом.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    String delIdStr = scanner.nextLine();
                    try {
                        int id = Integer.parseInt(delIdStr.trim());
                        boolean success = manager.deleteById(id);
                        if (success) {
                            System.out.println("Registration deleted successfully.");
                        } else {
                            System.out.println("Registration not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: ID должен быть числом.");
                    }
                    break;

                case 5:
                    System.out.println("Select event to filter:");
                    for (int i = 0; i < events.length; i++) {
                        System.out.println((i + 1) + ". " + events[i]);
                    }
                    System.out.print("Choice (1-3): ");
                    String filterChoiceStr = scanner.nextLine();
                    try {
                        int filterChoice = Integer.parseInt(filterChoiceStr.trim());
                        if (filterChoice < 1 || filterChoice > 3) {
                            System.out.println("Ошибка: Неверный выбор мероприятия.");
                            break;
                        }
                        manager.displayByEvent(events[filterChoice - 1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Выбор должен быть числом.");
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Неизвестный пункт меню. Выберите от 0 до 5.");
            }
        }
        scanner.close();
    }
}