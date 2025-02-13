//package it.epicode.blog.posts;
//
//import com.github.javafaker.Faker;
//import it.epicode.blog.autore.AutoreRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class PostRunner implements CommandLineRunner {
//
//    private final PostRepository postRepository;
//    private final Faker faker;
//    private final AutoreRepository autoreRepository;
//    private final PostService postService;
//    @Override
//    public void run(String...args) throws Exception {
//
//        for (int i = 0; i < 30; i++) {
//            Post post = new Post();
//            post.setCategoria(faker.lorem().word());
//            post.setTitolo(faker.lorem().sentence());
//            post.setCover(faker.internet().url());
//            post.setContenuto(faker.lorem().sentence());
//            post.setTempoDiLettura(faker.number().numberBetween(1, 100));
//            post.setAutore(autoreRepository.findById(1L).get());
//            postRepository.save(post);
//        }
//
//    }
//}
