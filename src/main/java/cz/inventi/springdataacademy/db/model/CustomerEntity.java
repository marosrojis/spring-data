package cz.inventi.springdataacademy.db.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomerEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToMany
  @JoinTable(
      name = "customer_menu",
      joinColumns = @JoinColumn(name = "customerId"),
      inverseJoinColumns = @JoinColumn(name = "menuId"))
  private Set<MenuEntity> menu;

  public CustomerEntity() {}

  public CustomerEntity(Long id, String name) {
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

  public void setMenu(Set<MenuEntity> menu) {
    this.menu = menu;
  }

  public Set<MenuEntity> getMenu() {
    return menu;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerEntity that = (CustomerEntity) o;
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
}
