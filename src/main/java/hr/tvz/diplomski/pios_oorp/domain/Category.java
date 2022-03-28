package hr.tvz.diplomski.pios_oorp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private Date creationDate;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    public Category parentCategory;

    @OneToMany(mappedBy="parentCategory", cascade = CascadeType.ALL)
    public List<Category> subCategories;


    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
