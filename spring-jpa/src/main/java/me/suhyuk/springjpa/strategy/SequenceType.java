package me.suhyuk.springjpa.strategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
        name = "SEQUENCE_GENERATOR",
        sequenceName = "SEQUENCE",
        initialValue = 1,
        allocationSize = 1
)
public class SequenceType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_GENERATOR")
    private Long id;

    private String name;

    public SequenceType(String name) {
        this.name = name;
    }
}
