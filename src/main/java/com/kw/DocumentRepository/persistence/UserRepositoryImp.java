package com.kw.DocumentRepository.persistence;

import javax.persistence.*;

public class UserRepositoryImp implements UserRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("doc_repo");
    private EntityManager em;

    @Override
    public User getUserById(int id) {
        em = emf.createEntityManager();
        User searchedUser = null;
        String query = "SELECT u FROM User u WHERE u.user_id = " + id;
        TypedQuery<User> typedQuery = em.createQuery(query, User.class);

        try {
            searchedUser = typedQuery.getSingleResult();
        } catch (Exception ex) {
            if( !(ex instanceof EntityNotFoundException)) {
                ex.printStackTrace();
            }
        }

         return searchedUser;
    }

    @Override
    public User getUserByEmail(String email) {
        em = emf.createEntityManager();
        User searchedUser = null;
        String queryString = "SELECT u FROM User u WHERE u.email = '" + email + "'";
        Query query = em.createQuery(queryString, User.class);

        try {
            searchedUser = (User)(query.getResultList().get(0));
        } catch (Exception ex) {
            if( !(ex instanceof EntityNotFoundException)) {
                ex.printStackTrace();
            }
        }
        em.close();
        return searchedUser;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
