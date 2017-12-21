package model.DAO;

import java.util.List;
import model.util.HibernateUtil;
import modelo.entidad.GnrPost;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * Todas las funciones de interación con la base de datos (listar, crear,
 * borrar...) FUNCIONES -> Listar, crear, mostrar por ID, editar y borrar
 */
public class GnrPost_DAO {

    static public List<GnrPost> getAllPosts() {
        List<GnrPost> posts = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM GnrPost ORDER BY postDate DESC";
            Query query = session.createQuery(hql);

            posts = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;

    }

    static public boolean delete(Integer postId) {
        Transaction transaction = null;
        
        try {
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "DELETE FROM GnrPost WHERE postId=:postId";
            Query query = session.createQuery(hql);
            
            query.setInteger("postId", postId);
            query.executeUpdate();
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }

    }
    
    static public GnrPost getBySlug(String post_slug) {
        GnrPost post = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM GnrPost WHERE post_slug=:post_slug";
            Query query = session.createQuery(hql);
            
              query.setString("post_slug", post_slug);

            post = (GnrPost) query.uniqueResult();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return post;

    }

    public static boolean crear(GnrPost post) {
        Transaction transaction = null;
        
        try {
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(post);
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public static GnrPost getById(Integer post_id) { 
        GnrPost post = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM GnrPost WHERE post_id=:post_id";
            Query query = session.createQuery(hql);
            
              query.setInteger("post_id", post_id);

            post = (GnrPost) query.uniqueResult();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return post;
        
    }
}
