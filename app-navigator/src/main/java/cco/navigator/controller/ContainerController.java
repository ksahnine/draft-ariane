package cco.navigator.controller;

import cco.navigator.dao.ContainerEntity;
import cco.navigator.dao.SystemEntity;
import cco.navigator.repository.ContainerRepository;
import cco.navigator.repository.SystemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
public class ContainerController {
  private final ContainerRepository containerRepository;
  private final SystemRepository systemRepository;

  public ContainerController(ContainerRepository containerRepository, SystemRepository systemRepository) {
    this.containerRepository = containerRepository;
    this.systemRepository = systemRepository;
  }

  @PutMapping
  ContainerEntity createOrUpdateSystem(@RequestBody ContainerEntity newContainer) {
    return containerRepository.save(newContainer);
  }

  @GetMapping
  List<ContainerEntity> getAllContainers() {
    return containerRepository.findAll();
  }

  @GetMapping("/{name}")
  ContainerEntity getByCodeName(@PathVariable String name) {
    return containerRepository.findOneByName(name);
  }

  @DeleteMapping("/{name}")
  void deleteByName(@PathVariable String name) {
    ContainerEntity containerEntityToDelete = getByCodeName(name);
    containerRepository.delete(containerEntityToDelete);
  }

  @PutMapping("/{name}/calls/{iua}")
  ContainerEntity addContainerToSystem(@PathVariable String name, @PathVariable String iua) {
    SystemEntity calledSystem = systemRepository.findOneByIua(iua);
    ContainerEntity container = getByCodeName(name);
    container.getCalls().add(calledSystem);
    containerRepository.save(container);
    return container;
  }

}
