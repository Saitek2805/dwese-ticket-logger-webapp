package org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;



@Data  // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor  // Genera un constructor sin parámetros
@AllArgsConstructor  // Genera un constructor con todos los campos de la clase
public class Province {


    private Integer id;          // Identificador único de la provincia
    private String code;     // Código de la provincia
    private String name;     // Nombre de la provincia
    private Region region;   // Relación con la entidad Region (comunidad autónoma)



}
