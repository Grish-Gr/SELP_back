package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "article_pages")
public class ArticlePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "html_content", columnDefinition="text")
    private String htmlContent;
}
