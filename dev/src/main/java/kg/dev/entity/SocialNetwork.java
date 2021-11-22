package kg.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "social_network")
public class SocialNetwork extends BaseEntity{
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "telegram")
    private String telegram;
    @Column(name = "phone_number")
    private String phoneNumber;
}
