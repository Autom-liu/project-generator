package generator.core.config.template;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ParentPomConfig extends BasePomTemplateConfig {

	private List<String> modules;
	
}
