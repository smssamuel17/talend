package routines;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.*;
import routines.system.FastDateParser;
import routines.system.LocaleProvider;

import routines.system.FastDateParser;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class DQ_METHODS {

    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
	
    public static Integer checkInteger(String value) {
        if (value == null) {
           return null;
        }
        value=routines.DQ_METHODS.stripControls(value.replace(" ",""));
        Pattern p = Pattern.compile("^(\\+|-)?\\d+$");
        Matcher m =p.matcher(value);
        if (m.matches() ){
        	return Integer.parseInt(value);
        }else
        	return null;
    }
    public static Integer checkInteger(Integer value) {
        if (value == null) {
           return null;
        }else
        	return value;
    }
    
    public static Integer checkPositiveInteger(Integer value) {
        if (value == null) {
           return null;
        }else{
        	Pattern p = Pattern.compile("^(\\+)?\\d+$");
        	Matcher m =p.matcher(value.toString());
        	if (m.matches() ){
        	return value;
        	}else {
        	 return -1;
        	}
        }
    }
    
    public static Boolean checkPositiveIntegerRule(String value) {
        if (value == null) {
           return false;
        }else{
        	Pattern p = Pattern.compile("^(\\+)?\\d+$");
        	Matcher m =p.matcher(value);
        	if (m.matches() ){
        	return true;
        	}else {
        	 return false;
        	}
        }
    }
    
        public static Double checkDouble(Double value) {
        if (value == null) {
           return Double.parseDouble("0.00");
        }else
        	return value;
    }
        
     public static Double checkDouble(Integer value) {
            if (value == null) {
               return Double.parseDouble("0.00");
            }else
            	return value.doubleValue();
    }
     
    public static Double checkDouble(String value) {
        if (value == null) {
           return Double.parseDouble("0.00");
        }else if(value.equals("")){
        	return Double.parseDouble("0.00");
    }else
    	return Double.parseDouble(value);
    }
    
    public static String checkZipcode9(String value) {
        if (value == null) {
           return "";
        }else if(value.equals("")){
        	return "";
    }else{
    	 Pattern p = Pattern.compile("^\\d{5}$|^\\d{5}-\\d{4}$");
         Matcher m =p.matcher(value);
         if (m.matches() ){
        	return value.trim();
        }else{
        	return "";
       }
       }
    }
    
    public static Boolean checkSFDCAccountRule(String value) {
        if (value == null) {
           return false;
        }else if(value.equals("")){
        	return false;
    }else{
    	 value=value.replaceAll("\\d+","").trim();
    	 value=value.replaceAll("null","").trim();
    	 value=value.trim();
    	 Pattern p = Pattern.compile("^\\pL+[\\pL\\pZ\\pP]{0,}$");
         Matcher m =p.matcher(value);
         if (m.matches() ){
        	return true;
        }else{
        	return false;
       }
       }
    }
    
    
    public static String checkEmail(String value) {
        if (value == null) {
           return "";
        }
        value=value.trim();
        value=routines.DQ_METHODS.stripControls(value.replace(" ",""));
      Pattern p = Pattern.compile("^(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|”(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*”)@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)*[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\]|(?:\\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\\]))");
//    Pattern p = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");        
        Matcher m =p.matcher(value);
        if (m.matches() ){
        	return value.trim();
        }else
        	return "";
        
    }
    
    public static Boolean checkEmailValidity(String value) {
        if (value == null || value.equals("null") || value.trim().equals("")) {
           return true;
        }
        value=value.trim();
        value=routines.DQ_METHODS.stripControls(value.replace(" ",""));
//      Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Pattern p = Pattern.compile("^(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|”(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*”)@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)*[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\]|(?:\\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\\]))");
//      Pattern p = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m =p.matcher(value);
        if (m.matches() ){
        	return true;
        }else
        	return false;
        
    }
    
    public static String stripControls(String arg) 
    {
     if(arg==null){
        return "";
     }
     arg=arg.trim();
     // The pattern matches control characters
    //java.util.regex.Pattern p = java.util.regex.Pattern.compile("[\\x00-\\x1F\\x7F]");
    Pattern p = java.util.regex.Pattern.compile("\\p{C}");
    Matcher m = p.matcher(arg);
    String aLine = arg;
    m.reset(aLine);
    //Replaces control characters with an empty
    //string.
    String result = m.replaceAll("");
    return result;
        }
    
public static Date checkDateFormat(String format, String value ) throws ParseException {
	    if (value==null && format.equals("yyyy-MM-dd")) {
	    	value="1900-01-01";
	    }
	    if (value==null && format.equals("yyyyMMdd")){
	    	value="19000101";
	    }
        DateFormat df = new SimpleDateFormat(format);
    	DateFormat def=new SimpleDateFormat("yyyyMMdd");
    	DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
    	Date d1=dfm.parse("1900-01-01");
    	Date d2=dfm.parse("1900-01-01");
    	String pattern=format;
    	Pattern p=null;
    	value=routines.DQ_METHODS.stripControls(value.replace(" ",""));
String    	str_value=value;
if(pattern.equals("yyyy-MM-dd")){
p = Pattern.compile("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");
}else
if(pattern.equals("yyyyMMdd")){
p = Pattern.compile("^(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$");
}
Matcher m =p.matcher(str_value);
if (m.matches() ){
try{
	d2=df.parse(value);
}catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
return df.parse(df.format(d2));
}else
return df.parse(df.format(d1));

   	
	}
public static String checkPhone(String strPhone){
	if(strPhone==null){
	    return "";
	}
	strPhone=strPhone.trim().replaceAll("-","");
	if(strPhone.trim().length()>10){
		return "(" + strPhone.substring(0,3) + ")" + strPhone.substring(3,6) +"-" + strPhone.substring(6,10) + " ext-" + strPhone.substring(10,strPhone.length());
	}
	if(strPhone.trim().length()==10){
		return "(" + strPhone.substring(0,3) + ")" + strPhone.substring(3,6) +"-" + strPhone.substring(6,10);
	}else if(strPhone.trim().length()>=7 && strPhone.trim().length()<=9){
		return strPhone.substring(0,3) + "-" +strPhone.substring(3,strPhone.length());
	}else 
		return "";
	}


}

