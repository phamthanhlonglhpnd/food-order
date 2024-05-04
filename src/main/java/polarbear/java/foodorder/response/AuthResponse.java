package polarbear.java.foodorder.response;

import lombok.Data;
import polarbear.java.foodorder.enums.USER_ROLE;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
