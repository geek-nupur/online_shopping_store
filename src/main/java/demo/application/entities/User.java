package demo.application.entities;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(length = 60)
    private String email;

    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    // this is not working
    /*
    @Getter
    @Column
    @ElementCollection(targetClass = UserRoles.class)
    @Enumerated(EnumType.STRING)
    List<UserRoles> roles;

     */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles;

    @Column(name = "is_enabled", nullable = false)
    boolean isEnabled;

}
