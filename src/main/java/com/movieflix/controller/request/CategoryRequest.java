package com.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(  @Schema(type = "string", description = "Nome da categoria")
                                @NotEmpty(message = "Nome da categoria é obrigatório.")
                                String name) {
}
