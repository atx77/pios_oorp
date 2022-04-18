package hr.tvz.diplomski.pios_oorp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Cart domain model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"items", "user"})
@EqualsAndHashCode(exclude = {"items", "user"})
@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date creationDate;

    private BigDecimal totalPrice;
}
