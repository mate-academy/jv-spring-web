package mate.academy.spring.dao;

import java.util.Objects;

public class UserResponseDto {
    private Long id;
    private String name;
    private String lastname;

    public UserResponseDto(Long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResponseDto that = (UserResponseDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

    @Override
    public String toString() {
        return "UserResponseDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", lastname='" + lastname + '\''
                + '}';
    }
}
