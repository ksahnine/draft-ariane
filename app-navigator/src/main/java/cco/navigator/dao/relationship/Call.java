package cco.navigator.dao.relationship;

import cco.navigator.dao.SystemEntity;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Call {
  @Id
  @GeneratedValue
  Long id;
  @Property
  private String type;
  @TargetNode
  private SystemEntity to;

  public Call() {}

  public Call(SystemEntity to, String type) {
    this.to = to;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public SystemEntity getTo() {
    return to;
  }

  public void setTo(SystemEntity to) {
    this.to = to;
  }
}

