package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import tacos.Taco;

@Configuration
public class SpringDataRestConfiguration {
	
	/*
	 * ResourceProcessor bean lets us add links to the list of links that Spring
	 * Data REST automatically includes.
	 * HATEOS offers ResourceProcessor that lets us manipulate Resources before they are 
	 * returned throught API.
	 * HATEOS will discover this bean and any other bean of type ResourceProcessor automatically and
	 * will apply them to the appropriate resources. In this case if a PagedResources<Resource<Taco>>
	 * is returned from the controller, it will receive a link for the most recently created tacos. This 
	 * includes the response for requests for api/tacos.
	 */
	@Bean
	public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links){
		return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
			@Override
			public PagedResources<Resource<Taco>> process(PagedResources<Resource<Taco>> resource){
					resource.add(
							links.linkFor(Taco.class)
							.slash("recent")
							.withRel("recents")
							);
					return resource;
			}
					
		};
	}
	
}
