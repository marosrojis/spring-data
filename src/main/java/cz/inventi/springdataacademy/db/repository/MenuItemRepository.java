package cz.inventi.springdataacademy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.inventi.springdataacademy.db.model.MenuItemEntity;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

  List<MenuItemEntity> findAllByMenuId(Long menuId);

}