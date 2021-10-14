package cco.navigator.repository;

import cco.navigator.dao.SystemEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemRepository extends Neo4jRepository<SystemEntity, String> {
  SystemEntity findOneByIua(String iua);

  @Query("MATCH (system:SystemEntity) WHERE system.description CONTAINS $description RETURN system")
  List<SystemEntity> findSearchByDescription(@Param("description") String description);

  @Query("MATCH (system:SystemEntity), (container:ContainerEntity) WHERE system.iua = $iua AND container.name = $containerName CREATE (system)-[r:CONTAINS]->(container) RETURN type(r)")
  SystemEntity addContainer(@Param("iua") String iua, @Param("containerName") String containerName);

  @Query("MATCH (n) DETACH DELETE n")
  void removeAll();

  //@Query("MATCH (from:SystemEntity)-[:CONTAINS]->(p:ContainerEntity)-[:CALLS]->(to:SystemEntity) WHERE from.iua = $iua RETURN from, to, apoc.create.vRelationship(from,'DEPENDS_ON',{via:p.name},to) as rel")
  @Query("MATCH path=(from:SystemEntity)-[:CONTAINS]->(p:ContainerEntity)-[:CALLS]->(to:SystemEntity) WHERE from.iua = $iua RETURN path")
  List<SystemEntity> findSystemDependenciesByIua(@Param("iua") String iua);
}
