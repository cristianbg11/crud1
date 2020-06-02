import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import student.Estudiante;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //public static ArrayList estudiantes=new ArrayList<Estudiante>();
    public static void main(final String[] args) throws Exception {
        //final Session session = getSession();
        final Session secion = getSession();
        EntityManager em = getSession();

        port(getHerokuAssignedPort());
        //port(8080);
        //freemarker.template.Configuration config = new Configuration();
        //config.setClassForTemplateLoading(this.getClass(), "/templates/");
        staticFiles.location("/publico");

        get("/", (request, response)-> {
            // return renderContent("publico/index.html");
            Map<String, Object> attributes = new HashMap<>();
            List<Estudiante> estudiantes = em.createQuery("select e from Estudiante e", Estudiante.class).getResultList();
            attributes.put("listado",estudiantes);
            return new ModelAndView(attributes, "index.ftl");

        } , new FreeMarkerEngine());

        get("/view", (request, response)-> {
            Map<String, Object> attributes = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            //Estudiante est = (Estudiante) estudiantes.get(id);
            Estudiante estudiante = secion.find(Estudiante.class, id);
            attributes.put("vista",estudiante);
            return new ModelAndView(attributes, "view.ftl");

        } , new FreeMarkerEngine());

        get("/edit", (request, response)-> {
            Map<String, Object> attributes = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            Estudiante estudiante = secion.find(Estudiante.class, id);
            attributes.put("edicion",estudiante);
            attributes.put("id",id);
            return new ModelAndView(attributes, "edit.ftl");

        } , new FreeMarkerEngine());

        get("/delete", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            final Session sesion = getSession();
            Estudiante estudiante = sesion.find(Estudiante.class, id);

            sesion.getTransaction().begin();
            sesion.remove(estudiante);
            sesion.getTransaction().commit();
            //estudiantes.remove(id);
            response.redirect("/");
            return "Estudiante Eliminado";
        });

        post("/insertar", (request, response) -> {
            em.getTransaction().begin();
            Estudiante est=new Estudiante();
            est.matricula = Integer.parseInt(request.queryParams("matricula"));
            est.nombre=request.queryParams("nombre");
            est.apellido = request.queryParams("apellido");
            est.telefono = request.queryParams("telefono");
            em.persist(est);
            em.getTransaction().commit();
            //estudiantes.add(est);
            response.redirect("/");
            return "Estudiante Creado";
        });

        post("/actualizar", (request, response) -> {
            final Session sesion = getSession();
            int id= Integer.parseInt(request.queryParams("id"));
            Estudiante est = sesion.find(Estudiante.class, id);
            em.getTransaction().begin();
            est.matricula = Integer.parseInt(request.queryParams("matricula"));
            est.nombre=request.queryParams("nombre");
            est.apellido = request.queryParams("apellido");
            est.telefono = request.queryParams("telefono");
            response.redirect("/");
            return 0;
        });
    }
    private static String renderContent(String htmlFile) throws IOException, URISyntaxException {
        URL url = Main.class.getResource(htmlFile);
        Path path = Paths.get(url.toURI());
        return new String(Files.readAllBytes(path), Charset.defaultCharset());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }
}