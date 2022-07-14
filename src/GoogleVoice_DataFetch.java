import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;



public class GoogleVoice_DataFetch {
	
	public WebDriver driver;
	String[][] Data;
	int k=0,j=0;
	String Local = "C:\\FTPDownload\\Processing";
	String Local1 = "C:/FTPDownload/Processing/";
	String outputLoc = "C:/FTPDownload/Archived/";
	
	@Before
	public void setUp() {
		
		/*System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
		driver.manage().window().maximize();
		*/
		
		DesiredCapabilities caps = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", Local);
        options.setExperimentalOption("prefs", prefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
    	System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver(caps);
		driver.manage().window().maximize();
		
	}
	
	
	@Test
	public void testUntitled() throws InterruptedException {
		
		
		driver.get("https://admin.google.com/");
		
		waitForElement("Choose an account","//span[text()='Choose an account']");
		driver.findElement(By.xpath("//div[@class='BHzsHc' and text()='Use another account']")).click();
		waitForElement("Sign in","//span[text()='Sign in']");
		
		driver.findElement(By.name("identifier")).clear();
		driver.findElement(By.name("identifier")).sendKeys("pbi@proglobaltechnologies.com");
		driver.findElement(By.xpath("//button[@jsname='LgbsSe']")).click();
		waitForElement("Power BI","//span[text()='Power BI']");

		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Pakist@nisb123!!@@");
		driver.findElement(By.xpath("//button[@jsname='LgbsSe']")).click();
		waitForElement("Admin Console","//div[@class='dIg4sc avSwxc']");
		
		driver.get("https://admin.google.com/ac/reporting/audit/voice");
		waitForElement("Voice","//div[@class='dIg4sc avSwxc']");
		
		driver.findElement(By.xpath("//span[text()='Date range']")).click();
		driver.findElement(By.xpath("//span[@class='RveJvd snByac' and text()='Yesterday']")).click();
		Thread.sleep(2000);
		
		waitForElement("Yesterday","//span[@tabindex='0' and text()='Yesterday']");
		driver.findElement(By.xpath("//div[@role='button' and @data-tooltip='Download']")).click();
		
		waitForElement("Download","//div[@class='NQgmDb bEd2J']");
		driver.findElement(By.xpath("//div[@data-value='FORMAT_CSV']/div/div[@class='t5nRo Id5V1']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='RveJvd snByac' and text()='DOWNLOAD']")).click();
		waitForElement("In progress","//h2[@class='vSJYNb' and text()='In progress']");

		waitForElement("Audit logs CSV file is ready to download.","(//h2[@class='vSJYNb' and text()='In progress']/parent::div/div/div/div[@class='DlkbId']/div)[1]");
		driver.findElement(By.xpath("(//h2[@class='vSJYNb' and text()='In progress']/parent::div/div/div/div[@class='DlkbId']/div/a)[1]")).click();
		
		
		
		String filename = null;
		 File folder = new File(Local1);
		 File[] listOfFiles = folder.listFiles();

		     for (int i = 0; i < listOfFiles.length; i++) {
		       if (listOfFiles[i].isFile()) {
		         System.out.println("File " + listOfFiles[i].getName());
		         filename = listOfFiles[i].getName();
		       } 
		     }
		     
		     int exist = 0;
				while(exist == 0) {
					if(filename.contains(".csv")){
						exist = 1;
						break;
					}else {
						Thread.sleep(1000);
						
						listOfFiles = folder.listFiles();

					     for (int i = 0; i < listOfFiles.length; i++) {
					       if (listOfFiles[i].isFile()) {
					         System.out.println("File " + listOfFiles[i].getName());
					         filename = listOfFiles[i].getName();
					       } 
					     }
						
					}
				}
				
		
		
		
		readAllDataAtOnce(Local1+filename);
		//dbUpdation();
		File f = new File(Local1+filename);
		boolean check = f.renameTo(new File(outputLoc+filename));
		System.out.println(check);
		driver.get("https://accounts.google.com/Logout");
			
		
	}
	
	@After
	public void tearDown() {
		
		driver.quit();
	}


	public void readAllDataAtOnce(String file) 
	{ 
	    try { 
	        // Create an object of file reader 
	        // class with CSV file as a parameter. 
	        FileReader filereader = new FileReader(file); 
	  
	        // create csvReader object and skip first Line 
	        CSVReader csvReader = new CSVReaderBuilder(filereader) 
	                                  .withSkipLines(1) 
	                                  .build(); 
	        List<String[]> allData = csvReader.readAll(); 
	        int count = allData.size();
	        Data = new String[count][12];
	  
	        // print Data 
	        for (String[] row : allData) { 
	            for (String cell : row) { 
	            	
	            	if(j==4) {
	            		    String[] parts = cell.split(" ");
	            		    Data[k][j] = parts[0];
	            		    j++;
	            		    Data[k][j] = parts[1];
	            		    j++;
	            		   // System.out.println("Date: " + parts[0]);
	            		   // System.out.println("Time: " + parts[1] + " " + parts[2]);
	            	}else {
	            	
	            		Data[k][j] = cell;
		                j++;
	            	}
	                
	            } 
	            //System.out.println();
	            k++;
	            j=0;
	        } 
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	} 
	
	
	public void waitForElement(String v1 ,String item) throws InterruptedException {
	  	  
	  	  //System.out.println("item is "+item);
	  	  //System.out.println("v1 is "+v1);
	  	  
	  	  
	  	  for (int second = 0;; second++) {
	  		     if (second >= 40) fail("timeout");
	  		     try { 
	  		    	//String g1 = driver.findElement(By.xpath("//html/body/div/div/div/div[3]/div[2]/label")).getText();

	  		    	 Thread.sleep(1000);
	  		    	 String g1 = driver.findElement(By.xpath(item)).getText();
	  		    	 //System.out.println("g1 is "+g1);
	  		    	 v1 = v1.trim();
	  		    	 g1 = g1.trim();
	  		    	 System.out.println("Actual Text is "+g1);
	  		    	 System.out.println("Expected Text is "+v1);
	  		    	 if (v1.equals(g1))
	  		    	 
	  				    //System.out.println("g1 is "+g1);
	  		    		 break;
	  		    	 
	  		    	 
	  		    	 
	  		     } 
	  		     catch (Exception e){}
	  		     Thread.sleep(1000);
	  	  	
	  	  }
	  	  
	    }	
	
	
	public void waitForElement_specificWord(String v1 ,String item) throws InterruptedException {
	  	  
	  	  //System.out.println("item is "+item);
	  	  //System.out.println("v1 is "+v1);
	  	  
	  	  
	  	  for (int second = 0;; second++) {
	  		     if (second >= 40) fail("timeout");
	  		     try { 
	  		    	//String g1 = driver.findElement(By.xpath("//html/body/div/div/div/div[3]/div[2]/label")).getText();

	  		    	 Thread.sleep(1000);
	  		    	 String g1 = driver.findElement(By.xpath(item)).getText();
	  		    	 System.out.println("Actual Text is "+g1);
	  		    	 System.out.println("Expected Text is "+v1);
	  		    	 v1 = v1.trim();
	  		    	 g1 = g1.trim();
	  		    	 if (g1.contains(v1))
	  		    	 
	  				    //System.out.println("g1 is "+g1);
	  		    		 break;
	  		    	 
	  		    	 
	  		    	 
	  		     } 
	  		     catch (Exception e){}
	  		     Thread.sleep(1000);
	  	  	
	  	  }
	  	  
	    }	
	
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
}
