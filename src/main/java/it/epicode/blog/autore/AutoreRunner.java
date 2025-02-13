//package it.epicode.blog.autore;
//
//import com.github.javafaker.Faker;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AutoreRunner implements CommandLineRunner {
//    private final Faker faker;
//    private final AutoreRepository autoreRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            Autore autore = new Autore();
//            autore.setNome(faker.name().firstName());
//            autore.setCognome(faker.name().lastName());
//            autore.setDataDiNascita(faker.date().birthday());
//            autore.setEmail(faker.internet().emailAddress());
//            autore.setAvatar(faker.avatar().image());
//            autoreRepository.save(autore);
//        }
//    }
//}
