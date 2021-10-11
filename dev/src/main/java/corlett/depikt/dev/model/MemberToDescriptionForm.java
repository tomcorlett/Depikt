package corlett.depikt.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberToDescriptionForm {
    private Long memberId;
    private Long descriptionId;
}
