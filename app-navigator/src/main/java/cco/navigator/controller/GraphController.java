package cco.navigator.controller;

import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/graph")
public class GraphController {
  private static String CONTAINS_REL = "CONTAINS";

  @Autowired
  private Neo4jClient neo4jClient;

  @CrossOrigin(origins = "*")
  @GetMapping("/paths/to/{iua}")
  public Map pathsToIua(@PathVariable String iua) {
    Collection<Map<String, Object>> resultQuery = neo4jClient.query("match path=(o:SystemEntity)-[*]->(a:SystemEntity)-[CONTAINS]->(unit) where a.iua='" + iua + "' return path")
                .fetch()
                .all();

    return convertToVisJsDataset(resultQuery);
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/paths/from/{iua}")
  public Map pathsFromIua(@PathVariable String iua) {
    Collection<Map<String, Object>> resultQuery = neo4jClient.query("match path=(unit)<-[CONTAINS]-(a)-[*]->(t:SystemEntity) where a.iua='" + iua + "' return path")
        .fetch()
        .all();

    return convertToVisJsDataset(resultQuery);
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/relationships/from/{iua}")
  public Map relationshipsFromIua(@PathVariable String iua) {
    Collection<Map<String, Object>> resultQuery = neo4jClient.query("match path=(o:SystemEntity)-[*]->(a:SystemEntity)-[CONTAINS]->(unit) where a.iua='" + iua + "' return path")
        .fetch()
        .all();

    return convertToVisJsDataset(resultQuery);
  }

  private Map<String, Object> convertToVisJsDataset(Collection<Map<String, Object>> resultQuery) {
    Map<String,Object> result = new HashMap<String,Object>();

    List<VisNode> nodes = new ArrayList<VisNode>();
    List<VisEdge> edges = new ArrayList<VisEdge>();

    for (Map<String, Object> element : resultQuery) {
      for(Object content : element.values()) {
        InternalPath path = (InternalPath) content;
        for (Node node : path.nodes()) {
          String label = node.get("description").asString();
          if ( node.get("iua") != null )
            label = node.get("iua").toString().replaceAll("\"", "");

          VisNode newNode = new VisNode(
              node.id(),
              label,
              node.labels().toString(),
              node.get("type").toString().replaceAll("\"", ""),
              node.get("zone").toString().replaceAll("\"", "")
          );
          if ( ! nodes.contains(newNode))
            nodes.add(newNode);
        };

        for (Relationship relationship : path.relationships()) {
          VisEdge newEdge = new VisEdge(
              relationship.startNodeId(),
              relationship.endNodeId(),
              relationship.type(),
              relationship.id());
          if ( ! edges.contains(newEdge)) {
            edges.add(newEdge);
          } else {
            //System.out.println( relationship.id() + " / " + relationship.startNodeId() + " -> " + relationship.endNodeId() + " / " + relationship.type() );
            VisEdge existingEdge = edges.get(edges.indexOf(newEdge));
            if ( ! existingEdge.neo4jRelIds.contains(relationship.id()) )
              existingEdge.neo4jRelIds.add(relationship.id());
          }
        }
      }
    }
    result.put("nodes", nodes);
    result.put("edges", edges);
    return result;
  }

  public class VisNode {
    private long id;
    private String label;
    private String artefact;
    private String type;
    private String zone;

    public VisNode(long id, String label, String artefact, String type, String zone) {
      this.id = id;
      this.label = label;
      this.artefact = artefact;
      this.type = type;
      this.zone = zone;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getLabel() {
      return label;
    }

    public void setLabel(String label) {
      this.label = label;
    }

    public String getArtefact() {
      return artefact;
    }

    public void setArtefact(String artefact) {
      this.artefact = artefact;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getZone() {
      return zone;
    }

    public void setZone(String zone) {
      this.zone = zone;
    }

    public String getGroup() {
      return zone;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      VisNode visNode = (VisNode) o;
      return id == visNode.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }

  public class VisEdge {
    private long from;
    private long to;
    private String relation;
    protected List<Long> neo4jRelIds = new ArrayList<Long>();

    public VisEdge(long from, long to, String relation, long neo4jRelId) {
      this.from = from;
      this.to = to;
      this.relation = relation;
      this.neo4jRelIds.add(neo4jRelId);
    }

    public long getFrom() {
      return from;
    }

    public void setFrom(long from) {
      this.from = from;
    }

    public long getTo() {
      return to;
    }

    public void setTo(long to) {
      this.to = to;
    }

    public String getRelation() {
      return relation;
    }

    public void setRelation(String relation) {
      this.relation = relation;
    }

    public String getArrows() {
      return "to";
    }

    public boolean isDashes() {
      if (CONTAINS_REL.equals(relation)) return true;
      return false;
    }

    public int getCount() {
      return neo4jRelIds.size();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      VisEdge visEdge = (VisEdge) o;
      return from == visEdge.from && to == visEdge.to && Objects.equals(relation, visEdge.relation);
    }

    @Override
    public int hashCode() {
      return Objects.hash(from, to, relation);
    }
  }
}
