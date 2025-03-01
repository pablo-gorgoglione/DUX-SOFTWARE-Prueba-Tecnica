package com.base_api.dto.user;

import com.base_api.dto.common.PublicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends PublicDTO {
    String name;
    String username;
}
