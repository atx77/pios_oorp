package hr.tvz.diplomski.pios_oorp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"recensions", "category", "brand"})
@EqualsAndHashCode(exclude = {"recensions", "category", "brand"})
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String summary;
    private BigDecimal regularPrice;
    private BigDecimal actionPrice;
    private BigDecimal discount;
    private Integer availableQuantity;
    private String imageUrl;
    private Date creationDate;
    private boolean active;

    @OneToMany(mappedBy = "product")
    private List<Recension> recensions;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
