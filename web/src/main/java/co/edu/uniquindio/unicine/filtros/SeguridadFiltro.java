package co.edu.uniquindio.unicine.filtros;

import co.edu.uniquindio.unicine.bean.SeguridadBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SeguridadFiltro implements Filter {

    private static final String PAGINA_INICIO_CLIENTE      = "/index.xhtml";
    private static final String PAGINA_INICIO_ADMIN        = "/admin/index_admin.xhtml";
    private static final String PAGINA_INICIO_ADMIN_TEATRO = "/admin_teatro/index_admin_teatro.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();

            //Obtenemos el objeto seguridadBean de la sesión actual
            SeguridadBean userManager = (SeguridadBean) request.getSession().getAttribute("seguridadBean");

            //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("/cliente/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion() == 2) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        if( userManager.getTipoSesion() == 0 )
                            response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN);
                        else if( userManager.getTipoSesion() == 1 )
                            response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN_TEATRO);
                        else
                            if(userManager.getCiudad() == null)
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                            else
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE + "?city=" + userManager.getCiudad().getId());
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                }
            }else if (requestURI.startsWith("/admin/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion() == 0) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        if( userManager.getTipoSesion() == 1 )
                            response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN_TEATRO);
                        else {
                            if(userManager.getCiudad() == null)
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                            else
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE + "?city=" + userManager.getCiudad().getId());
                        }
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                }
            }else if (requestURI.startsWith("/admin_teatro/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion() == 1) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        if( userManager.getTipoSesion() == 0 )
                            response.sendRedirect(request.getContextPath() + PAGINA_INICIO_ADMIN);
                        else {
                            if(userManager.getCiudad() == null)
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                            else
                                response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE + "?city=" + userManager.getCiudad().getId());
                        }
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO_CLIENTE);
                }
            }else{
                //La página solicitada no está en la carpeta /usuario entonces el filtro no aplica
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
