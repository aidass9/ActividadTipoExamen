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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
            System.out.println(posts);
        }
        
        catch (Exception e) {
            e.printStackTrace();

        }
        
        return mv;
    }

}
