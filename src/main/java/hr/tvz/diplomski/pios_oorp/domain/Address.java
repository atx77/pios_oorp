package hr.tvz.diplomski.pios_oorp.domain;

import hr.tvz.diplomski.pios_oorp.enumeration.CountryEnum;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String streetName;
    private String streetNumber;
    private String city;
    private Integer postcode;
    private CountryEnum country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
