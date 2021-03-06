package repository;

import entity.Location;
import entity.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    /**
     * find location by id
     * @param id id
     * @return location
     */
    Location findLocationByLocationId(int id);

    /**
     * find location by name
     * @param name name
     * @return location
     */
    Location findLocationByName(String name);

    /**
     * find all locations where product can be applied
     * @param product product
     * @return list of locations
     */
    List<Location> getLocationsByProducts(Shop product);
}
