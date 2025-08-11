package nocast.storeservice.user.dto;

import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class UserCreateDto {
    @Min(value = 4, message = "The minimum number of characters is 4")
    @NotNull(message = "The username cannot be empty")
    String username;
    @NotNull(message = "The password cannot be empty.")
    @Min(value = 5, message = "The password is too short")
    String password;
    @Email(message = "You entered your email address incorrectly")
    String email;
    @NotNull(message = "The name cannot be empty")
    @Min(value = 4, message = "The name must be at least 4 characters long")
    String firstName;
}
