package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dto class for entity {@link com.epam.esm.giftcertificates.entity.User}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "NotNull.user.username")
    @Size(min = 4, max = 35, message = "Size.user.username")
    @Pattern(regexp = "[A-Za-z\\d-.@_]+", message = "Pattern.user.username")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userRole;

    @NotNull(message = "NotNull.user.password")
    @Size(min = 8, max = 60, message = "Size.user.password")
    @Pattern(regexp = "[A-Za-z\\d_-]+", message = "Pattern.user.password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(final String userRole) {
        this.userRole = userRole;
    }

}
