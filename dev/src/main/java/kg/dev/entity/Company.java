package kg.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "social_network_id")
    private SocialNetwork socialNetwork;
    @OneToMany
    @JoinColumn(name = "vacancy_id")
    private List<Vacancy> vacancyList;

}
