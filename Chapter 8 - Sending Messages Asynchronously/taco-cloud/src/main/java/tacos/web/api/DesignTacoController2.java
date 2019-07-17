//package tacos.web.api;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.Resources;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import tacos.Taco;
//import tacos.data.TacoRepository;
//
///*Stereotype annotation for component scanning which tells that all handler 
// * methods (get, post) should have their return value written directly to 
// * the body of the reponse, rather than being carried in the model to the view for rendering*/
//@RestController
//
///*
// * produces specifies that the handler methods in this class will only handle
// * requests if the request's Accept header includes "application/json" */
//@RequestMapping(path = "/design", produces = "application/json")
//
///*
// * Because the angular or other client portion will be running in separate host,
// * the web browser will prevent the client from consuming this API. CrossOrigin
// * allows clients from any domain to consume API
// */
//@CrossOrigin(origins = "*")
//
///* This is a rest api */
//public class DesignTacoController2 {
//	
//	private TacoRepository tacoRepo;
//	
//	@Autowired
//	public DesignTacoController2(TacoRepository tacoRepo) {
//		this.tacoRepo = tacoRepo;
//	}
//	
//	/* handles the request for /design/recent 
//	 */
//	@GetMapping("/recent")
//	public Resources<TacoResource> recentTacos(){
//		/* PageRequest object specifies that the page will have only 5 tacos in each page.
//		 * The API will display the 0th page containg 5 tacos at max sorted in desceding
//		 * order by creation date
//		 */
//		PageRequest page = PageRequest.of(0,5, Sort.by("createdAt").descending());
//		
//		/* getting the list of tacos from the database and storing in tacos variable */
//		List<Taco> tacos = tacoRepo.findAll(page).getContent();
//		
//		/*
//		 * Passes the list of tacos to toResources() method in TacoResourceAssembler which 
//		 * cycles through all the Taco objects, calling the toResource() method to create a 
//		 * list of TacoResource objects
//		 */
//		List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//		
//		/* Creates a Resources<TacoResource> object which has a list of tacoResources */
//		Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
//		
//		/* Add a self link to this method i.e when /design/recent is requested.
//		 * First asks for a link to DesignTacoController2, whose base path is /design
//		 * Uses the controller's basepath as the foundation of the Link object
//		 * slash literally works like '/' so the url's path becomes /design/recent
//		 * Finally you specify relation name to the link which is 'recents' */
//		recentResources.add(
//				ControllerLinkBuilder.linkTo(DesignTacoController2.class)
//									.slash("recent")
//									.withRel("recents")
//			);
//		
//		return recentResources;
//	}
//	
//	/* handles request for design/id 
//	 * The id in the method parameter gets its value from {id} placeholder which is connected by @PathVariable
//	 * findById(id) gives optional taco because that taco may or may not exists.
//	 * If it exists wrap the taco by ReponseEntity with a status code of Ok.*/
//	@GetMapping("/{id}")
//	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
//		Optional<Taco> optTaco = tacoRepo.findById(id);
//		if(optTaco.isPresent()) {
//			return new ResponseEntity<>(optTaco.get(),HttpStatus.OK);
//		}
//		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//	}
//	
//	/* method to save the taco which is designed by the clients
//	 * Since no path is declared, this will handle request for /design
//	 * The consumes attribute is used to say that the method will only handle
//	 * requests whose Content-type mathches application/json*/
//	@PostMapping(consumes = "application/json")
//	
//	/*
//	 * @ResponseStatus tells the client that the request was succesful and taco was
//	 * created.
//	 */
//	@ResponseStatus(HttpStatus.CREATED)
//	
//	/*
//	 * @RequestBody is used to indicate that JSON in the body of the request should
//	 * be converted to Taco object and bound to the taco parameter. Without this
//	 * annotation, Spring MVC assumes that you want request parameters to be bound
//	 * to the Taco object.
//	 */
//	public Taco postTaco(@RequestBody Taco taco) {
//		return tacoRepo.save(taco);
//	}
//}
