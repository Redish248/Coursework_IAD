package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public", catalog = "s243887")
public class UsersEntity {
    private int userid;
    private String surname;
    private String name;
    private String nick;
    private Short height;
    private Short weight;
    private boolean sex;
    private Short district;
    private Date birthday;
    private String password;
    private String status;
    private Integer cash;
    private Collection<GamesEntity> gamesByUserid;
    private Collection<PresentstotributesEntity> presentstotributesByUserid;
    private Collection<TrainingsEntity> trainingsByUserid;
    private Collection<TributesEntity> tributesByUserid;
    private DistrictsEntity districtsByDistrict;
    private Collection<UserskillsEntity> userskillsByUserid;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "nick")
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Basic
    @Column(name = "height")
    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight")
    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "sex")
    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "district")
    public Short getDistrict() {
        return district;
    }

    public void setDistrict(Short district) {
        this.district = district;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "cash")
    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userid != that.userid) return false;
        if (sex != that.sex) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (cash != null ? !cash.equals(that.cash) : that.cash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (sex ? 1 : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersBySteward")
    public Collection<GamesEntity> getGamesByUserid() {
        return gamesByUserid;
    }

    public void setGamesByUserid(Collection<GamesEntity> gamesByUserid) {
        this.gamesByUserid = gamesByUserid;
    }

    @OneToMany(mappedBy = "usersBySenderid")
    public Collection<PresentstotributesEntity> getPresentstotributesByUserid() {
        return presentstotributesByUserid;
    }

    public void setPresentstotributesByUserid(Collection<PresentstotributesEntity> presentstotributesByUserid) {
        this.presentstotributesByUserid = presentstotributesByUserid;
    }

    @OneToMany(mappedBy = "usersByTrainer")
    public Collection<TrainingsEntity> getTrainingsByUserid() {
        return trainingsByUserid;
    }

    public void setTrainingsByUserid(Collection<TrainingsEntity> trainingsByUserid) {
        this.trainingsByUserid = trainingsByUserid;
    }

    @OneToMany(mappedBy = "usersByUserid")
    public Collection<TributesEntity> getTributesByUserid() {
        return tributesByUserid;
    }

    public void setTributesByUserid(Collection<TributesEntity> tributesByUserid) {
        this.tributesByUserid = tributesByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "districtid")
    public DistrictsEntity getDistrictsByDistrict() {
        return districtsByDistrict;
    }

    public void setDistrictsByDistrict(DistrictsEntity districtsByDistrict) {
        this.districtsByDistrict = districtsByDistrict;
    }

    @OneToMany(mappedBy = "usersByUserid")
    public Collection<UserskillsEntity> getUserskillsByUserid() {
        return userskillsByUserid;
    }

    public void setUserskillsByUserid(Collection<UserskillsEntity> userskillsByUserid) {
        this.userskillsByUserid = userskillsByUserid;
    }
}