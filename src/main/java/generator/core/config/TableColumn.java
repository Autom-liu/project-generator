package generator.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TableColumn {

    private String column;

    private String originColumn;

    private String dataType;

    private String characterMaximnmLength;

    private String comment;

    private Boolean isPrimary;

    private String javaField;

    private String javaType;

    private String jdbcType;

    private Boolean isNullable;

    public TableColumn(String column, String dataType, String characterMaximnmLength, String comment, Boolean isPrimary) {
        this.column = column;
        this.dataType = dataType;
        this.characterMaximnmLength = characterMaximnmLength;
        this.comment = comment;
        this.isPrimary = isPrimary;
    }
}
