/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring1.controllers;


import com.mycompany.spring1.beans.Pracownik;
import com.mycompany.spring1.dao.PracownikDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author student
 */
@Controller
public class PracownikController {
    @Autowired 
    PracownikDao dao; //wstrzyknięcie dao z pliku XML 
    
    @RequestMapping("/addForm") 
    public String showform(Model m){ 
        m.addAttribute("command", new Pracownik()); 
        return "addForm"; //przekiekrowanie do addForm.jsp
    } 
   
    @RequestMapping(value="/save",method = RequestMethod.POST) 
    public String save(@ModelAttribute("pr") Pracownik pr){ 
        dao.save(pr); 
        return "redirect:/viewAll";
        //przekierowanie do endpointa o URL: /viewAll
    } 
    
   /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */ 
    @RequestMapping("/viewAll") 
    public String viewAll(Model m){ 
        List<Pracownik> list=dao.getAll(); 
        m.addAttribute("list",list); 
        return "viewAll"; //przejście do widoku viewAll.jsp 
    }
    @RequestMapping("/delete/{id}")
    public String deletePracownik(@PathVariable int id) {
        dao.deletePracownik(id);
        return "redirect:/viewAll";
    }
    
    @GetMapping("/edit/{id}")
    public String editPracownik(@PathVariable int id, Model m) {
        Pracownik p = dao.getPracownikById(id);
        m.addAttribute("pracownik", p);
        return "editForm";
    }
    
    // Method to handle POST request for saving edited Pracownik
    @PostMapping(value = "edit/editsave")
    public String editSave(@ModelAttribute("pracownik") Pracownik pr) {
        dao.update(pr); 
        return "redirect:/viewAll"; // Redirect to /viewAll
    }
    
    @ExceptionHandler({Exception.class})
    public ModelAndView error(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURI());
        mav.setViewName("errorPage");
        return mav;
    }
}
