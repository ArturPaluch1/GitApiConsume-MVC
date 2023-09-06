package GitConsume.GitConsume.Services;

import GitConsume.GitConsume.Data.GitHubRepository;

import java.util.List;

public interface GitHubRepositoryService {
    List<GitHubRepository> getUserRepositoriesList(String gitHubUser, String acceptHeader);
}
