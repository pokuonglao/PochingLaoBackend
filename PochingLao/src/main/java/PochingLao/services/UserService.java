
package PochingLao.services;

import PochingLao.entity.User;
import PochingLao.dto.CredentialsDto;
import PochingLao.dto.UserDto;
import PochingLao.dto.SignUpDto;
import PochingLao.mappers.UserMapper;
import PochingLao.Repo.UserRepository;
import PochingLao.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

// Annotate the class as a service and indicate required arguments for constructor-based injection
@Service
@RequiredArgsConstructor
public class UserService {
    // Declare final fields for user repository, password encoder, and user mapper
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Method to handle user login
    public UserDto login(CredentialsDto credentialsDto) {
        // Retrieve user from the repository based on the provided login
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                // Throw an exception if the user is not found
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        // Check if the provided password matches the user's password
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            // If the password matches, return the user DTO mapped from the user entity
            return userMapper.toUserDto(user);
        }
        // If the password does not match, throw an exception
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    // Method to register a new user
    public UserDto register(SignUpDto signUpDto) {
        // Check if a user with the provided login already exists
        Optional<User> existingUser = userRepository.findByLogin(signUpDto.getLogin());
        if (existingUser.isPresent()) {
            // If a user with the provided login already exists, throw an exception
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }
        // Map the sign-up DTO to a user entity and set the password using the password encoder
        User newUser = userMapper.signUpToUser(signUpDto);
        newUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        // Save the new user in the repository and return the user DTO mapped from the saved user entity
        User savedUser = userRepository.save(newUser);
        return userMapper.toUserDto(savedUser);
    }
}
