package solutions.thex.badgify.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("gitlab-profile-view")
@Builder
@Data
public class GitLabProfileView {

    @Id
    private String id;
    @Indexed(unique=true)
    private String profile;
    private long count;

}
