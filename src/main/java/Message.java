import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;
import java.util.logging.Level;

public class Message implements Supplier<String> {

 String message;
 String infoClass;


       public Message(Class c,String message) {
	this.message = message;
	this.infoClass = c.getName();
    }

    @Override
    public String get() {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"+","+"SSS");
	String date = simpleDateFormat.format(new Date());
	String result=date+" "+ Level.INFO+"  "+infoClass+" : "+message;
	Writer writer= null;
	try {
	    writer = new Writer();
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	writer.writingToFile(result);
	return result;
    }
    }

