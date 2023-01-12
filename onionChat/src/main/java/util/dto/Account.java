package util.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Account {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_birth;
    private String user_phone;
    private String user_nick;
    private String user_msg;
    private String user_reg;
}
