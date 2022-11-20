package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.UserDAOImpl;
import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.services.UserManager;

import java.util.List;
import java.util.Objects;


public class UserManagerImpl implements UserManager {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public List<UserEntity> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public UserEntity findUser(final Integer id) {
        final UserEntity user = (UserEntity) userDAO.findByID(id);
//        if (Objects.isNull(user)) {
//            throw new IllegalStateException(String.format("User with %s id doesn't exist", id));
//        }
        return user;
    }

    @Override
    public List<MobileDeviceEntity> findMobileDevicesByOwner(final Integer ownerId) {
        final UserEntity owner = (UserEntity) userDAO.findByID(ownerId);
        if (Objects.isNull(owner)) {
            throw new IllegalStateException(String.format("User with %s id doesn't exist", ownerId));
        }
        return userDAO.findMobileDevicesByOwnerId(ownerId);
    }

    @Override
    public boolean create(UserEntity user) {
        if (Objects.isNull(user)) {
            throw new IllegalStateException(String.format("User doesn't exist"));
        }
        return userDAO.create(user);
    }

    @Override
    public boolean update(UserEntity user) {
        if (Objects.isNull(user)) {
            throw new IllegalStateException(String.format("User doesn't exist"));
        }
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(final UserEntity user) {
        if (Objects.isNull(user)) {
            throw new IllegalStateException(String.format("User doesn't exist"));
        }
        return userDAO.delete(user.getId());
    }
}
