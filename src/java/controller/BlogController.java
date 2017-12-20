/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.GnrPost_DAO;
import modelo.entidad.GnrPost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Aida
 */
@Controller
public class BlogController {

    @RequestMapping({"/index.htm", "/"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("index");
        try {
            List<GnrPost> posts = GnrPost_DAO.getAllPosts();
            mv.addObject("posts", posts);
            
        } catch (Exception e) {
            e.printStackTrace();

        }

        return mv;
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(HttpServletRequest request, @PathVariable("id") Integer idPost) {

        ModelAndView mv = new ModelAndView("index");

        try {
            boolean resultado = GnrPost_DAO.delete(idPost);

            if (resultado) {
                request.getSession().setAttribute("mensaje", "Borrado con exito");
                //mv.addObject("error", "Borrado correctamente");
            } else {
                request.getSession().setAttribute("error", "Fallo al borrar");
                
                //mv.addObject("error", "Error al borrar");
            }
            
            //mv.addObject("posts", posts);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "redirect:/index.htm";
    }
    
    
    @RequestMapping(value = "/{slug}")
    public ModelAndView detalle(@PathVariable("slug") String post_slug) {
        System.out.println("DETALLE" + post_slug);
        ModelAndView mv = new ModelAndView("BlogDetalle");
        
        try {
            GnrPost post = GnrPost_DAO.getBySlug(post_slug);
            //System.out.println("HOLII" + post.toString());
            mv.addObject("post", post);
            
            //mv.addObject("posts", posts);
        } catch (Exception e) {
            e.printStackTrace();

        }
        
        return mv;

        
    }

}
