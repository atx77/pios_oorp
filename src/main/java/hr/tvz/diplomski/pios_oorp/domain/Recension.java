package hr.tvz.diplomski.pios_oorp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "product"})
@EqualsAndHashCode(exclude = {"user", "product"})
@Entity(name = "recension")
public class Recension {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date creationDate;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
