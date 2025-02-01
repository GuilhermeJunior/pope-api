package dev.stormgui.pope_api.service;

import dev.stormgui.pope_api.model.PopeEntity;
import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.model.dto.PopesResponse;
import dev.stormgui.pope_api.repository.PopeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopeService {

    private final PopeEntityRepository popeEntityRepository;

    public PopesResponse getAll() {
        List<PopeResponse> popes = popeEntityRepository.findAll()
                .stream()
                .map(PopeEntity::toDTO)
                .toList();
        return new PopesResponse(popes);
    }

    public PopeResponse findById(Long id) {
       return popeEntityRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Not found ID"))
               .toDTO();
    }

    public Page<PopeResponse> findAllPage(int pages, int size) {
        return popeEntityRepository.findAll(PageRequest.of(pages, size)).map(PopeEntity::toDTO);
    }
}
