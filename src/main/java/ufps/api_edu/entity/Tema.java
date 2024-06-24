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
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @Lob
    private String descripcion;

    private double aprobacion;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    private List<Recurso> recursos = new ArrayList<>();

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas = new ArrayList<>();

    private boolean enabled;
}
