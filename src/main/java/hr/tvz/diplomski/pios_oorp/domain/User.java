package hr.tvz.diplomski.pios_oorp.domain;

import hr.tvz.diplomski.pios_oorp.enumeration.UserType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * User domain model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orders", "addresses", "recensions", "cart"})
@EqualsAndHashCode(exclude = {"orders", "addresses", "recensions", "cart"})
@Entity(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Recension> recensions;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
