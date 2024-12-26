package ecommerce.ecommerce.core.Dtos.UserDtos;

import ecommerce.ecommerce.model.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @NotBlank(message = "name cannot be empty")
    String name;
    @NotBlank(message = "user name can not be empty")
    String username;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 3, max = 50, message = "password must be between 3 and 50 characters.")
    String password;

    Set<Role> authorities;
}
