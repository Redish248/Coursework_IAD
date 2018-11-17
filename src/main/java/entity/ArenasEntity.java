package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "arenas", schema = "public", catalog = "postgres")
public class ArenasEntity {
    //TODO: one to one to gameEntity
    private int arenaid;
    private short arenaLength;
    private short arenaWidth;
    private String typeoflocation;
    private Collection<GamesEntity> gamesByArenaid;

    public ArenasEntity() {
        gamesByArenaid = new HashSet<GamesEntity>();
    }

    public ArenasEntity(short arenaLength, short arenaWidth, String typeoflocation) {
        this.arenaLength = arenaLength;
        this.arenaWidth = arenaWidth;
        this.typeoflocation = typeoflocation;
        gamesByArenaid = new HashSet<GamesEntity>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arenaid")
    public int getArenaid() {
        return arenaid;
    }

    public void setArenaid(int arenaid) {
        this.arenaid = arenaid;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_length")
    public short getArenaLength() {
        return arenaLength;
    }

    public void setArenaLength(short arenaLength) {
        this.arenaLength = arenaLength;
    }

    @Basic
    @Min(0)
    @NotNull
    @Column(name = "arena_width")
    public short getArenaWidth() {
        return arenaWidth;
    }

    public void setArenaWidth(short arenaWidth) {
        this.arenaWidth = arenaWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArenasEntity that = (ArenasEntity) o;

        if (arenaid != that.arenaid) return false;
        if (arenaLength != that.arenaLength) return false;
        if (arenaWidth != that.arenaWidth) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = arenaid;
        result = 31 * result + (int) arenaLength;
        result = 31 * result + (int) arenaWidth;
        return result;
    }

    @Basic
    @NotNull
    @Column(name = "typeoflocation", length = 40)
    public String getTypeoflocation() {
        return typeoflocation;
    }

    public void setTypeoflocation(String typeoflocation) {
        this.typeoflocation = typeoflocation;
    }

    @OneToMany(mappedBy = "arenasByArena")
    public Collection<GamesEntity> getGamesByArenaid() {
        return gamesByArenaid;
    }

    public void setGamesByArenaid(Collection<GamesEntity> gamesByArenaid) {
        this.gamesByArenaid = gamesByArenaid;
    }
}
