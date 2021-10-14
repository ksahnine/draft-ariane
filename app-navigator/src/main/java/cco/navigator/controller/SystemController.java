package cco.navigator.controller;

import cco.navigator.dao.ContainerEntity;
import cco.navigator.dao.SystemEntity;
import cco.navigator.repository.ContainerRepository;
import org.springframework.web.bind.annotation.*;
import cco.navigator.repository.SystemRepository;

import java.util.List;

@RestController
@RequestMapping("/systems")
public class SystemController {
  private final SystemRepository systemRepository;
  private final ContainerRepository containerRepository;

  public SystemController(SystemRepository systemRepository, ContainerRepository containerRepository) {
    this.systemRepository = systemRepository;
    this.containerRepository = containerRepository;
  }

  @PutMapping
  SystemEntity createOrUpdateSystem(@RequestBody SystemEntity newSystem) {
    return systemRepository.save(newSystem);
  }

  @GetMapping
  List<SystemEntity> getAllSystems() {
    return systemRepository.findAll();
  }

  @GetMapping("/{iua}")
  SystemEntity getByCodeIua(@PathVariable String iua) {
    return systemRepository.findOneByIua(iua);
  }

  @DeleteMapping("/{iua}")
  void deleteByCodeIua(@PathVariable String iua) {
    SystemEntity systemEntityToDelete = getByCodeIua(iua);
    systemRepository.delete(systemEntityToDelete);
  }

  @GetMapping("/{iua}/containers")
  List<ContainerEntity> getContainersByCodeIua(@PathVariable String iua) {
    return containerRepository.findContainersInSystemByIua(iua);
  }

  @GetMapping("/{iua}/dependencies")
  List<SystemEntity> getSystemDependenciesByCodeIua(@PathVariable String iua) {
    return systemRepository.findSystemDependenciesByIua(iua);
  }

  @PutMapping("/{iua}/contains/{containerName}")
  SystemEntity addContainerToSystem(@PathVariable String iua, @PathVariable String containerName) {
    ContainerEntity containerToAdd = containerRepository.findOneByName(containerName);
    SystemEntity system = getByCodeIua(iua);
    system.getContainers().add(containerToAdd);
    systemRepository.save(system);
    return system;
    //systemRepository.addContainer(iua, containerName);
  }

  @DeleteMapping
  void removeAll() {
    systemRepository.removeAll();
  }
}
