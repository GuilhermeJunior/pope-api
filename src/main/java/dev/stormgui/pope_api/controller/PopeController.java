package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.service.PopeService;
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

    @GetMapping
    public Page<PopeResponse> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return popeService.findAllPage(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PopeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(popeService.findById(id));
    }
}
