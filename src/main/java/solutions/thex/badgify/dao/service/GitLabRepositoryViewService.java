package solutions.thex.badgify.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solutions.thex.badgify.dao.GitLabRepositoryView;
import solutions.thex.badgify.dao.repository.GitLabRepositoryViewRepository;

import java.util.Optional;

@Service
public class GitLabRepositoryViewService {

    private final GitLabRepositoryViewRepository repositoryViewRepository;

    @Autowired
    public GitLabRepositoryViewService(GitLabRepositoryViewRepository repositoryViewRepository) {
        this.repositoryViewRepository = repositoryViewRepository;
    }

    public GitLabRepositoryView save(GitLabRepositoryView repositoryView) {
        return repositoryViewRepository.save(repositoryView);
    }

    public Optional<GitLabRepositoryView> getByProfileAndRepo(String profile, String repo) {
        return repositoryViewRepository.findByProfileAndRepo(profile, repo);
    }

}
