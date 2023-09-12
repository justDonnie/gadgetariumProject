package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Brand {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "brand_gen"
    )
    @SequenceGenerator(
            name = "brand_gen",
            sequenceName = "brand_seq",
            allocationSize = 1
    )
    private Long id;
    private String brandName;
    private String image;
    @OneToMany(mappedBy = "brand",fetch = EAGER,cascade = ALL)
    private List<Product> products;
}