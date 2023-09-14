package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_gen"
    )
    @SequenceGenerator(
            name = "comment_gen",
            sequenceName = "comment_seq",
            allocationSize = 1
    )
    private Long id;
    private String comment;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    public void addComment(Comment comment) {
        if (comment != null){
            comment.setComment(this.comment);
            comment.setUser(this.user);
        }else {
            throw  new RuntimeException("Comment is null!!!");
        }

    }




}