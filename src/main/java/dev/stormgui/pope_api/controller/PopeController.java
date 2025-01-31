package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.model.dto.PopesResponse;
import dev.stormgui.pope_api.service.PopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/popes")
@RequiredArgsConstructor
public class PopeController {

    private final PopeService popeService;

    @GetMapping
    public ResponseEntity<PopesResponse> getAll() {
        return ResponseEntity.ok(popeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PopeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(popeService.findById(id));
    }
}
