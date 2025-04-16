import jakarta.persistence.Entity;
import jakarta.persistence.Query;
import lombok.ToString;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    static Session session;

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSession();
        session = sessionFactory.openSession();

        Employee lena = getEmployee(4);
        if (lena != null) {
            Job jobLena = getJob(lena.getIdJob());
            if (jobLena != null) {
                System.out.println("Employee before: " + lena);
                System.out.println("Job before: " + jobLena);
            }

            lena.setSalary(lena.getSalary() * 2);
            jobLena.setMinSalary(jobLena.getMinSalary() + 10);
            System.out.println("Employee after: " + lena);
            System.out.println("Job after: " + jobLena);

            Transaction transaction = session.beginTransaction();
            session.save(lena); // UPDATE
            session.save(jobLena);
            if (lena.getSalary() > 10000) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        }

//        addEmployee("Cristian", "Mihalache", 1000);
//        addJob("Truck Driver");
//        getRichEmployees();

        Map<String, String> conditions = new HashMap<>();
        conditions.put("   age", "              < 90   ");
        conditions.put("salary   ", ">                    40");
        conditions.put("firstName\n", "        LIKE '%i'");
//        conditions.put("streetName  ", " = 'Florilor'");
//        conditions.put(" true; ", " delete from Employee;");
        customEmployeesSelect(conditions);

        if (containsField("firstName", Employee.class)) {
            System.out.println("Employee contine firstName");
        } else {
            System.out.println("Employee NU contine firstName");
        }

        if (containsField("streetName", Employee.class)) {
            System.out.println("Employee contine streetName");
        } else {
            System.out.println("Employee NU contine streetName");
        }

        if (containsSetterMethod(Employee.class, "setCnp", String.class)) {
            System.out.println("Employee contine setCnp cu parametru String");
        } else {
            System.out.println("Employee NU contine setCnp cu parametru String");
        }

        // TODO: contains annotations?
        if (containsAnnotation(Employee.class, ToString.class)) {
            System.out.println("Employee contine adnotarea @Entity");
        } else {
            System.out.println("Employee NU contine adnotarea @Entity");
        }
    }

    static boolean containsField(String fieldName, Class clazz) {
        System.out.println("Voi cauta campul " + fieldName + " in clasa " + clazz.getName());
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    static boolean containsSetterMethod(Class clazz, String methodName, Class parameterClass) {
        System.out.println("Voi cauta metoda " + methodName + " in clasa " + clazz.getName());
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterClass);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    static boolean containsAnnotation(Class clazz, Class annotationClass) {
        System.out.println("Voi cauta adnotarea " + annotationClass.getName() + " in clasa " + clazz.getName());
        Annotation annotation = clazz.getAnnotation(annotationClass);
        return annotation != null;
    }

    // select e from Employee where firstName LIKE 'Vasilica'
    // select e from Employee where lastName = 'Gigel'
    // select e from Employee where age < 90 and salary > 40
    static void customEmployeesSelect(Map<String, String> whereConditions) {
        Set<String> whereKeys = whereConditions.keySet();
        StringBuilder sb = new StringBuilder("select e from Employee as e where true ");
        for (String whereKey : whereKeys) {
            System.out.println("Am gasit cheia {" + whereKey + "}");
            String whereValue = whereConditions.get(whereKey);
//            whereValue = whereValue.trim();
            sb.append(" and ").append(whereKey).append(" ").append( whereValue);
        }
        String hqlQuery = sb.toString().replaceAll("\\s+", " ");
        System.out.println("Query-ul construit: " + hqlQuery);
//        String hqlQuery = "select e from Employee as e where " +
//                " e.salary > 500 and e.age < 50 and ceva  ";
//        session.createSQLQuery();
        Query query = session.createQuery(hqlQuery, Employee.class);

        List<Employee> employees = query.getResultList();

        for (int i = 0; i < employees.size(); i++) {
            Employee angajat = employees.get(i);
            System.out.println("Angajat: " + angajat);
        }

        Iterator<Employee> empIterator = employees.iterator();
        while (empIterator.hasNext()) {
            Employee employee = empIterator.next();
            System.out.println("Angajat: " + employee);
        }

        for (Employee employee : employees) {
            System.out.println("Angajat: " + employee);
        }
    }

    // TODO: query custom
    // TODO: select multiple
    // TODO: select with conditions
    // TODO: join

    static void addEmployee(String firstName, String lastName, float salary) {
        Employee employee = new Employee(firstName, lastName, salary);
        Transaction t = session.beginTransaction();
        session.save(employee);
        t.commit();
        System.out.println("Added employee: " + employee);
    }

    static void addJob(String title) {
        Job job = new Job(title);
        Transaction t = session.beginTransaction();
        session.save(job);
        t.commit();
        System.out.println("Job: " + job);
    }

    static Job getJob(int id) {
        return session.get(Job.class, id);
    }

    static Employee getEmployee(int id) {
        // SELECT * FROM `employees` WHERE `id` = {id}
        return session.get(Employee.class, id);
    }
}
