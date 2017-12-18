package model.DAO;

import java.util.List;
import model.util.HibernateUtil;
import modelo.entidad.GnrPost;
import org.hibernate.Query;
import org.hibernate.Session;

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
            String hql = "FROM GnrPost";
            Query query = session.createQuery(hql);
            
            posts = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;

    }
}
