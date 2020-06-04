package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "presents_to_tribute")
@ToString(of = {"sendingId", "quantity", "product"})
public class PresentsToTribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sending_id")
    private Integer sendingId;

    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Shop product;

    @ManyToOne
    @JoinColumn(name = "tribute_id", referencedColumnName = "tribute_id", nullable = false)
    private Tribute tribute;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

    public PresentsToTribute(Shop product, Tribute tribute, User sender, int quantity) {
        this.product = product;
        this.tribute = tribute;
        this.sender = sender;
        this.quantity = quantity;
    }

}
