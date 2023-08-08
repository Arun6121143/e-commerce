package com.userservice.userservice.user;

import com.userservice.userservice.address.Address;
import com.userservice.userservice.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AddressService addressService;

    public UserService(UserRepository userRepository,AddressService addressServce) {
        this.userRepository = userRepository;
        this.addressService=addressServce;
    }

    public User saveUser(User user) throws InvalidUserEmailException {
        User existingUser = userRepository.findByUserEmail(user.getUserEmail());
        if(existingUser!=null){
            throw  new InvalidUserEmailException("user with this mail id "+user.getUserEmail()+" already exists");
        }
       User newUser = userRepository.save(user);
       addressService.saveAddress(newUser.getAddress(),newUser.getUserId());
       return newUser;
    }

    public boolean checkPhonenumberExist(String phonenumber) {
        User user = userRepository.findByPhonenumber(phonenumber);
        return user!=null;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) throws InvalidUserEmailException {
        User user = userRepository.findByUserEmail(email);
        if (user == null) {
            throw new InvalidUserEmailException("user with emailId doesnot exist");
        }
        if (!user.getUserEmail().equals(email)) {
            throw new InvalidUserEmailException("invalid emailId please enter correct email Id");
        }
        return user;
    }

    public Address getAddressByUser(UUID userId) throws UserWithUserIdNotFound {
        Address existingAddress = userRepository.findById(userId).get().getAddress();
        if (existingAddress==null){
            throw new UserWithUserIdNotFound("userId doesn't exist");
        }
        return existingAddress;
    }
}