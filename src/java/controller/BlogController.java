/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.GnrPost_DAO;
import modelo.entidad.GnrPost;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 *
 * @author Aida
 */
@Controller
public class BlogController {

    @RequestMapping({"/index.htm", "/"})
    public ModelAndView index() {

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

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public ModelAndView crear(HttpServletRequest request,
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postSlug") String postSlug,
            @RequestParam("postBody") String postBody
    ) {

        ModelAndView mv = new ModelAndView("crearPost");
        mv.addObject("postTitle", postTitle);
        mv.addObject("postSlug", postSlug);
        mv.addObject("postBody", postBody);

        if (StringUtils.isEmpty(postTitle) || StringUtils.isEmpty(postSlug)
                || StringUtils.isEmpty(postBody)) {
            System.out.println(postTitle);
            request.getSession().setAttribute("error", "Debes rellenar todos los campos");
        } else {
            GnrPost post = new GnrPost();
            post.setPostTitle(postTitle);
            post.setPostSlug(postSlug);
            post.setPostBody(postBody);
            post.setPostDate(new Date());
            post.setPostAbstract("Abstract");
            post.setPostVisible("Mostrar");
            post.setPostImage("foto.jpg");

            boolean resultado = GnrPost_DAO.crear(post);

            if (resultado) {
                request.getSession().setAttribute("mensaje", "Post creado correctamente");
                mv = new ModelAndView("redirect:/index.htm");
            } else {
                request.getSession().setAttribute("error", "Error al crear el post");
            }

        }
        return mv;

    }

    @RequestMapping(value = "/formularioCrear")
    public ModelAndView formularioCrear() {
        ModelAndView mv = new ModelAndView("crearPost");

        return mv;

    }

    @RequestMapping(value = "/formularioEditar/{id}")
    public ModelAndView formularioEditar(@PathVariable("id") Integer post_id) {
        ModelAndView mv = new ModelAndView("editarPost");
        GnrPost post = GnrPost_DAO.getById(post_id);
        
        mv.addObject("postTitle", post.getPostTitle());
        mv.addObject("postSlug", post.getPostSlug());
        mv.addObject("postBody", post.getPostBody());

        return mv;

    }
}
