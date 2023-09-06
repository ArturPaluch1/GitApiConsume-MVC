package GitConsume.GitConsume.Controllers;

import GitConsume.GitConsume.Services.GitHubRepositoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GitConsumeController {


    private final GitHubRepositoryServiceImpl gitHubRepositoryService;

    public GitConsumeController(GitHubRepositoryServiceImpl gitHubRepositoryService) {
        this.gitHubRepositoryService = gitHubRepositoryService;
    }

    @GetMapping("/")
    public String tempIndex(Model model) {


        String userName = "";
        model.addAttribute("gitHubUser", userName);


        return "index";
    }


    @PostMapping("/getUserRepository")
    public Object getUserRepository(@RequestParam(value = "gitHubUser", required = false) String gitHubUser, @RequestParam(value = "Accept", required = false) String accept, Model model) {


        var userRepos = gitHubRepositoryService.getUserRepositoriesList(gitHubUser, accept);

        model.addAttribute("repos", userRepos);


        return "gitUserRepositories";


    }


}
