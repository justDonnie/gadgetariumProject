package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;


@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Favorite {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "favorite_gen"
    )
    @SequenceGenerator(
            name = "favorite_gen",
            sequenceName = "favorite_seq",
            allocationSize = 1
    )
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

}