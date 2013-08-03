import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.java.dev.eval.Expression;


public class main {

   
	public static void main(String[] args) throws IOException {
		 File f = new File("input.txt");
		 BufferedReader br = new BufferedReader(new InputStreamReader(  
				new FileInputStream(f)));  
		 
		 File ouput = new File("output.txt");
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(  
				new FileOutputStream(ouput)));  
		 Map<String,Double> convert = new HashMap<String,Double>();
		 String line;
		 while( ( line =br.readLine()).indexOf("=")!=-1){
			 String []  c = line.split("=");
			 convert.put(c[0].trim().replace("1", ""), Double.valueOf(c[1].replace(" m", "").trim()) );
		 }
		 Map<String,String> fuzhuandan = new HashMap<String,String>();
		 fuzhuandan.put("miles", "mile");
		 fuzhuandan.put("inches", "inch");
		 fuzhuandan.put("faths", "fath");
		 fuzhuandan.put("feet", "foot");
		 fuzhuandan.put("yards", "yard");
		 
		 bw.write("448318588@qq.com"+"\n");
		 bw.write( "\n" );
		 
		 
		 while( ( line =br.readLine())!=null){
			 if(line.trim().equals("")){
				 continue;
			 }
			 
			 
			 Iterator<String> it =  fuzhuandan.keySet().iterator();
			 while(it.hasNext()){
				 String key = it.next();
				 String value =  fuzhuandan.get(key);
				 line = line.replaceAll(key,value);
			 }
			  it =  convert.keySet().iterator();
		    	 while(it.hasNext()){
				 String key = it.next();
				 String value = String.valueOf(convert.get(key));
				 line = line.replaceAll(key,"*"+value);
			 }
			 
			 
			  Expression exp = new Expression(line);

				Map<String, BigDecimal> variables = new HashMap<String, BigDecimal>();

			 

				BigDecimal result = exp.eval(variables);
				 float fl = result.floatValue();
				 DecimalFormat df = new DecimalFormat("0.##");
				 
				String res  =  df.format(fl)+" m";
				//res  =  String.valueOf(fl);
				System.out.println(res);
				bw.write(res+"\n");
				bw.flush();
				 
		 }
		 bw.flush();
		 bw.close();
		 br.close();
	}

}
