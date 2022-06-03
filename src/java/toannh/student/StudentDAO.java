/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import toannh.utils.XMLHelper;

/**
 *
 * @author HuuToan
 */
public class StudentDAO {

    public StudentDTO checkLogin(String username, String password, Document document) throws XPathExpressionException {
        // ACCESS DOM TREE
        //1. Assume that DOM TREE had existed
        //2. Create xpath expression
        String expression = "//student[@id='"
                + username
                + "' and normalize-space(password)='"
                + password
                + "' and normalize-space(status)!='dropout']";
        //3. Create xpath object
        XPath xpath = XMLHelper.getXPath();
        //4. Evaluated
        Node result = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);

        if (result != null) {
            //set value to DTO
            expression = "lastname";
            String lastname = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "middlename";
            String middlename = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "firstname";
            String firstname = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "@class";
            String sClass = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "address";
            String address = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "sex";
            String sex = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            expression = "status";
            String status = (String) xpath.evaluate(expression, result, XPathConstants.STRING);

            String fullName = lastname.trim() + " " + middlename.trim() + " " + firstname.trim();
            StudentDTO dto = new StudentDTO(username, sClass, fullName, address, sex, status);
//                StudentDTO dto = new StudentDTO(username, sClass, fullName, address, sex, status);
            return dto;
        }
        return null;
    }
    private List<StudentDTO> students;

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void searchAddress(String searchValue, Document document) throws XPathExpressionException {
        String expression = "//student[contains(address,'"
                + searchValue
                + "')]";
        XPath xpath = XMLHelper.getXPath();
        NodeList studentList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
        for (int i = 0; i < studentList.getLength(); i++) {
            Node student = studentList.item(i);

            expression = "@id";
            String id = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "@class";
            String sClass = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "lastname";
            String lastname = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "middlename";
            String middlename = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "firstname";
            String firstname = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "address";
            String address = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "sex";
            String sex = (String) xpath.evaluate(expression, student, XPathConstants.STRING);

            expression = "status";
            String status = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
            String fullName = lastname.trim() + " " + middlename.trim() + " " + firstname.trim();

            StudentDTO dto = new StudentDTO(id, sClass, fullName, address, sex, status);
            if (this.students == null) {
                this.students = new ArrayList<>();
            }
            this.students.add(dto);
        }
    }

    public boolean deleteStudent(String id, Document document, String xmlFile) throws XPathExpressionException, TransformerException {
        boolean result = false;
        if (document != null) {
            //1. Find student
            String expression = "//student[@id='"
                    + id
                    + "']";
            XPath xpath = XMLHelper.getXPath();
            Node student = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
            //2. Find Parent
            if (student != null) {
                Node students = student.getParentNode();
                if (students != null) {
                    //3. Remove Child
                    students.removeChild(student);
                    //4. Transformer
                    XMLHelper.transformDOMtoFile(document, xmlFile);
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean updateStudent(StudentDTO dto, Document document, String xmlFile) throws XPathExpressionException, TransformerException {
        boolean result = false;
        String[] a = dto.getFullName().split(" ");
        String lastname = a[0];
        String middlename = a[1];
        String firstname = a[2];
        if (document != null) {
            String expression = "//student[@id='"
                    + dto.getId()
                    + "']";
            XPath xpath = XMLHelper.getXPath();
            Node student = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
            boolean flag;
            if (student != null) {
                NodeList students = student.getChildNodes();
                student.getAttributes().getNamedItem("class").setNodeValue(dto.getsClass());
                for (int i = 0; i < students.getLength(); i++) {
                    if (students.item(i).getNodeName().equals("lastname")) {
                        students.item(i).setTextContent(lastname);
                    }
                    if (students.item(i).getNodeName().equals("middlename")) {
                        students.item(i).setTextContent(middlename);
                    }
                    if (students.item(i).getNodeName().equals("firstname")) {
                        students.item(i).setTextContent(firstname);
                    }
                    if (students.item(i).getNodeName().equals("address")) {
                        students.item(i).setTextContent(dto.getAddress());
                    }
                }
                XMLHelper.transformDOMtoFile(document, xmlFile);
                result = true;
            }
        }
        return result;
    }
    public boolean createStudent(StudentDTO dto, Document document, String xmlFile) throws TransformerException{
        boolean result = false;
        if(dto == null){
           return  false;
        }
        if(document != null){
            //1. Make Nodes - Elements (Body - Text, Attribute)
            //a. create student (attribute: id, class; no body text)
            Map<String, String> attributes = new HashMap<>();
            attributes.put("id", dto.getId());
            attributes.put("class", dto.getsClass());
            Element student = XMLHelper.createElement("student", null, attributes, document);
            //b. Create lastName(attr: null, body text
            Element lastname = XMLHelper.createElement("lastname", dto.getLastName(), null, document);
            //c. Create middleName(attr: null, body text
            Element middlename = XMLHelper.createElement("middlename", dto.getMiddleName(), null, document);
            //d. Create firstName(attr: null, body text
            Element firstname = XMLHelper.createElement("firstname", dto.getFirstName(), null, document);
            //e. Create lastName(attr: null, body text
            Element sex = XMLHelper.createElement("sex", dto.getSex(), null, document);
            //f. Create lastName(attr: null, body text
            Element password = XMLHelper.createElement("password", dto.getPassword(), null, document);
            //g. Create lastName(attr: null, body text
            Element address = XMLHelper.createElement("address", dto.getAddress(), null, document);
            //h. Create lastName(attr: null, body text
            Element status = XMLHelper.createElement("status", dto.getStatus(), null, document);
            //2. Make Element Relationship - appendChild
            student.appendChild(lastname);
            student.appendChild(middlename);
            student.appendChild(firstname);
            student.appendChild(sex);
            student.appendChild(password);
            student.appendChild(address);
            student.appendChild(status);           
            //3. Find attached Node - using XPath or DOM API methods
            NodeList students = document.getElementsByTagName("students");
            students.item(0).appendChild(student); //root elemen is only 1 so we .item(0)
            //4. Transformer
            XMLHelper.transformDOMtoFile(document, xmlFile);
            result = true;           
        }//if document has existed      
        return result;
    }
}
