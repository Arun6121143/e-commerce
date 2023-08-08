package com.userservice.userservice.user;

import com.userservice.userservice.Orders;
import com.userservice.userservice.Product;
import com.userservice.userservice.UserProducts;
import com.userservice.userservice.address.Address;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final  UserService userService;

    private final RestTemplate restTemplate;


    public UserController(UserService userService,RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate=restTemplate;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody @Valid User user) throws InvalidUserEmailException {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>("user with userId "+ savedUser.getUserId()+" saved successfully", HttpStatus.OK);
    }

    //only accessible by admin
    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers(){
       return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    //only accessible to logined user only for their email id
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws InvalidUserEmailException {
        return new ResponseEntity<>(userService.findUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/getUserAddress/{userId}")
    public ResponseEntity<Address> getUserAddressByUserId(@PathVariable UUID userId) throws UserWithUserIdNotFound {
        return new ResponseEntity<>(userService.getAddressByUser(userId),HttpStatus.OK);
    }

//    // only for user access
//    @GetMapping("/getProducts")
//    public ResponseEntity<?>getAllProducts(){
//     UserProducts product = restTemplate.getForObject("http://localhost:8082/products/getProductsUsers",UserProducts.class);
//     return new ResponseEntity<>(product.getAllProducts(),HttpStatus.OK);
//    }

    @GetMapping("/orderProducts/{productId}/{userId}")
    public ResponseEntity<?> orderProducts(@PathVariable UUID productId,@PathVariable UUID userId){
       Product product = restTemplate.getForObject("http://product-service/products/"+productId, Product.class);
       Orders order =  restTemplate.getForObject("http://order-service/orders/orderProducts/"+productId+"/"+userId, Orders.class);
       return new ResponseEntity<>("Your order is success your oderId is "+order.getOrderId(),HttpStatus.OK);
    }

    @GetMapping("/userOrders/{userId}")
    public ResponseEntity<?> getUserOrderedProducts(@PathVariable UUID userId){
       UserProducts products =  restTemplate.getForObject("http://order-service/orders/userProducts/"+userId, UserProducts.class);
       return new ResponseEntity<>(products.getAllProducts(),HttpStatus.OK);
    }

}
