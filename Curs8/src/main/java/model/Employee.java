package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@NoArgsConstructor
@ToString
public class Employee {

    public Employee(String firstName, String lastName, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Setter
    private float salary;

    @Column(nullable = true, unique = true)
    private String cnp;

    @Column(name = "id_job")
    private Integer idJob;

    @Column(name = "birth_date")
    private Date birthDate;

    private short age;

}
