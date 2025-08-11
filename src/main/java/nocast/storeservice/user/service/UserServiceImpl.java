package nocast.storeservice.user.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.exception.UserNotFoundException;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.dto.UserReadDto;
import nocast.storeservice.user.persistence.User;
import nocast.storeservice.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper<User, UserReadDto> readDtoMapper;
    private final Pageable defaultPageable = PageRequest.of(0, 20);
    private final Mapper<UserFilter, Predicate> predicateMapper;
    private final Mapper<UserCreateDto, User> userCreateDtoMapper;

    @Override
    public Page<UserReadDto> findAll() {
        return userRepository.findAll(defaultPageable)
                .map(readDtoMapper::map);
    }

    @Override
    public Page<UserReadDto> findAll(Pageable pageable, UserFilter filter) {
        return userRepository.findAll(predicateMapper.map(filter), pageable)
                .map(readDtoMapper::map);
    }

    @Override
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(readDtoMapper::map);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser() {
        final var sec = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var user = userRepository.findByUsername(sec.getUsername());
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean register(UserCreateDto dto) {
        final var exists = userRepository.existsByUsername(dto.getUsername());
        if (!exists) {
            return false;
        }
        final var user = userCreateDtoMapper.map(dto);
        userRepository.saveAndFlush(user);
        return true;
    }
}
