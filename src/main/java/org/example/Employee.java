package org.example;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private final String lastName;
    private final String firstName;
    // В поле org.example.Employee  добавлены новые поля «Зарплата» и «Отдел»
    private final int departmentId;
    // В поле org.example.Employee  добавлены новые поля «Зарплата» и «Отдел»
    private final double salary;

    public Employee(String lastName, String firstName, int departmentId, float salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.salary = salary;
    }
    // конструктор для методов find и remove чтобы поиск сотрудников осуществлять только по ключу
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        salary = 0;
        departmentId = 0;
    }
    public int getDepartment(){
        return this.departmentId;
    }
    // 3 реализация
    public double getSalary(){
        return this.salary;
    }
    /* 1 и 2 реализация
    public float getSalary(){
        return this.salary;
    }
     */
    // метод используется скрытой функцией toString для вывода объекта
    public String getFirstName(){
        return this.firstName;
    }
    // метод используется скрытой функцией toString для вывода объекта
    public String getLastName(){
        return this.lastName;
    }
    // метод генерации ключа для коллекции
    public String toKey() {
        return firstName + lastName;
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.firstName+this.lastName);
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return this.firstName.equals(c2.firstName) && this.lastName.equals(c2.lastName);
    }
}
