package com.kw.DocumentRepository.persistence;


import java.util.List;

public interface UserGameSaveRepository {

    UserGameSave getUserGameSaveByGameSaveId(String userId);

    UserGameSave getUserGameSave(int userId, String gameSaveName);

    List<UserGameSave> searchUsersGameSaves(int userId, String searchedName);

    List<UserGameSave> getUsersGameSaves(int userId);

    void addUserGameSave(int userId, UserGameSave userGameSave);

    void deleteUserGameSave(int userGameSaveId);

    void deleteUserGameSave(UserGameSave userGameSave);
}
