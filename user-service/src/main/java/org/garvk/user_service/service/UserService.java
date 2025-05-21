package org.garvk.user_service.service;

import org.garvk.user_service.exception.InvalidCredentialsException;
import org.garvk.user_service.exception.UserNotFoundException;
import org.garvk.user_service.exception.UsernameTakenException;
import org.garvk.user_service.model.User;
import org.garvk.user_service.model.UserCredDTO;
import org.garvk.user_service.model.UserType;
import org.garvk.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo aInUserRepo){
        this.userRepo = aInUserRepo;
    }

    public User createUser(User aInUser) {

        if(aInUser.getUserName() != null){
            User lUserByUsername = userRepo.findByUserName(aInUser.getUserName()).orElse(null);
            if(null != lUserByUsername){
                throw new UsernameTakenException(aInUser.getUserName() + " already taken");
            }
        }

        return userRepo.save(aInUser);
    }

    public User getUserById(Long aInId) {
        User aOutUser = userRepo.findById(aInId).orElse(null);

        if(null == aOutUser){
            throw new UserNotFoundException("User with Id " + aInId + " Not Found");
        }

        return aOutUser;
    }

    public User updateUser(User aInUser) {
        User aOutUser = null;
        Long lId = aInUser.getId();

        // If Id not Present Checking for UserName
        if(null != lId){
            aOutUser = userRepo.findById(lId).orElse(null);
        }

        // If either are present but still user not found
        if(null == aOutUser){
            throw new UserNotFoundException("User with the given Id not found");
        }

        // username present in new data
        if(null != aInUser.getUserName()){
            User lUser = userRepo.findByUserName(aInUser.getUserName()).orElse(null);
            if(null != lUser)
                throw  new UsernameTakenException(
                        "Username " + aInUser.getUserName() + " already Taken");
            aOutUser.setUserName(aInUser.getUserName());
        }

        if(null != aInUser.getUserPassword()) aOutUser.setUserPassword(aInUser.getUserPassword());

        if(null != aInUser.getUserType()) aOutUser.setUserType(aInUser.getUserType());

        return userRepo.save(aOutUser);

    }

    public void deleteUser(Long aInId) {
        User lUser = userRepo.findById(aInId).orElse(null);
        if(null == lUser) throw new UserNotFoundException("User with the given Id not present");
        userRepo.delete(lUser);
    }

    public Boolean validateUser(UserCredDTO aInUserCred) {

        if(null == aInUserCred.getUserName() || null == aInUserCred.getPassword()){
            throw new InvalidCredentialsException();
        }

        User lValidUser = userRepo.findByUserName(aInUserCred.getUserName()).orElse(null);

        if(null == lValidUser) throw new UserNotFoundException("User with the given Username not found");

        return aInUserCred.getUserName().equals(lValidUser.getUserName())
                && aInUserCred.getPassword().equals(lValidUser.getUserPassword());
    }

    public Boolean isUserConsumer(Long aInId) {
        User lUser = userRepo.findById(aInId).orElse(null);

        if(null == lUser) throw new UserNotFoundException("User with given Id not found");

        return lUser.getUserType().equals(UserType.CONSUMER);
    }

    public Boolean isUserOwner(Long aInId) {
        User lUser = userRepo.findById(aInId).orElse(null);

        if(null == lUser) throw new UserNotFoundException("User with given Id not found");

        return lUser.getUserType().equals(UserType.OWNER);
    }
}
