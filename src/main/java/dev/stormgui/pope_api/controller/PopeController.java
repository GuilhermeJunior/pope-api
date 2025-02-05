package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.service.PopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/popes")
@RequiredArgsConstructor
public class PopeController {

    private final PopeService popeService;

    @Operation(summary = "Get all Popes")
    @GetMapping
    public Page<PopeResponse> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return popeService.findAllPage(page, size);
    }

    @Operation(summary = "Get a Pope by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the pope",
                    content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PopeResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Pope not found", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<PopeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(popeService.findById(id));
    }
}
