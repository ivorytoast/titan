package backend.nyc.com.titan.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "session_fct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@IdClass(SessionRelationshipId.class)
public class SessionDB implements Serializable {

    @Id
    @Column(name = "session_id")
    private String id;

    @Id
    @Column(name = "board_version")
    private int version;

    @Column(name = "board")
    private String board;

}