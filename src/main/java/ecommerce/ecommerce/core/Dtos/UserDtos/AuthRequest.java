package ecommerce.ecommerce.core.Dtos.UserDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "user name cannot be empty")
    private String username;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 3, max = 50, message = "password must be between 3 and 50 characters.")
    private String password;
}
