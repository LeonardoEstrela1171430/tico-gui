package isep.ipp.pt.tico;

import IDC.EvolActions.Impl.Additions.AddClass;
import IDC.EvolActions.Impl.Additions.AddDatatypeProperty;
import IDC.EvolActions.Impl.Additions.AddObjectProperty;
import IDC.EvolActions.Impl.Additions.AddProperty;
import IDC.EvolActions.Impl.Additions.Restriction.AddAllValuesFromRestriction;
import IDC.EvolActions.Impl.Additions.Restriction.AddCardinalityRestriction;
import IDC.EvolActions.Impl.Additions.Restriction.AddRestriction;
import IDC.EvolActions.Impl.Additions.Restriction.AddSomeValuesFromRestriction;
import IDC.EvolActions.Impl.Copy.CopyClass;
import IDC.EvolActions.Impl.Copy.CopyDatatypeProperty;
import IDC.EvolActions.Impl.Copy.CopyObjectProperty;
import IDC.EvolActions.Impl.Copy.CopyProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class TicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicoApplication.class, args);
	}

/*	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(
						AddClass.class,
						AddCardinalityRestriction.class,
						AddRestriction.class,
						AddSomeValuesFromRestriction.class,
						AddAllValuesFromRestriction.class,
						AddDatatypeProperty.class,
						AddObjectProperty.class,
						AddProperty.class,
						CopyClass.class,
						CopyDatatypeProperty.class,
						CopyObjectProperty.class,
						CopyProperty.class);

				super.configure(objectMapper);
			};
		};
		return builder;
	}*/
}
