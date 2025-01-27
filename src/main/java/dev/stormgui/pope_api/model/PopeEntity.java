package dev.stormgui.pope_api.model;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "popes")
public class PopeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String pontificate;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    public PopeResponse toDTO() {
        return new PopeResponse(this.name, this.pontificate, this.placeOfBirth);
    }
}
