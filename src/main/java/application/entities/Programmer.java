package application.entities;

import application.annotations.InjectRandomInt;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "programmers")
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Programmer extends Person {
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surName;

    @OneToMany(mappedBy = "programmer", fetch = FetchType.EAGER)

    private List<ProgrammerTask> task = new ArrayList<>();

}
