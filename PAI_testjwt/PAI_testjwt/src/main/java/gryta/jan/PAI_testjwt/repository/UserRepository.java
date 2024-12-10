package gryta.jan.PAI_testjwt.repository;

import gryta.jan.PAI_testjwt.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}
