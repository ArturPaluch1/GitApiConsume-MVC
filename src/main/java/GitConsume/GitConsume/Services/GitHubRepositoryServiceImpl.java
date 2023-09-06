package GitConsume.GitConsume.Services;


import GitConsume.GitConsume.Data.Branch;
import GitConsume.GitConsume.Data.GitHubRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class GitHubRepositoryServiceImpl implements GitHubRepositoryService {


    @Override
    public List<GitHubRepository> getUserRepositoriesList(String gitHubUser, String acceptHeader) {

        String url = "https://api.github.com/users/" + gitHubUser + "/repos";


        RestTemplate restTemplate = new RestTemplate();


        List<GitHubRepository> userRepositoriesList = new ArrayList<>();


        ResponseEntity<Object> response = null;


        if (acceptHeader.equals("xml")) {

            throw new ResponseStatusException(NOT_ACCEPTABLE, "XML is not acceptable");
        } else {
            if(gitHubUser.equals("")) throw new ResponseStatusException(NOT_FOUND, "User with this username do not exist on GitHub");
            try {
                HttpHeaders headers = new HttpHeaders();

                headers.add("Accept", "application/" + acceptHeader);
                headers.set("Authorization", "token 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");
                HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
                response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);


            } catch (Exception e) {
                throw new ResponseStatusException(NOT_FOUND, "User with this username do not exist on GitHub");
            }


            for (var item : (ArrayList<LinkedHashMap>) response.getBody()
            ) {

                if (item.get("fork").equals(false)) {

                    String urlBranches = "https://api.github.com/repos/" + item.get("full_name") + "/branches";

                    HttpHeaders headers1 = new HttpHeaders();
                    headers1.set("Authorization", "token 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");
                    headers1.add("Accept", "application/" + acceptHeader);
                    HttpEntity<String> entity1 = new HttpEntity<String>("parameters", headers1);
                    ResponseEntity<Object> response1 = restTemplate.exchange(urlBranches, HttpMethod.GET, entity1, Object.class);


                    var branches = response1.getBody();

                    GitHubRepository gitHubRepository = new GitHubRepository(gitHubUser, (String) item.get("name"));

                    for (var branchItem : (ArrayList) branches
                    ) {
                        LinkedHashMap branch = (LinkedHashMap) branchItem;
                        LinkedHashMap commit = (LinkedHashMap) branch.get("commit");
                        commit.get("sha");

                        var newBranch = new Branch(branch.get("name").toString(), commit.get("sha").toString());
                        gitHubRepository.getBranches().add(newBranch);
                    }
                    userRepositoriesList.add(gitHubRepository);

                }
            }


        }
        return userRepositoriesList;
    }
}
