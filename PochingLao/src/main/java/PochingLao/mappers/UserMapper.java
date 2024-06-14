package PochingLao.mappers;

import PochingLao.dto.SignUpDto;
import PochingLao.dto.UserDto;
import PochingLao.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
