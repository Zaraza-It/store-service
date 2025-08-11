package nocast.storeservice.user.service;

import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.dto.UserReadDto;
import nocast.storeservice.user.persistence.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<UserReadDto> findAll();

    Page<UserReadDto> findAll(Pageable pageable, UserFilter filter);

    Optional<UserReadDto> findById(Long id);

    Optional<User> findByUsername(String username);

    User getUser();

    boolean register(UserCreateDto dto);
}
