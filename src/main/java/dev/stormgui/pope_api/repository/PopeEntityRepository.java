package dev.stormgui.pope_api.repository;

import dev.stormgui.pope_api.model.PopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopeEntityRepository extends JpaRepository<PopeEntity, Long> {
}
