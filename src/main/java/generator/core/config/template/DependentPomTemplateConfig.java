package generator.core.config.template;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DependentPomTemplateConfig extends BasePomTemplateConfig {

	private List<BasePomTemplateConfig> dependents;
	
}
