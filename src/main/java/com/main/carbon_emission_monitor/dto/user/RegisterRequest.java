package com.main.carbon_emission_monitor.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {

    @NotNull(message = "用户名不能为空")
    @Length(min=4,max=10,message = "用户名必须在4-10位")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$",message = "用户名只能包含大小写字母和数字")
    private String username;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,16}$",message = "密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字")
    private String password;

    @NotNull(message = "昵称不能为控")
    @Length(min=2,max=10,message = "昵称必须在2-10位")
    private String nickname;

}
