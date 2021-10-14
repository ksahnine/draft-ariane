package cco.navigator.dao;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class ContainerEntity {
  @Id
  private String name;
  @Property
  private String description;
  @Property
  private String type;
  @Relationship(type = "CALLS", direction = Relationship.Direction.OUTGOING)
  private Set<SystemEntity> calls = new HashSet<>();

  public ContainerEntity(String name) {
    this.name = name;
  }

  public ContainerEntity() {
  }

  public ContainerEntity(String name, String description, String type) {
    this.name = name;
    this.description = description;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Set<SystemEntity> getCalls() {
    return calls;
  }
}
