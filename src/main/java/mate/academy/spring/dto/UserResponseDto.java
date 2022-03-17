package mate.academy.spring.dto;

public class UserResponseDto {
    private String name;
    private String lastName;

    public UserResponseDto(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
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
        return "UserResponseDto{"
                + "name='" + name + '\''
                + ", lastName='" + lastName + '\'' + '}';
    }
}
