package mate.academy.spring.model.dto;

public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;

    public UserResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" + "id=" + id
                + ", name='" + name + '\''
                + ", lastName='" + lastName
                + '\'' + '}';
    }
}
