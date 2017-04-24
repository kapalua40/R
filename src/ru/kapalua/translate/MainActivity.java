package ru.kapalua.translate;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import android.content.pm.*;
import android.util.*;
import org.apache.commons.logging.*;
import android.util.Log;
import android.content.*;
import android.view.KeyEvent;
import android.text.*;

public class MainActivity extends Activity 
{

	//данные
	//спиннеры
	private Spinner spinner1;
	
	private Spinner spinner2;
	
	 private TextView textView;
	
	 private EditText editText;
	
	 private CheckBox checkBox;
	 
	 ParsXml xml = new ParsXml();
	
	
	
	TextWatcher inputTW;
	
	
	
	
	String tag = "log_tag";
	
	
	//методы
    //Создание 
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		sp();
		
		getActionBar().hide();
		
		spinner1.setSelection(60);
		
		spinner2.setSelection(3);
	
		
		
		Log.d(tag, "end onCreate()");
		
		
    }
	
	
	
	//клик по кнопке реверса языков
	public void btnClick(View v)
	{
		Log.d(tag, "start btnClick()");
		
		
		int i = spinner1.getSelectedItemPosition();
		
		spinner1.setSelection(spinner2.getSelectedItemPosition());
		
		spinner2.setSelection(i);
		
		
		
	}
	
	
	
	
	
	//привязка xml элеменов к переменным
	private void sp()
	{
		
		Log.d(tag, "start sp()");

	spinner1 = (Spinner)findViewById(R.id.spinner1);

	spinner2 = (Spinner)findViewById(R.id.spinner2);


	
	textView = (TextView)findViewById(R.id.text);


	editText = (EditText)findViewById(R.id.edit);
	

	
	
	inputTW = new TextWatcher()
		{

			@Override
			public void afterTextChanged(Editable p1)
			{
				// TODO: Implement this method
                 if(editText.getText().toString().length() > 0)
				 { 
				 
				 enter(editText);
				 
				 }
				 else
				 {
					 textView.setText("");
				 }
			}

			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
				// TODO: Implement this method
			}

			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
				// TODO: Implement this method
			}

		};
		
		
	
	checkBox = (CheckBox)findViewById(R.id.checkBox);
	
	
	editText.addTextChangedListener(inputTW);
	
	
		
	}        
	
	
	//ввод в EditText
	public void enter(View v)
	{
		Log.d(tag, "start enter();");

		final String  text = editText.getText().toString();

		final String lang1 = langs(spinner1.getSelectedItemPosition());

		final String lang2 = langs(spinner2.getSelectedItemPosition());
		
		 
		new Thread(new Runnable() 
		{
			public void run() 
			{
				
				
				
			   
			   if(checkBox.isChecked())
			   {
				   
			   
				final String document =  xml.parse(ToApi.translate(text, lang2 ));
				
				
				
				textView.post(new Runnable() 
				{ 
				public void run()
				 {
					 
					//код для доступа к компонентам форм
					textView.setText(document + "\n");
					
					
					
				 }
				});
				
				}
				else
				{
					final String document =  xml.parse(ToApi.translate(text, lang1 , lang2));


					textView.post(new Runnable() 
						{ 
							public void run()
							{

								//код для доступа к компонентам форм
								textView.setText(document + "\n");
								
								
								
							}
						});
				}
				
			}
		}).start();
		
		Log.d(tag, "end enter();");
	}
	
	
	
	
	
	//нажатие на кнопку очистки
	public void clear(View v)
	{
		
		editText.setText("");
		
		textView.setText("");
		
	}
	
	
	//определение кода языка по позиции в спиннере
	private static String langs(int position)
	{
		
		String[] lan = 
		{  
		
		"az" , 	
		"sq" , 	
		"am" , 	
		"en" , 	
		"ar" , 	
		"hy" , 	
		"af" , 	
		"eu" , 	
		"ba" , 	
		"be" , 	
		"bn" , 	
		"bg" , 	
		"bs" , 	
		"cy" , 	
		"hu" , 	
		"vi" , 	
		"ht" , 	
		"gl" , 	
		"nl" , 	
		"mrj", 
		"el" , 	
		"ka" , 
		"gu" , 	
		"da" , 	
		"he" , 	
		"yi" , 	
		"id" , 
		"ga" , 	
		"it" , 	
		"is" , 	
		"es" , 
		"kk" , 	
		"kn" , 	
		"ca", 	
		"ky", 	
		"zh", 	
		"ko", 	
		"xh", 
		"la",  
		"lv", 
		"lt",  
		"lb",  
		"mg",  
		"ms",  
		"ml", 
		"mt", 
	 	"mk", 
		"mi", 
		"mr", 
		"mhr", 
		"mn", 
	 	"de", 
		"ne",
		"no",
		"pa",
		"pap", 
		"fa",
		"pl",
	 	"pt",
		"ro", 
		"ru", 
		"ceb", 
		"sr", 
		"si", 
	    "sk", 
		"sl", 
	 	"sw", 
		"su",
		"tg",
		"th", 
		"tl", 
		"ta", 
		"tt", 
		"te", 
		"tr", 
		"udm", 
		"uz", 
		"uk",
		"ur",
		"fi",
		"fr",
		"hi",
		"hr",
	    "cs",
		"sv",
		"gd",
		"et",
		"eo",
		"jv",
		"ja"
		
		};
		
		
		return lan[position];
	}
	
	
		
	
}
