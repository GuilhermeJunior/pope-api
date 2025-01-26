package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.service.PopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/popes")
@RequiredArgsConstructor
public class PopeController {

    private final PopeService popeService;

}
