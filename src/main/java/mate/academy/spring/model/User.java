package mate.academy.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    public User() {
    }

    public User(String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
