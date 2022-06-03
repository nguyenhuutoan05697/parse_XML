package toannh.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import toannh.utils.XMLHelper;

/**
 *
 * @author HuuToan
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying");
       ServletContext context = sce.getServletContext();
       String xmlFilePath = context.getInitParameter("STUDENTACCOUNT_XML_PATH");
       String realPath = context.getRealPath("/");
       String xmlFile = realPath + xmlFilePath;
        System.out.println("XML File " + xmlFile);
        //1. Parse DOM
        try{
        Document document = XMLHelper.parseDOMFromFile(xmlFile);
        context.setAttribute("DOMTREE", document);
        //2. Store DOM treses on context Store
        }catch(SAXException ex){
            context.log("SAX " + ex.getMessage());
        }catch(ParserConfigurationException ex){
            context.log("Parse " + ex.getMessage());
        }catch(IOException ex){
            context.log("IO " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }
    
}
