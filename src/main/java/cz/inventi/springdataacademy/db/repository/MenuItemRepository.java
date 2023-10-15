package cz.inventi.springdataacademy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cz.inventi.springdataacademy.db.model.MenuItemEntity;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

  List<MenuItemEntity> findAllByMenuId(Long menuId);

  @Query("SELECT m FROM MenuItemEntity m WHERE m.menu.id = ?1")
  List<MenuItemEntity> findAllByMenuIdQuery(Long menuId);

}