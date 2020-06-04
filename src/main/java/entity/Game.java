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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameId;

    @NotNull
    @Column(name = "type_of_game")
    private boolean typeOfGame;

    @Min(0)
    @Column(name = "number_of_tributes")
    private int numberOfTributes;

    @NotNull
    @Column(name = "start_date")
    private Calendar startDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "steward", referencedColumnName = "user_id")
    private User steward;

    @OneToOne
    @JoinColumn(name = "arena", referencedColumnName = "arena_id")
    private Arena arena;

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<Tribute> tributes;

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<WeaponsInGame> weaponsInGames;


    public Game(boolean typeOfGame, User steward, Arena arena, int numberOfTributes, Calendar startDate) {
        this.typeOfGame = typeOfGame;
        this.numberOfTributes = numberOfTributes;
        this.startDate = startDate;
        this.steward = steward;
        this.arena = arena;
        this.status = "created";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(gameId,that.gameId) &&
                typeOfGame == that.typeOfGame &&
                numberOfTributes == that.numberOfTributes &&
                duration == that.duration &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(steward, that.steward) &&
                Objects.equals(arena, that.arena) &&
                Objects.equals(tributes, that.tributes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameId, typeOfGame, numberOfTributes, startDate, duration, steward, arena, tributes);
    }

}
