package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "skill")
@ToString(of = {"skillId", "name", "description", "typeOfSkill"})
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;
    @NotNull
    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "type_of_skill", length = 42)
    private String typeOfSkill;

    @JsonIgnore
    @OneToOne(mappedBy = "skill")
    private District district;

    @OneToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "weapon_id")
    private Weapon weapon;

    @JsonIgnore
    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<Training> trainings;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Collection<User> users;

    public Skill(String name, String description, String typeOfSkill, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.typeOfSkill = typeOfSkill;
        this.weapon = weapon;
    }

}
