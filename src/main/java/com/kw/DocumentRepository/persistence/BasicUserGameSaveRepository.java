package com.kw.DocumentRepository.persistence;

import javax.persistence.*;
import java.util.List;

public class BasicUserGameSaveRepository implements UserGameSaveRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public BasicUserGameSaveRepository() {
        emf = Persistence.createEntityManagerFactory("doc_repo");
    }

    @Override
    public UserGameSave getUserGameSaveByGameSaveId(String gameSaveId) {
        em = emf.createEntityManager();
        EntityTransaction transaction;
        UserGameSave gameSave = null;
        try{
            transaction = em.getTransaction();
            transaction.begin();
            gameSave = em.find(UserGameSave.class, gameSaveId);
        } catch (Exception ex) {
            if(!(ex instanceof NoResultException)) {
                ex.printStackTrace();
            }
        }
        em.close();
        return gameSave;
    }

    @Override
    public UserGameSave getUserGameSave(int userId, String gameSaveName) {
        return null;
    }

    @Override
    public List<UserGameSave> searchUsersGameSaves(int userId, String searchedName) {
        return null;
    }

    @Override
    public List<UserGameSave> getUsersGameSaves(int userId) {
        return null;
    }

    @Override
    public void addUserGameSave(int userId, UserGameSave userGameSave) {

    }

    @Override
    public void deleteUserGameSave(int userGameSaveId) {

    }

    @Override
    public void deleteUserGameSave(UserGameSave userGameSave) {

    }
}
