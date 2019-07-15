package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.Taco;

/*ResourceAssemblerSupport implements ResourceSupport and makes sure that a self-link is always added.
 * The parameter <Taco, TacoResource> specifies that we convert Taco to TacoResource*/
public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{
	
	/*
	 * informs the super class(ResourceAssemblerSupport) that it will be using
	 * DesignTacoController2 to determine the base path for any URLs in the links it
	 * creates when creating TacoResource
	 */
	public TacoResourceAssembler() {
		super(RecentTacosController.class, TacoResource.class);
	}
	
	/*
	 * This method would be optional if TacoResource had a default constructor.
	 * Instantiates a TacoResource given a Taco
	 */
	@Override
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco);
	}
	
	/*
	 * Mandatory method that creates a TacoResource object from a Taco, and
	 * automatically gives it a self link with the URL being derived from the Taco
	 * object's id property. Under the cover, toResource will call instantiateResource().
	 */	@Override
	public TacoResource toResource(Taco taco) {
		return createResourceWithId(taco.getId(),taco);	
	}

}
