package com.example.manas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "url")
    private String url;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "company")
    private String company;
    @Column(name = "company_url")
    private String company_url;
    @Column(name = "location")
    private String location;
    @Column(name = "title")
    private String title;
    @Column(name = "description", length = 10485760)
    private String description;
    @Column(name = "how_to_apply")
    private String how_to_apply;
    @Column(name = "company_logo")
    private String company_logo;
}
