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



public class DataRead {
	
	public WebDriver driver;
	String[][] Data;
	int k=0,j=0;
	String Local = "C:\\FTPDownload\\Processing";
	String Local1 = "C:/FTPDownload/Processing/";
	String outputLoc = "C:/FTPDownload/Archived/";
	
	public DataRead(WebDriver driver) {
		this.driver = driver;
	}
	
	@Before
	public void setUp() {

		System.out.println("This is the edited line made by rehan");
		System.out.println("Added in rehan repo this is the second line");
		System.out.println("Added by rehansharaf200@gmail.com");
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
		
		
		/*driver.get("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1598419318&rver=7.1.6819.0&"
				+ "wp=MBI_SSL&wreply=https%3A%2F%2Flw.skype.com%2Flogin%2Foauth%2Fproxy%3Fclient_id%3D633212%"
				+ "26state%3DPZqNxdz2uvry%26redirect_uri%3Dhttps%253A%252F%252Fmanager.skype.com%252Flogin%253"
				+ "Fattempt%253D1%2526return_url%253Dhttps%25253A%25252F%25252Fmanager.skype.com%25252F%26response"
				+ "_type%3Dpostgrant%26policy%3DMBI_SSL&lc=1033&id=293290&mkt=en-US&psi=skype&lw=1&"
				+ "cobrandid=2befc4b5-19e3-46e8-8347-77317a16a5a5&client_flight=ReservedFlight33%2CReservedFlight67");
		
		waitForElement("Sign in","//div[@role='heading' and text()='Sign in']");
		
		driver.findElement(By.name("loginfmt")).clear();
		driver.findElement(By.name("loginfmt")).sendKeys("googlevoice183");
		driver.findElement(By.id("idSIButton9")).click();
		waitForElement("Enter password","//div[@id='loginHeader']");
		driver.findElement(By.name("passwd")).clear();
		driver.findElement(By.name("passwd")).sendKeys("CoRona740!@$");
		driver.findElement(By.id("idSIButton9")).click();
		waitForElement("Stay signed in?","//div[@role='heading']");

		driver.findElement(By.id("idSIButton9")).click();
		waitForElement("medical lien management","//div[@class='YJJGe']/h1");
		driver.get("https://manager.skype.com/reports");
		
		waitForElement_specificWord("Reports Summary","//h1[@class='focus-on-load']");

		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String yesterdayDate =  dateFormat.format(yesterday());
        System.out.println("Yesterday Date "+yesterdayDate);
        
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterdayDate1 =  dateFormat.format(yesterday());
        System.out.println("Yesterday Date "+yesterdayDate1);

		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String todayDate = df.format(new Date());
		System.out.println("Today Date "+todayDate);
		
		df = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate1 = df.format(new Date());
		System.out.println("Today Date "+todayDate1);

		((JavascriptExecutor)driver).executeScript("document.getElementById('startDate').setAttribute('value','"+yesterdayDate+"')");
		((JavascriptExecutor)driver).executeScript("document.getElementById('endDate').setAttribute('value','"+todayDate+"')");
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('altFrom').setAttribute('value','"+yesterdayDate1+"')");
		((JavascriptExecutor)driver).executeScript("document.getElementById('altTo').setAttribute('value','"+todayDate1+"')");
	
		driver.findElement(By.xpath("//span[@class='blue']")).click();
		String createdone = "Reports Summary - "+yesterdayDate+" to "+todayDate;
		waitForElement(createdone,"//h1[@class='focus-on-load']");
		Thread.sleep(1000);
		driver.findElement(By.id("downloadAllocationReports")).click();
		waitForElement("Please choose which reports you would like to download.","//p[@class='important']");
		driver.findElement(By.name("export[allocations]")).click();
		driver.findElement(By.xpath("//span[@class='blue' and text()='Generate']")).click();
		
		String filename = "Usage_"+yesterdayDate1+"_"+todayDate1+".csv";
		File f = new File(Local1+filename);
		int exist = 0;
		while(exist == 0) {
			if(f.exists()){
				exist = 1;
				break;
			}else {
				Thread.sleep(1000);
			}
		}
		
		driver.get("https://manager.skype.com/logout");
		*/
		
		readAllDataAtOnce("C:/FTPDownload/Archived/Usage_2020-08-25_2020-08-26.csv");
//		for(int a = 0; a<Data.length; a++) {
//			for(int b=0; b<11; b++)
//				System.out.print(Data[a][b]+"       ");
//		
//			System.out.println();
//		}
		
		
		//f.renameTo(new File(outputLoc+filename));
			
		
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
