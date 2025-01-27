package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.service.PopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/popes")
@RequiredArgsConstructor
public class PopeController {

    private final PopeService popeService;

    @GetMapping
    public List<PopeResponse> getAll() {
        return popeService.getAll();
    }
}
