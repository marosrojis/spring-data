package cz.inventi.springdataacademy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cz.inventi.springdataacademy.db.model.MenuEntity;
import cz.inventi.springdataacademy.db.model.MenuItemEntity;
import cz.inventi.springdataacademy.db.repository.MenuItemRepository;
import cz.inventi.springdataacademy.db.repository.MenuRepository;
import cz.inventi.springdataacademy.dto.MenuItemDTO;

@RestController
public class MenuItemController {

  @Autowired
  private MenuItemRepository menuItemRepository;

  @Autowired
  private MenuRepository menuRepository;

  @GetMapping("/menu-items")
  List<MenuItemDTO> getAll() {
    return menuItemRepository.findAll().stream().map(this::toMenuItemDTO).collect(Collectors.toList());
  }

  @GetMapping("/menu/{id}/menu-items")
  List<MenuItemDTO> getAllByMenuId(@PathVariable Long id) {
    return menuItemRepository.findAllByMenuIdQuery(id).stream().map(this::toMenuItemDTO).collect(Collectors.toList());
  }

  @PostMapping("/menu-items")
  MenuItemDTO create(@RequestBody MenuItemDTO newMenu) {
    MenuEntity menuEntity = menuRepository.findById(newMenu.menuId())
        .orElseThrow(() -> new RuntimeException("ID not found"));

    MenuItemEntity menuItemEntity = menuItemRepository.save(toMenuItemEntity(newMenu, menuEntity));
    return toMenuItemDTO(menuItemEntity);
  }

  @GetMapping("/menu-items/{id}")
  MenuItemDTO getOne(@PathVariable Long id) {
    return toMenuItemDTO(menuItemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ID not found")));
  }

  @DeleteMapping("/menu-items/{id}")
  void delete(@PathVariable Long id) {
    menuItemRepository.deleteById(id);
  }

  private MenuItemDTO toMenuItemDTO(MenuItemEntity entity) {
    return new MenuItemDTO(entity.getId(), entity.getName(), entity.getMenu().getId());
  }

  private MenuItemEntity toMenuItemEntity(MenuItemDTO dto, MenuEntity menuEntity) {
    MenuItemEntity entity = new MenuItemEntity();
    entity.setId(dto.id());
    entity.setName(dto.name());
    entity.setMenu(menuEntity);
    return entity;
  }
}

