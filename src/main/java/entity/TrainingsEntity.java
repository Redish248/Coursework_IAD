package entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "trainings", schema = "public", catalog = "s243887")
public class TrainingsEntity {
    private int trainingid;
    private String name;
    private Integer skillid;
    private Short coefficient;
    private Short duration;
    private String description;
    private Integer trainer;
    private Time timeoftraining;
    private Short dayofweek;
    private SkillsEntity skillsBySkillid;
    private UsersEntity usersByTrainer;

    @Id
    @Column(name = "trainingid")
    public int getTrainingid() {
        return trainingid;
    }

    public void setTrainingid(int trainingid) {
        this.trainingid = trainingid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "skillid")
    public Integer getSkillid() {
        return skillid;
    }

    public void setSkillid(Integer skillid) {
        this.skillid = skillid;
    }

    @Basic
    @Column(name = "coefficient")
    public Short getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Short coefficient) {
        this.coefficient = coefficient;
    }

    @Basic
    @Column(name = "duration")
    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "trainer")
    public Integer getTrainer() {
        return trainer;
    }

    public void setTrainer(Integer trainer) {
        this.trainer = trainer;
    }

    @Basic
    @Column(name = "timeoftraining")
    public Time getTimeoftraining() {
        return timeoftraining;
    }

    public void setTimeoftraining(Time timeoftraining) {
        this.timeoftraining = timeoftraining;
    }

    @Basic
    @Column(name = "dayofweek")
    public Short getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(Short dayofweek) {
        this.dayofweek = dayofweek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingsEntity that = (TrainingsEntity) o;

        if (trainingid != that.trainingid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (skillid != null ? !skillid.equals(that.skillid) : that.skillid != null) return false;
        if (coefficient != null ? !coefficient.equals(that.coefficient) : that.coefficient != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (trainer != null ? !trainer.equals(that.trainer) : that.trainer != null) return false;
        if (timeoftraining != null ? !timeoftraining.equals(that.timeoftraining) : that.timeoftraining != null)
            return false;
        if (dayofweek != null ? !dayofweek.equals(that.dayofweek) : that.dayofweek != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainingid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (skillid != null ? skillid.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
        result = 31 * result + (timeoftraining != null ? timeoftraining.hashCode() : 0);
        result = 31 * result + (dayofweek != null ? dayofweek.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid")
    public SkillsEntity getSkillsBySkillid() {
        return skillsBySkillid;
    }

    public void setSkillsBySkillid(SkillsEntity skillsBySkillid) {
        this.skillsBySkillid = skillsBySkillid;
    }

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "userid")
    public UsersEntity getUsersByTrainer() {
        return usersByTrainer;
    }

    public void setUsersByTrainer(UsersEntity usersByTrainer) {
        this.usersByTrainer = usersByTrainer;
    }
}