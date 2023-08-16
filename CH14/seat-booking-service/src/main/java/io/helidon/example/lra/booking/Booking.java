package io.helidon.example.lra.booking;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

@Entity(name = "Booking")
@Access(AccessType.PROPERTY)
@NamedQueries({
        @NamedQuery(name = "getBookingBySeat",
                query = "SELECT b FROM Booking b INNER JOIN b.seat s WHERE s.id = :id"),
        @NamedQuery(name = "getBookingByLraId",
                query = "SELECT b FROM Booking b INNER JOIN b.seat s WHERE b.lraId = :lraId"),
})
public class Booking {

    private Long id;
    private String firstName;
    private String lraId;
    private Seat seat;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLraId() {
        return lraId;
    }

    @OneToOne
    @JsonbTransient
    public Seat getSeat() {
        return seat;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLraId(String lraId) {
        this.lraId = lraId;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
