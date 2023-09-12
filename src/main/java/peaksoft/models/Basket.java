package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;


@Entity
@Table(name = "baskets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Basket {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "basket_gen"
    )
    @SequenceGenerator(
            name = "basket_gen",
            sequenceName = "basket_seq",
            allocationSize = 1
    )
    private Long id;
    @ManyToMany(mappedBy = "baskets",fetch = EAGER,cascade = ALL)
    private List<Product> products;

    @OneToOne
    private User user;
}