package mate.academy.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
}
