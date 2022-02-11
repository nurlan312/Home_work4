package org.example;

import org.example.entity.Employee_1;
import org.example.util.Hibernate_util;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Hibernate_util.getSession();
        Employee_1 employee1 = new Employee_1("Erkin", "Kubanychbekov", 25, 350000);
        Employee_1 employee2 = new Employee_1("Erlan", "Babaev", 28, 450000);
        Employee_1 employee3 = new Employee_1("Maksim", "Trubin", 24, 550000);
        Employee_1 employee4 = new Employee_1("Aza", "Krasavchik", 19, 550000);
        Employee_1 employee5 = new Employee_1("Anton", "Ershov", 26, 600000);
        Employee_1 employee6 = new Employee_1("Aza", "Azamatov", 18, 750000);
        Employee_1 employee7 = new Employee_1("Kanat", "Kadyrbekov", 32, 250000);
        Employee_1 employee8 = new Employee_1("Aza", "Sadyrov", 35, 850000);
        Employee_1 employee9 = new Employee_1("Omur", "Yzakov", 36, 380000);
        Employee_1 employee10 = new Employee_1("Aza", "Musagaliev", 37, 1000000);
        Employee_1 employee11 = new Employee_1("Almaz", "Tashiev", 41, 490000);
        Employee_1 employee12 = new Employee_1("Aza", "Murzakanov", 36, 3500000);
        create(employee1);
        create(employee2);
        create(employee3);
        create(employee4);
        create(employee5);
        create(employee6);
        create(employee7);
        create(employee8);
        create(employee9);
        create(employee10);
        create(employee11);
        create(employee12);

        /**
         * 1. Аты Аза жана жашы 20дан чон болгон баардык жумушчуларды алынынз.
         * 2. Аты Аза болгон жумушчулардын жашын 18ге озгортунуз.
         * 3. Аты Аза болгон жумушчуларды очурунуз.
         */

        getEmployeeWithNameAndAge("Aza", 20);
        updateAge("Aza", 18);
        deleteName("Aza");

    }

    public static int create(Employee_1 employee1) {
        Session session = Hibernate_util.getSession().openSession();
        session.beginTransaction();
        session.save(employee1);
        session.getTransaction();
        session.close();
        System.out.println("Успешно добавлен в таблицу: " + employee1);
        return employee1.getId();
    }

    public static List<Employee_1> getEmployeeWithNameAndAge(String name, int age) {
        Session session = Hibernate_util.getSession().openSession();
        session.beginTransaction();
        String hql = "from Employee_1 where name = :employee_name and age > :age";
        List employee_1s = session.createQuery(hql)
        .setParameter("employee_name", name)
        .setParameter("age", age).list();
        session.getTransaction().commit();
        System.out.println("Аты Аза жана жашы 20дан чон болгон баардык жумушчулар: \n" + employee_1s);
        session.close();
        return employee_1s;
    }

    public static void updateAge(String name, int age) {
        Session session = Hibernate_util.getSession().openSession();
        session.beginTransaction();
        String hql = "update Employee_1 set age = :age where name = :employee_name";
        Query query = session.createQuery(hql);
        query.setParameter("employee_name", name);
        query.setParameter("age", age);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Аты Аза болгон жумушчулардын жашы 18ге озгортулду.\n");
    }

    public static void deleteName(String name) {
        Session session = Hibernate_util.getSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete Employee_1 where name = :employee_name");
        query.setParameter("employee_name", name);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Аты Аза болгон жумушчулар очурулду.\n");
    }
}