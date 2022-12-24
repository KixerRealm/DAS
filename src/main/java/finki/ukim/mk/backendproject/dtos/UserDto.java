package finki.ukim.mk.backendproject.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String email;
}
