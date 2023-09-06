package GitConsume.GitConsume.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class GitHubRepository {
    private String ownerLogin;
    private String repositoryName;
    public List<Branch> branches;

    public GitHubRepository(String ownerLogin, String repositoryName) {
        this.ownerLogin = ownerLogin;
        this.repositoryName = repositoryName;
        this.branches = new ArrayList<>();
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}


