package cco.navigator.controller;

import cco.navigator.dao.App;
import cco.navigator.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppController {

  @Autowired
  AppService appService;

  @GetMapping("{appId}")
  public App getApp(@PathVariable int appId) {
    return appService.getApp(appId);
  }

  @GetMapping
  public List<App> getAllApps() {
    return appService.getAllApps();
  }

  @PostMapping
  public String createApp(@RequestBody App app) {
    if (appService.addApp(app)) return "New App created successfully ";
    else return "adding new App failed !!!";
  }

  @PutMapping("{appId}")
  public String updateApp(@PathVariable int appId, @RequestBody App app) {
    if (appService.updateApp(appId, app)) return "App details Updated successfully";
    else return "update failed!!!";
  }

  @DeleteMapping("/{appId}")
  public String deleteApp(@PathVariable int appId) {
    if (appService.deleteApp(appId)) return "App deleted successfully";
    else return "deleting the app failed !!!";
  }
}
