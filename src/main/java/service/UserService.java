package service;

import entity.District;
import entity.Game;
import entity.Status;
import entity.Tribute;
import entity.User;

import java.util.Calendar;
import java.util.List;

public interface UserService {


    /**
     * It creates new user
     * @param user user
     * @return new user if he was created
     */
    User createUser(User user);

    /**
     * It deletes user
     * @param user user
     * @return true if it was deleted
     */
    boolean deleteUser(User user);

    /**
     * It updtaes user
     * @param user user
     * @return user if he was updated correctly
     */
    User updateUser(User user);

    /**
     * find user by id
     * @param userId id
     * @return user
     */
    User getUserByUserId(int userId);

    /**
     * Tries to find user by nick
     * @param nick nick
     * @return user if it exists
     */
    User getUserByNick(String nick);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<User> getUsersForGame(District district, boolean sex, Calendar date1, Calendar date2, Status status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    User getUserByTribute(Tribute tribute);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<User> getUsersByStatus(Status status);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<User> getSendersOfPresentsByTribute(Tribute tribute);

    /**
     * update last activity date for getting daily prize
     * @return is successful
     */
    User updateUserLastActivity(User user);

    /**
     * add role to user
     * @param user user
     * @param role name of role
     * @return user
     */
    User addRole(User user, String role);

    /**
     * remove role from user
     * @param user user
     * @param role name of role
     * @return user
     */
    User removeRole(User user, String role);

    /**
     * find steward of game
     * @param game game
     * @return steward
     */
    User getSteward(Game game);

    User getUserByEmail(String email);
}
