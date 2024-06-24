package ufps.api_edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @Lob
    private String descripcion;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Tema> temas = new ArrayList<>();

    private boolean enabled;

}
