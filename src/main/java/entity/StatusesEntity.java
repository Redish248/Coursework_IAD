package entity;

import javax.persistence.*;

@Entity
@Table(name = "statuses", schema = "public", catalog = "s243887")
public class StatusesEntity {
    private long statusid;
    private String name;
    private Integer priceid;
    private PricesEntity pricesByPriceid;

    @Id
    @Column(name = "statusid")
    public long getStatusid() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
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
    @Column(name = "priceid")
    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusesEntity that = (StatusesEntity) o;

        if (statusid != that.statusid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (priceid != null ? !priceid.equals(that.priceid) : that.priceid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (statusid ^ (statusid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (priceid != null ? priceid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "priceid", referencedColumnName = "priceid")
    public PricesEntity getPricesByPriceid() {
        return pricesByPriceid;
    }

    public void setPricesByPriceid(PricesEntity pricesByPriceid) {
        this.pricesByPriceid = pricesByPriceid;
    }
}
