package cz.inventi.springdataacademy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.inventi.springdataacademy.db.model.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

}