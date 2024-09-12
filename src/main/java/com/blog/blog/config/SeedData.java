package com.blog.blog.config;

import com.blog.blog.models.Account;
import com.blog.blog.models.Authority;
import com.blog.blog.models.Post;
import com.blog.blog.repositories.AuthorityRepository;
import com.blog.blog.services.AccountService;
import com.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account acc1 = new Account();
            Account acc2 = new Account();

            acc1.setFirstName("user");
            acc1.setLastName("user");
            acc1.setEmail("user.user@domain.com");
            acc1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            acc1.setAuthorities(authorities1);

            acc2.setFirstName("admin");
            acc2.setLastName("admin");
            acc2.setEmail("admin.admin@gmail.com");
            acc2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            acc2.setAuthorities(authorities2);

            accountService.save(acc1);
            accountService.save(acc2);

            Post post = new Post();
            post.setTitle("title 1");
            post.setBody("body 1");
            post.setAccount(acc1);

            Post post1 = new Post();
            post1.setTitle("title 2");
            post1.setBody("body 2");
            post1.setAccount(acc2);

            postService.save(post);
            postService.save(post1);



        }

    }
}
