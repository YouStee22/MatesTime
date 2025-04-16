package com.example.matestime.config;

import com.example.matestime.dao.UserDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration                                          //Tutaj konfiguruje Beany
public class JdbiConfig {

    @Bean                                           //@Bean oznacza, że metoda ta tworzy i zwraca bean, który ma być zarządzany przez Springa.
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public UserDao userDao(Jdbi jdbi) {             //Ta metoda tworzy bean dla UserDao.
        return jdbi.onDemand(UserDao.class);        //jdbi.onDemand(UserDao.class) zwraca instancję UserDao, którą JDBI tworzy dynamicznie w momencie wywołania
    }
}
