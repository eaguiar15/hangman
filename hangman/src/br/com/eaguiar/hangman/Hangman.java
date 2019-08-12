package br.com.eaguiar.hangman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import cs.utils.PropertiesLoader;

import org.w3c.dom.Element;

public class Hangman {
	
/**
 * @author Aguiar, Emiliano 		
 * @return random word String
 */
	public String getXML() {
		List<String> list = new ArrayList<String>();
		try {
			Properties prop = PropertiesLoader.loadProperties("config.properties");
			File fXmlFile = new File(prop.getProperty("PATHXML"));  
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName("word_list");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				for(int b = 0 ; b < eElement.getElementsByTagName("word").getLength() ; b++) {
					list.add(eElement.getElementsByTagName("word").item(b).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}  catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		int rand = new Random().nextInt(list.size());
		
		return list.get(rand);
		
	}
	
/**
 * @author Aguiar, Emiliano 		
 * @return String with html inputs
 */	
	public String buildInputs(String word) {
		String html = "";
		for (int a = 0; a < word.length() ; a++){
			   html+="<input type=text readonly name=letter" + a + ">";
		}
		return html;
	}
	
	public HttpServletRequest check(HttpServletRequest request){
		int numberOfAttempts = Integer.parseInt(request.getParameter("pAttempts").toString());
		HttpSession session = request.getSession(true);
		String word = (String)session.getAttribute("word");
		String sTry = (String) request.getParameter("pValue").toUpperCase();
		String html = "";
		String wordAnswered = "";
		boolean rightAnswer = false;
		
		for(int a=0 ; a < word.length() ; a++) {
			if(sTry.charAt(0) == word.charAt(a)) { // right answer
				html+="<input type=text readonly name=letter" + a + " value=" + sTry + ">";
				wordAnswered+=sTry;
				rightAnswer = true;
			}else {
				html+="<input type=text readonly name=letter" + a + " value=" + request.getParameter("letter" + a) + ">";
				wordAnswered+=request.getParameter("letter" + a);
			}
		}
		if(!rightAnswer) {
			numberOfAttempts++;
		} 
		if(victory(wordAnswered,word)) {
			request.setAttribute("win", "yes");
		}
		request.setAttribute("attempts", numberOfAttempts);
		request.setAttribute("letters", html);
		
		return request;
	}
	
	public boolean victory(String wordAnswer, String wordRight) {
		if(wordRight.toUpperCase().trim().equals(wordAnswer.toUpperCase().trim())) {
			return true;
		}
		
		return false;
	}
	
	

	

}
