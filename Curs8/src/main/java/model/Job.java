package model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    public Job() {
        this(null);
    }

    public Job(String title) {
        this.title = title;
    }

    @Column(name = "min_salary") // TODO: Add default value as 0
    private Float minSalary;

    @Column(name = "max_salary") // TODO: Add default value as NULL
    private Float maxSalary;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getMinSalary() {
        return minSalary;
    }

    public float getMaxSalary() {
        return maxSalary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMinSalary(float minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(float maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
