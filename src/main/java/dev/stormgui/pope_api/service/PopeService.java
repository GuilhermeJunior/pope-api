package dev.stormgui.pope_api.service;

import dev.stormgui.pope_api.repository.PopeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PopeService {

    private final PopeEntityRepository popeEntityRepository;
}
