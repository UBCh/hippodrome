import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Writer {

    String nameFile;
    private String path ;

    public Writer() throws ParseException {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String date = simpleDateFormat.format(new Date());
	this.nameFile = "hippodrome"+date;
	this.path = "logs/"+nameFile+".log";
    }

    public void writingToFile(String toStringForWrite) {

	StringBuilder stringBuilder = new StringBuilder();
	   stringBuilder.append(toStringForWrite).append("\n");

	try {
	    FileWriter writer = new FileWriter(path, true);
	    writer.append(stringBuilder);
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}