package yte.intern.spring.application.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import yte.intern.spring.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {
    private String authority;
    public Authority(String authority) {
        this.authority = authority;
    }
}


