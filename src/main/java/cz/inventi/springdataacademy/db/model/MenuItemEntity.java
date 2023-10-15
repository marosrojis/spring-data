package cz.inventi.springdataacademy.db.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuItemEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "menu_id", nullable = false)
  private MenuEntity menu;

  public MenuItemEntity() {}

  public MenuItemEntity(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuItemEntity that = (MenuItemEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "MenuItemEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public MenuEntity getMenu() {
    return menu;
  }

  public void setMenu(MenuEntity menu) {
    this.menu = menu;
  }
}
