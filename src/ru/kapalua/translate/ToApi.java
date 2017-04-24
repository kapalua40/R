package ru.kapalua.translate;
import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import android.util.*;


//класс для обращения к серверу с API
public class ToApi
{
	
	
	//данные 
	//адрес сервера для взаимодействия
	private static String address = "https://translate.yandex.net/api/v1.5/tr/";
	
	//ключ для взаимодействия
	private static String key = "trnsl.1.1.20170407T044603Z.12d1b3848c55d40a.5f63821dc91b40a0f1c8e40bd21111915656a2ba";
	
	private static String hint = "ru,en";
	
	static String tag = "log_tag";
	
	
	//методы
	//метод запроса к серверу для перевода текста с языка lang1 на lang2
	public static String translate(String text, String lang1, String lang2) 
	{
		try
		{
			String url = address +  "translate?key=" + key + "&amp;text=" + encode(text) + "&amp;lang=" + lang1 + "-" + lang2;
		
		return url;
		}
		catch(Exception e)
		{
			Log.d(tag, e.toString());
			
			return "Ошибка: " + e.toString();
		}
	}
	
	
	//метод урл кодирования
	private static String encode(String text) throws UnsupportedEncodingException
	{
		return URLEncoder.encode(text, "UTF-8");
	}
	
	
	//определение языка по тексту
	public static String detect(String text)
	{
		try
		{
			
			String url = address + "detect?key=" + key + "&amp;text=" + encode(text) + "&amp;hint=" + hint;
			
			
			return url;
		}
		catch(Exception e)
		{
			Log.d(tag, e.toString());
			
			return "Ошибка: " + e.toString();
		}
	}
	
	
	//определение возможности направления перевода
	public static String getLangs(String lang)
	{
		
		try
		{
			String url = address + "getLangs?key=" + key + "&amp;ui=" + lang;
			
			
			return url;
		}
		catch(Exception e)
		{
			Log.d(tag, e.toString());
			
			return "Ошибка: " + e.toString();
		}
	}
	
	
	//перевод текста на язык lang
	public static String translate(String text, String lang) 
	{
		try
		{
			String url = address +  "translate?key=" + key + "&amp;text=" + encode(text) + "&amp;lang=" + lang;

			return url;
		}
		catch(Exception e)
		{
			Log.d(tag, e.toString());
			return "Ошибка: " + e.toString();
		}
	}
	
}
