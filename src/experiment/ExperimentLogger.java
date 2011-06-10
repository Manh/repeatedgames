package experiment;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import reward.Reward;

import agent.Agent;

import util.Action;
import util.JointActionState;
import util.JointActionStateDomain;
import util.State;

/**
 * @author Enrique Munoz de Cote
 * 
 */
public class ExperimentLogger {

	private int timeStep;
	private File file;
	private File configFile;
	StringBuffer content;
	StringBuffer config;
	private int[] stateCount;
	String ret =	System.getProperty("line.separator");
	SimpleDateFormat dateFormat;
	String tempPath = null;
	String logPath = null;
	String xmlFile;
	
	public ExperimentLogger(){
        File tmpPath = new File(System.getProperty("user.home")+"/experiments/repeatedgames/tmp/");
        tempPath = System.getProperty("user.home")+"/experiments/repeatedgames/";
        logPath = System.getProperty("user.home")+"/experiments/repeatedgames/";

        if(!tmpPath.exists()){
        	tmpPath.mkdirs();
        }
		configFile = new File(tempPath + "tmp/configurationLogger.log");
		config = new StringBuffer();
		//getFileStateExperience(tempFile);
	}
	
	public void setLogFile(String f, int i){
        file = new File(logPath + "tmp/" + f + "_rewardLogger--" + i + ".log");
		content = new StringBuffer();
	}
	
	private void getFileStateExperience(File aFile){

		
	    if(aFile.exists()){
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        
	        /* for the lemonade game
	        int i=0;
	        int k=0;
	        while (( line = input.readLine()) != null){
	        	stateCount[i] += Integer.parseInt(line);
	        	k += stateCount[i];
	        	i++;
	        }
	        assert(k == 100);
	        */
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    }

	}
	
	public void flushtoFile() throws FileNotFoundException, IOException {

		Writer output = new BufferedWriter(new FileWriter(file));
	    try {
	      //FileWriter always assumes default encoding is OK!
	      output.write( content.toString() );
	    }
	    finally {
	      output.close();
	    }
	    
	}
	
	public void flushtoConfigFile() throws FileNotFoundException, IOException {

		Writer output = new BufferedWriter(new FileWriter(configFile));
	    try {
	      //FileWriter always assumes default encoding is OK!
	      output.write( config.toString() );
	    }
	    finally {
	      output.close();
	    }
	    
	}
	
	
	private void moveLogs(){
	    Calendar now = new GregorianCalendar();
	    Date nowDate = now.getTime();
	    dateFormat = new SimpleDateFormat("yyMMddHHmm");
	    String date = dateFormat.format(nowDate);
	    File logDir = new File(logPath+ "/" + date);
	    logDir.mkdir();
	    File log = new File(logPath + "/" + date+ "/data.txt");
	    File tmp = new File(tempPath + date + "/log.txt");
	    file.renameTo(log);
	    configFile.renameTo(tmp);
	}
	
	private void flushtoTempFile() throws FileNotFoundException, IOException {
		String ret =	System.getProperty("line.separator");
		//for (Integer c : stateCount) 
		//	content.append(c.toString()+ret);
		  Writer output = new BufferedWriter(new FileWriter(configFile));
		    try {
		      //FileWriter always assumes default encoding is OK!
		      output.write( content.toString() );
		    }
		    finally {
		      output.close();
		    }
	}
	
	public void flush(){
		    try {
				//flushtoTempFile();
				flushtoFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}
	
	public void partialFlush() throws FileNotFoundException, IOException {

		Writer output = new BufferedWriter(new FileWriter(file));
	    try {
	      //FileWriter always assumes default encoding is OK!
	      output.write( content.toString() );
	    }
	    finally {
	    	content.setLength(0);
	    }
	}
	
	public void recordUtils(int[] utils, int iter){
		int i = 0;
		//content.setLength(0);
		for (i=0;i<utils.length;i++) {
			content.append(utils[i] + "\t");
			//content.append("\t" + stateCount[i] + ret);
		}
		content.append("\t" + iter);
		content.append(ret);
	/*	
		if(iter%1000 == 0){
			try {
				//flushtoTempFile();
				partialFlush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}
	
	public void recordMean(int[] utils, int iter){
		content.append("Total utility: ");
		int i = 0;
		//content.setLength(0);
		for (i=0;i<utils.length;i++) {
			Integer n = utils[i];
			Double util = n.doubleValue()/iter;
			content.append(util.toString() + "\t");
			//content.append("\t" + stateCount[i] + ret);
		}
		content.append(ret);
		config.append(ret);
	}

	public void recordActions(Vector<Object> act){
		for (Object o : act) {
			content.append(o + "\t");
		}
	}
	
	public void recordConfig(String s){
		config.append(s);
		config.append(ret);
	}


}