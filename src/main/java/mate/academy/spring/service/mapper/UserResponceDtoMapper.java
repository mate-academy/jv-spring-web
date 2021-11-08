package mate.academy.spring.service.mapper;

import java.util.List;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserResponceDtoMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastName", source = "user.lastName")
    UserResponseDto userToUserResponceDto(User user);

    List<UserResponseDto> userListToUserResponceDtoList(List<User> users);
}
