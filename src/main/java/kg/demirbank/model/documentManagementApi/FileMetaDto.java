package kg.demirbank.model.documentManagementApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class FileMetaDto {
    private String customerNo;
    private String nameOfSpec;
    private String fileTag;
    private String name;
    private String fileId;
}
