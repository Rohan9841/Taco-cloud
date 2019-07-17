package tacos.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.Taco;
import tacos.data.TacoRepository;

/*sets the basepath of this class to the base path of spring data rest
 * Does not ensure that values returned from handler methods are automatically written to the body
 * of the response. Therefore, you need to either annotate the method with @ResponseBody or return a 
 * responseEntity that wraps the resposnse data.*/
@RepositoryRestController

/* this is a custom endpoint for api/tacos/recent */
public class RecentTacosController {
	
	private TacoRepository tacoRepo;
	
	@Autowired
	public RecentTacosController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	/* handles request for /tacos/recent */
	@GetMapping(path="/tacos/recent",produces="application/hal+json")
	public ResponseEntity<Resources<TacoResource>> recentTacos(){
		PageRequest page = PageRequest.of(0, 2, Sort.by("createdAt").descending());
		
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		
		List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
		
		Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
		
		recentResources.add(
				ControllerLinkBuilder.linkTo(RecentTacosController.class)
									.slash("recent")
									.withRel("recents")
									);
		
		return new ResponseEntity<>(recentResources, HttpStatus.OK);
	}
}
