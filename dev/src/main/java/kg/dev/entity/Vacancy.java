package kg.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "vacancy")
public class Vacancy extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(name = "position")
    private String position;
    @Column(name = "description")
    private String description;
    @Column(name = "vacancy_type")
    private String type;
    @Column(name = "salary")
    private String salary;
    @ManyToOne
    @JoinColumn(name = "social_network_id")
    private SocialNetwork socialNetwork;

}