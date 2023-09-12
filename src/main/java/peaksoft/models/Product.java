package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_gen"
    )
    @SequenceGenerator(
            name = "product_gen",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private BigDecimal price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany
    private List<Basket>baskets;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "product",fetch = EAGER,cascade = ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "product",fetch = EAGER,cascade = ALL)
    private List<Favorite>favorites;


}