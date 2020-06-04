package controller;

import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.PresentsToTributeService;
import service.ShopService;
import service.TributeService;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    TributeService tributeService;

    @Autowired
    PresentsToTributeService presentsToTributeService;

    @Secured("ROLE_USER")
    @GetMapping( "/shop")
    public @ResponseBody ResponseEntity getShopList() {
        List<Shop> shopList = shopService.getFullRange();
        return ResponseEntity.status(HttpStatus.OK).body(shopList);
    }

    @Secured("ROLE_USER")
    @GetMapping( "/products_by_types")
    public @ResponseBody ResponseEntity getProductsByType(@RequestParam("type") String type) {
        List<Shop> products = shopService.getProductsByTypeOfPresent(type);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
