package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "weapon")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weapon_id")
    private Integer weaponId;

    @Column(name = "name", length = 64, unique = true)
    private String name;

    @NotNull
    @Column(name = "type_of_weapon", length = 40)
    private String typeOfWeapon;

    @Min(0)
    @Max(100)
    @Column(name = "damage")
    private int damage;

    @Min(0)
    @Column(name = "radius_of_action")
    private int radiusOfAction;

    @Column(name = "picture")
    private byte[] picture;

    @JsonIgnore
    @OneToOne(mappedBy = "weapon")
    private Skill skill;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weapons_in_game",
            joinColumns = {@JoinColumn(name = "weapon_id")},
            inverseJoinColumns = {@JoinColumn(name = "tribute_id")}
    )
    private Collection<Tribute> owners;


    public Weapon(String name, String typeOfWeapon, int damage, int radiusOfAction, byte[] picture) {
        this.name = name;
        this.typeOfWeapon = typeOfWeapon;
        this.damage = damage;
        this.radiusOfAction = radiusOfAction;
        this.picture = picture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon that = (Weapon) o;
        return Objects.equals(weaponId, that.weaponId) &&
                damage == that.damage &&
                radiusOfAction == that.radiusOfAction &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeOfWeapon, that.typeOfWeapon) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(owners, that.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponId, name, typeOfWeapon, damage, radiusOfAction, picture, skill, owners);
    }


}
