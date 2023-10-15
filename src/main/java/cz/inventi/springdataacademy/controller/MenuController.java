package cz.inventi.springdataacademy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.inventi.springdataacademy.db.model.MenuEntity;
import cz.inventi.springdataacademy.db.repository.MenuRepository;
import cz.inventi.springdataacademy.dto.MenuDTO;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

  @Autowired
  private MenuRepository menuRepository;

  @GetMapping()
  List<MenuDTO> getAll() {
    return menuRepository.findAll().stream().map(this::toMenuDTO).collect(Collectors.toList());
  }

  @PostMapping()
  MenuDTO create(@RequestBody MenuDTO newMenu) {
    MenuEntity menuEntity = menuRepository.save(toMenuEntity(newMenu));
    return toMenuDTO(menuEntity);
  }

  @GetMapping("/{id}")
  MenuDTO getOne(@PathVariable Long id) {
    return toMenuDTO(menuRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ID not found")));
  }

  @PutMapping("/{id}")
  MenuDTO update(@RequestBody MenuDTO newMenu, @PathVariable Long id) {
    return toMenuDTO(menuRepository.findById(id)
        .map(menu -> {
          menu.setName(newMenu.name());
          menu.setEnabled(newMenu.enabled());

          return menuRepository.save(menu);
        })
        .orElseThrow(() -> new RuntimeException("ID not found")));
  }

  @DeleteMapping("/{id}")
  void delete(@PathVariable Long id) {
    menuRepository.deleteById(id);
  }

  private MenuDTO toMenuDTO(MenuEntity entity) {
    return new MenuDTO(entity.getId(), entity.getName(), entity.getEnabled());
  }

  private MenuEntity toMenuEntity(MenuDTO dto) {
    return new MenuEntity(dto.id(), dto.name(), dto.enabled());
  }
}

