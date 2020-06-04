package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_skill")
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_skill_id")
    private Integer userSkillId;

    @Min(0)
    @Column(name = "level_of_skill")
    private int levelOfSkill;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id", nullable = false)
    private Skill skill;

    public UserSkill(User user, Skill skill, int levelOfSkill) {
        setUser(user);
        setSkill(skill);
        this.levelOfSkill = levelOfSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkill that = (UserSkill) o;
        return Objects.equals(userSkillId, that.userSkillId) &&
                levelOfSkill == that.levelOfSkill &&
                Objects.equals(user, that.user) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSkillId, levelOfSkill, user, skill);
    }
}
