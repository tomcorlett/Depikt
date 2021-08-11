package corlett.depikt.dev.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void createUser(Users user) {
        Optional<Users> userByEmail = usersRepository.findUserByUsername(user.getUsername());
        if (userByEmail.isPresent()) throw new IllegalStateException("Username taken");
        usersRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) throw new IllegalStateException("User with ID " + userId + " does not exist"); 
        usersRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String forename, String email) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with ID " + userId + " dpes not exist"));
        if (forename != null && forename.length() > 0 && !user.getForename().equals(forename)) user.setForname(forename);
        if (email != null && email.length() > 0 && !user.getEmail().equals(email)) user.setEmail(email);
    }

}
