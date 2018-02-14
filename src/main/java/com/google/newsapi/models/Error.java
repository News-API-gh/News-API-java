package com.google.newsapi.models;
import static com.google.newsapi.config.Constants.*;
public class Error
{
  public ERROR_CODES code;
  public String Message;
  
	@Override
	public String toString()
	{
		return "Error [code=" + code + ", Message=" + Message + "]";
	}
  
  
}
