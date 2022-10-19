package com.example.pokemon.service;

import com.example.pokemon.model.Image;
import com.example.pokemon.repository.ImageRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


  @WebServlet("/viewImage")
  public class GetImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public GetImageServlet() {
      super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

      int imageId = Integer.parseInt(request.getParameter("id"));

      try {
        ImageRepository dao = new ImageRepository();
        Image image = dao.getImageFromDatabase(imageId);

        request.setAttribute("image", image);

        String page = "/index.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);

      } catch (Exception e){
        System.out.println("Could not retrieve image from database");
      }

    }
  }

