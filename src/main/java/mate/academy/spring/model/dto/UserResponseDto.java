package mate.academy.spring.model.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;

    public UserResponseDto(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
