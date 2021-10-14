package cco.navigator.dao;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class SystemEntity {
  @Id
  private String iua;
  @Property
  private String description;
  @Property
  private String zone;
  @Relationship(type = "CONTAINS", direction = Relationship.Direction.OUTGOING)
  private Set<ContainerEntity> containers = new HashSet<>();
  @Relationship(type = "DEPENDS_ON", direction = Relationship.Direction.OUTGOING)
  private Set<SystemEntity> dependencies = new HashSet<>();

  public SystemEntity(String iua) {
    this.iua = iua;
  }

  public SystemEntity() {
  }

  public SystemEntity(String iua, String description, String zone) {
    this.iua = iua;
    this.description = description;
    this.zone = zone;
  }

  public String getIua() {
    return iua;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public Set<ContainerEntity> getContainers() {
    return containers;
  }

  public Set<SystemEntity> getDependencies() {
    return dependencies;
  }

  public void setDependencies(Set<SystemEntity> dependencies) {
    this.dependencies = dependencies;
  }
}
