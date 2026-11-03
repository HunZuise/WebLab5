package registration;
import java.util.ArrayList;
import java.util.Iterator;
public class RegistrationManager {
    private ArrayList<Registration> registrations = new ArrayList<>();
    private int nextId = 1;
    public boolean addRegistration(String fullName, String email, String studentGroup, String eventTitle) {
        if (fullName == null || fullName.trim().length() < 3) {
            System.out.println("Ошибка: ФИО должно содержать не менее 3 символов.");
            return false;
        }
        if (email == null || !email.contains("@") || email.indexOf('@') == 0 || email.indexOf('@') == email.length() - 1) {
            System.out.println("Ошибка: Некорректный email (должен содержать '@' с текстом до и после).");
            return false;
        }
        if (studentGroup == null || studentGroup.trim().isEmpty()) {
            System.out.println("Ошибка: Учебная группа не может быть пустой.");
            return false;
        }
        if (eventTitle == null || eventTitle.trim().isEmpty()) {
            System.out.println("Ошибка: Название мероприятия не выбрано.");
            return false;
        }
        Registration reg = new Registration(nextId++, fullName.trim(), email.trim(), studentGroup.trim(), eventTitle.trim());
        registrations.add(reg);
        System.out.println("Регистрация успешна создана. ID: " + reg.getId());
        return true;
    }
    public void displayAll() {
        if (registrations.isEmpty()) {
            System.out.println("Список регистраций пуст.");
            return;
        }
        for (Registration reg : registrations) {
            System.out.println(reg);
        }
    }
    public Registration findById(int id) {
        for (Registration reg : registrations) {
            if (reg.getId() == id) {
                return reg;
            }
        }
        return null;
    }
    public boolean deleteById(int id) {
        Iterator<Registration> iterator = registrations.iterator();
        while (iterator.hasNext()) {
            Registration reg = iterator.next();
            if (reg.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public void displayByEvent(String eventTitle) {
        boolean found = false;
        for (Registration reg : registrations) {
            if (reg.getEventTitle().equalsIgnoreCase(eventTitle)) {
                System.out.println(reg);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Для данного мероприятия нет регистраций.");
        }
    }
}