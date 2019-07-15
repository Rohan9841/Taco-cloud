//package tacos.web.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.web.bind.annotation.GetMapping;
//import tacos.Ingredient;
//import tacos.data.IngredientRepository;
//
//@RepositoryRestController
//public class IngredientController {
//
//  private IngredientRepository repo;
//
//  @Autowired
//  public IngredientController(IngredientRepository repo) {
//    this.repo = repo;
//  }
//
//  @GetMapping(path = "ingredients/all", produces="application/hal+json")
//  public Iterable<Ingredient> allIngredients() {
//    return repo.findAll();
//  }
//  
//}