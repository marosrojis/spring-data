package cz.inventi.springdataacademy.db.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class MenuEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Boolean enabled;

  @OneToMany(mappedBy = "menu")
  private List<MenuItemEntity> menuItems = new ArrayList<>();

  @ManyToMany(mappedBy = "menu")
  private Set<CustomerEntity> customers;

  public MenuEntity() {}

  public MenuEntity(Long id, String name, Boolean enabled) {
    this.id = id;
    this.name = name;
    this.enabled = enabled;
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


  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<MenuItemEntity> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(List<MenuItemEntity> menuItems) {
    this.menuItems = menuItems;
  }

  public Set<CustomerEntity> getCustomers() {
    return customers;
  }

  public void setCustomers(Set<CustomerEntity> customers) {
    this.customers = customers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuEntity that = (MenuEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(enabled, that.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name,  enabled);
  }

  @Override
  public String toString() {
    return "MenuEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", enabled=" + enabled +
        '}';
  }
}
