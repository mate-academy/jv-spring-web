package mate.academy.spring.service.mapper;

import java.util.List;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserResponceDtoMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userLastName", source = "user.lastName")
    UserResponseDto userToUserResponceDto(User user);

    List<UserResponseDto> userListToUserResponceDtoList(List<User> users);
}
