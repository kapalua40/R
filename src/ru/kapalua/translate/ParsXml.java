package ru.kapalua.translate;


import javax.xml.parsers.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import android.util.*;


//класс для парсинга содержимого тегов из XML потока
public class ParsXml
{
	//данные
	//строитель
	private DocumentBuilder db;
	
	static String tag ="log_tag";
	
	//методы
	//конструктор
	public ParsXml() 
	{
		try
		{
			
		db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		}
		catch(Exception e)
		{
			
			Log.d(tag, e.toString());
			
		}
	}
	
	//метод парсинга принимает поток XML и отдает коллекцию содержимого тегов
	public String parse(String uri) 
	{
		try
		{
		
		Document document = db.parse(uri);
		
		Node root = document.getDocumentElement();
		
		
		NodeList list = root.getChildNodes();
		 
	
		String message ="";
		
		for(int i = 0; i < list.getLength(); i++)
		{
			message += list.item(i).getTextContent() + "\n";
		}
		
		return  message;
		
		}
		catch(Exception e)
		{
			
			Log.d(tag, e.toString());
			
			return "Ошибка: " + e.toString();
		}
	}
}
