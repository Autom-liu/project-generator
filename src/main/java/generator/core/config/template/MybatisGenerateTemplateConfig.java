package generator.core.config.template;

import generator.core.config.TableColumn;
import lombok.Data;

import java.util.List;

@Data
public class MybatisGenerateTemplateConfig extends BaseTemplateConfig {

    private String tableName;

    private String domainObjectName;

    private boolean generatedKey;

    private String keyColumn;

    private String sqlStatement;

    private boolean identity;

    private List<TableColumn> columns;

}
