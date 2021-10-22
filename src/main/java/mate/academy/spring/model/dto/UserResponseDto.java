package mate.academy.spring.model.dto;

public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;

    public Long getId() {
        return id;
    }

    public UserResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserResponseDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
