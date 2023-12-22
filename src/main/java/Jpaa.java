import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Jpaa {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();



        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author author1 = new Author("Стругацкие");
            Author author2 = new Author("Филип Дик");
            Author author3 = new Author("Юкио Мисима");
            Author author4 = new Author("Джоан Роулинг");
            Author author5 = new Author("Толстой");
            Author author6 = new Author("Достоевский");
            Author author7 = new Author("Тургенев");
            Author author8 = new Author("Чехов");

            Book book1 = new Book("Понедельник начинается в субботу", author1);
            Book book2 = new Book("Трудно быть богом", author1);
            Book book3 = new Book("Пикник на обочине", author1);
            Book book4 = new Book("Убик", author2);
            Book book5 = new Book("Исповедь маски", author3);
            Book book6 = new Book("Гарри Поттер и Принц Полукровка", author4);
            Book book7 = new Book("Война и мир", author5);
            Book book8 = new Book("Преступление и наказание", author6);
            Book book9 = new Book("Три сестры", author8);
            Book book10 = new Book("Муму", author7);
            Book book11 = new Book("Идиот", author6);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.persist(author4);
            session.persist(author5);
            session.persist(author6);
            session.persist(author7);
            session.persist(author8);

            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(book4);
            session.persist(book5);
            session.persist(book6);
            session.persist(book7);
            session.persist(book8);
            session.persist(book9);
            session.persist(book10);
            session.persist(book11);
            session.getTransaction().commit();
        }

        try(Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select b from Book b", Book.class).getResultList();
            books.forEach(System.out::println);
        }
        sessionFactory.close();
    }
}
