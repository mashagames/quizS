package com.example.quizS;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.*;


@RestController
public class ApiController {
    private final UserRepository userRepository;
    public ApiController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    // 1.   Создать запись
    // curl -s -X POST http://localhost:8080/send_data -d 'username=user&password=pwd123&repeatPassword=pwd123&age=25'
    @PostMapping("/send_data")
    public void send_data(
            @RequestParam("fio") String fio, @RequestParam("email") String email,
            @RequestParam("phone") String phone, @RequestParam("q1") String q1,
            @RequestParam("q2") String q2, @RequestParam("q3") String q3) {
        userRepository.save(new User(fio, email, phone, q1, q2, q3));
        System.out.println(fio);
    }

    @GetMapping("/get_data")
    public List<User> getData() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        System.out.println(userRepository.findAll().stream().count());
        System.out.println(users.stream().count());
        System.out.println(users.get(0).toString());
/*        if (username == null && age == null) {
            if (sortBy == null) {userRepository.findAll().forEach(users::add);}
            else {
                if (direction.equals("down"))
                    users = userRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
                else
                    users = userRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            }
        }
        if (username != null && age == null) userRepository.findByUsername(username).forEach(users::add);
        if (username == null && age != null) {
            for (Integer i = age - 5; i< age+5; i++)
                userRepository.findByAge(i).forEach(users::add);
        }
*/        return users;
    }

    // 5. Возвращает список пользователей
    // curl -s -X GET http://localhost:8080/users
    // 0. Возвращает список пользователей
    // curl -s -X GET http://localhost:8080/users?sortBy=username&direction=down
    // 0. Возвращает пользователя по username
    // curl -s -X GET http://localhost:8080/users?username=user
    // 0. Возвращает список пользователей по age+-5
    // curl -s -X GET http://localhost:8080/users?age=25
/*
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(name = "username", required = false) String username,
                                  @RequestParam(name = "age", required=false) Integer age,
                                  @RequestParam(name = "sortBy", required=false) String sortBy,
                                  @RequestParam(name = "direction", required=false) String direction) {
        List<User> users = new ArrayList<User>();
        if (username == null && age == null) {
            if (sortBy == null) {userRepository.findAll().forEach(users::add);}
            else {
                if (direction.equals("down"))
                    users = userRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
                else
                    users = userRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            }
            }
        if (username != null && age == null) userRepository.findByUsername(username).forEach(users::add);
        if (username == null && age != null) {
            for (Integer i = age - 5; i< age+5; i++)
                userRepository.findByAge(i).forEach(users::add);
        }
        return users;
    }
*/
    // 2. Возвращает пользователя по ID
    // curl -s -X GET http://localhost:8080/users/1

    @GetMapping("/get_data/{id}")
    public User getDataById(@PathVariable("id") long id) {
        Optional<User> userData =
                userRepository.findById(id);
        if (userData.isPresent()) {
            return userData.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(Model model) {

     //   logger.debug("Received request to show all persons");

        // Retrieve all persons by delegating the call to PersonService
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);

 //       List<User> users = userRepository.getAll();

        // Attach persons to the Model
        model.addAttribute("data", users);

        // This will resolve to /WEB-INF/jsp/personspage.jsp
        return "data";
    }

}
