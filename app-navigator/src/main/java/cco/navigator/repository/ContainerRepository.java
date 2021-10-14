package cco.navigator.repository;

import cco.navigator.dao.ContainerEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContainerRepository extends Neo4jRepository<ContainerEntity, String> {
  ContainerEntity findOneByName(String name);

  @Query("MATCH (system:SystemEntity)-[r:CONTAINS]->(container:ContainerEntity) WHERE system.iua = $iua RETURN container")
  List<ContainerEntity> findContainersInSystemByIua(@Param("iua") String description);
}
