package com.base_api.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PublicDTO {
    private String externalId;
    private Date createdAt;
    private Date updatedAt;
}
