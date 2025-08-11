package nocast.storeservice.user.mapper;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.user.dto.TypeRole;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.persistence.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .email(object.getEmail())
                .roles(Collections.singletonList(TypeRole.ROLE_USER.toString()))
                .firstName(object.getFirstName())
                .password(passwordEncoder.encode(object.getPassword()))
                .phoneNumber(null)
                .build();
    }
}
