package corlett.depikt.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleToMemberForm {
    private Long memberId;
    private String roleName;
}
