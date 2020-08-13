package demo.application.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private UserPermissions role;

    public Role(UserPermissions permission) {
        this.role = permission;
    }

    @Override
    public String getAuthority() {
        return getRole().name();
    }


}
