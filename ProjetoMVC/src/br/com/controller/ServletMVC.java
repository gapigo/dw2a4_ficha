package br.com.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bo.PessoaBO;

/**
 * Servlet implementation class ServletMVC
 */
@WebServlet("/informacoes")
public class ServletMVC extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMVC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PessoaBO pessoaBo = new PessoaBO();
		
		if(pessoaBo.inserePessoa(request.getParameter("nome"),
				request.getParameter("email")) == "saida.jsp") {
			request.setAttribute("msg", "Sucesso - Objeto Inserido na lista!");
			request.getRequestDispatcher(pessoaBo.inserePessoa(request.getParameter("nome"),
					request.getParameter("email"))).forward(request, response);
			
		} else {
			request.setAttribute("msg", "Ocorreu um erro na inser��o do objeto na lista!");
			request.getRequestDispatcher(pessoaBo.inserePessoa(request.getParameter("nome"),
					request.getParameter("email"))).forward(request, response);
		}
	}

}
