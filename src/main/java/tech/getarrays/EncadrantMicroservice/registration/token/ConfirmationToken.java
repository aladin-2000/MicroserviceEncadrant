package tech.getarrays.EncadrantMicroservice.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="ConfirmationToken_Encadrant")
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence_Encadrant",
            sequenceName = "confirmation_token_sequence_Encadrant",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence_Encadrant"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "encadrant_id"
    )
    private Encadrant encadrant;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Encadrant encadrant) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.encadrant = encadrant;
    }
}
