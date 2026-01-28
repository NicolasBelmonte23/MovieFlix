package com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(@Schema(type = "string", description = "Nome do filme")
                           @NotEmpty(message = "Nome do serviço de filmes é obrigatório.")
                           String title,
                           @Schema(type = "string", description = "Descrição do filme")
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           @Schema(type = "date", description = "Data de lançamento do filme. ex: 10/01/2010")
                           LocalDate releaseDate,
                           @Schema(type = "doble", description = "Score do filme. ex: 9.5")
                           double rating,
                           @Schema(type = "array", description = "Lista de códigos de cátegoria.")
                           List<Long> categories,
                           @Schema(type = "array", description = "Lista de código de streaming.")
                           List<Long> streamings

)
{

}
