package cco.navigator.service;

import cco.navigator.dao.App;
import cco.navigator.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {

  @Autowired
  AppRepository appRepository;

  public boolean addApp(App app) {
    appRepository.save(app);
    return true;
  }

  public List<App> getAllApps() {
    List<App> appStore = new ArrayList();
    appRepository.findAll().forEach(appStore::add);
    return appStore;
  }

  public boolean deleteApp(int id) {
    try {
      appRepository.deleteById(id);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public App getApp(int id) {
    return appRepository.findById(id).get();
  }

  public boolean updateApp(int id, App newApp) {
    appRepository.save(newApp);
    return true;
  }
}
