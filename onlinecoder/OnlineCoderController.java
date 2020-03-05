package com.yatra.automation.dashboard.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatra.automation.dashboard.constants.Constants;
import com.yatra.automation.dashboard.models.AmadeusEmployeeData;
import com.yatra.automation.dashboard.models.AmadeusEmployeeExitData;
import com.yatra.automation.dashboard.models.DBQueryMobileApp;
//import com.yatra.automation.dashboard.models.Events;
//import com.yatra.automation.dashboard.models.Employee;
import com.yatra.automation.dashboard.models.MobileAppPerfData;
//import com.yatra.automation.dashboard.services.EventPublisherService;
//import com.yatra.automation.dashboard.services.EventPublisherService;
import com.yatra.automation.dashboard.utils.DBUtil;
import com.yatra.automation.dashboard.utils.JSONUtil;
import com.yatra.automation.dashboard.utils.JenkinsSanityRun;

//import static com.yatra.automation.dashboard.constants.AdminToolConstants.*;

@Controller
public class OnlineCoderController {

	@RequestMapping(value = "/onlinecodermethod", method = RequestMethod.POST)
	public ModelAndView onlineCoderMethod(HttpServletRequest request,
			HttpServletRequest response, ModelMap model) throws IOException,
			InterruptedException {

		/*File problemFile = new File("D:\\ProblemMonk1.txt");*/
		
		String fileData="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Monk Takes a Walk<br>"

+"<br>Today, Monk went for a walk in a garden. There are many trees in the garden and each tree <br>has an English alphabet on it. While Monk was walking, he noticed that all trees with <br>vowels on it are not in good state. He decided to take care of them. So, he asked you to <br>tell him the count of such trees in the garden." 
+"<br>Note : The following letters are vowels: 'A', 'E', 'I', 'O', 'U' ,'a','e','i','o' and 'u'."

+"<br>Input:"
+"<br>The first line consists of an integer T denoting the number of test cases."
+"<br>Each test case consists of only one string, each character of string denoting the alphabet <br>(may be lowercase or uppercase) on a tree in the garden."
+"<br>Output:"
+"<br>For each test case, print the count in a new line."

+"<br>Constraints:"
+"<br>SAMPLE INPUT" 
+"<br>2"
+"<br>nBBZLaosnm"
+"<br>JHkIsnZtTL"


+"<br>SAMPLE OUTPUT" 
+"<br>2"
+"<br>1"


+"<br>Explanation"
+"<br>In test case 1, a and o are the only vowels. So, count=2";
		

		String filePathOfProblem=System.getProperty("user.dir") +"/ProblemMonk1.txt";
		File problemFile = new File(filePathOfProblem);
		FileWriter problemWriter = new FileWriter(problemFile);

		problemWriter.write(fileData);

		problemWriter.close();
		String homeDir=System.getProperty("user.dir");
		System.out.println("homeDir="+homeDir);

		BufferedReader reader = new BufferedReader(new FileReader(problemFile));

		String line = null;
		StringBuilder builderOfProblem = new StringBuilder();
		while ((line = reader.readLine()) != null) {

			builderOfProblem.append(line + "\n\n<br>");

		}

		model.addAttribute("problem", builderOfProblem.toString());

		/*
		 * System.out.println("codecompilevalue=" +
		 * request.getParameter("codecompile").toString());
		 */
		System.out.println(request.getParameter("language").toString());

		if (request.getParameter("javatextarea1") != null
				&& request.getParameter("language").toString()
						.equalsIgnoreCase("java")) {
			System.out
					.println(request.getParameter("javatextarea1").toString());

			String code = request.getParameter("javatextarea1").toString();

			String filePath=homeDir+"/TestClass.java";
			File file = new File(filePath);

			FileWriter writer = new FileWriter(file);

			writer.write(code);

			writer.close();
			try {
				if (request.getParameter("codecompile").toString() != null
						&& request.getParameter("codecompile").toString()
								.contains("Compile")) {

					Runtime rt = Runtime.getRuntime();
				//	String filePath = "D:\\TestClass.java";
					/*String[] commands = { "C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac "
							+ filePath };*/
					Process proc1 = rt
							.exec("C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac "+filePath);
					Thread.sleep(10000);
					/*
					 * Process proc = rt .exec(
					 * "C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\java -cp  D:\\ TestClass"
					 * );
					 */

					BufferedReader stdInput = new BufferedReader(
							new InputStreamReader(proc1.getInputStream()));

					BufferedReader stdError = new BufferedReader(
							new InputStreamReader(proc1.getErrorStream()));

					// read the output from the command
					System.out
							.println("Here is the standard output of the command:\n");
					String s = null;
					StringBuilder builder = new StringBuilder();
					/*
					 * boolean flag=true; while ((s = stdInput.readLine()) !=
					 * null) { flag=false; System.out.println(s); break; }
					 */

					// read any errors from the attempted command
					System.out
							.println("Here is the standard error of the command (if any):\n");
					boolean flag = false;
					while ((s = stdError.readLine()) != null) {
						flag = true;
						builder.append(s + "\n\n");
						System.out.println(s);
					}

					if (flag) {
						model.addAttribute("compileresult",
								"Compilation issues found , please fix them : \n\n\n "
										+ builder.toString());

					}

					else {

						model.addAttribute("compileresult",
								"Compiled Successfully \n");
					}

					model.addAttribute("javacode", code);

					return new ModelAndView("onlinecoder");
				}
			} catch (Exception e) {

				e.printStackTrace();

			}
			try {
				if (request.getParameter("coderun").toString() != null
						&& request.getParameter("coderun").toString()
								.contains("SubmitCode")) {

					Runtime rt = Runtime.getRuntime();
				//	String filePath = "D:\\TestClass.java";
					/*String[] commands = { "C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac "
							+ filePath };*/
					/*Process proc1 = rt
							.exec("C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac "+filePath);*/
					
					Process proc1 = rt
							.exec("/data/yatra/java/latest/bin/javac "+filePath);
					Thread.sleep(10000);

					BufferedReader stdInput = new BufferedReader(
							new InputStreamReader(proc1.getInputStream()));

					BufferedReader stdError = new BufferedReader(
							new InputStreamReader(proc1.getErrorStream()));

					// read the output from the command
					System.out
							.println("Here is the standard output of the command:\n");
					String s = null;
					StringBuilder builder = new StringBuilder();
					/*
					 * boolean flag=true; while ((s = stdInput.readLine()) !=
					 * null) { flag=false; System.out.println(s); break; }
					 */

					// read any errors from the attempted command
					System.out
							.println("Here is the standard error of the command (if any):\n");
					boolean flag = false;
					while ((s = stdError.readLine()) != null) {
						flag = true;
						builder.append(s + "\n\n");
						System.out.println(s);
					}

					if (flag) {
						model.addAttribute("compileresult",
								"Compilation issues found , please fix them : \n\n\n "
										+ builder.toString());
						model.addAttribute("javacode", code);
						return new ModelAndView("onlinecoder");
					}

					else {

						/*Process proc = rt
								.exec("C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\java -cp  "+ homeDir +" TestClass");*/
						
						Process proc = rt
								.exec("/data/yatra/java/latest/bin/java -cp  "+ homeDir +" TestClass");
						stdInput = new BufferedReader(new InputStreamReader(
								proc.getInputStream()));

						stdError = new BufferedReader(new InputStreamReader(
								proc.getErrorStream()));

						builder = new StringBuilder();
						flag = true;
						while ((s = stdInput.readLine()) != null) {
							builder.append(s + "\n");
							System.out.println(s);

						}
						model.addAttribute("compileresult", "Output:\n\n"
								+ builder.toString());

					}

					model.addAttribute("javacode", code);

					return new ModelAndView("onlinecoder");

				}
			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		if (request.getParameter("ctextarea1") != null)
			System.out.println(request.getParameter("ctextarea1").toString());

		if (request.getParameter("pythontextarea1") != null)
			System.out.println(request.getParameter("pythontextarea1")
					.toString());

		if (request.getParameter("language") != null)
			System.out.println(request.getParameter("language").toString());

		return new ModelAndView("onlinecoder");
	}

	@RequestMapping(value = "/onlinecoder", method = RequestMethod.GET)
	public ModelAndView onlineCoder(HttpServletRequest request,
			HttpServletRequest response, ModelMap model) throws IOException {

		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("Email"));
		System.out.println(request.getParameter("choice0"));
		
		/*File problemFile = new File("D:\\ProblemMonk1.txt");*/

		String fileData="                                              \n" +
	            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Monk Takes a Walk<br>\n" +
	            "\n" +
	            "<br>Today, Monk went for a walk in a garden. There are many trees in the garden and each tree <br>has an English alphabet on it. While Monk was walking, he noticed that all trees with <br>vowels on it are not in good state. He decided to take care of them. So, he asked you to <br>tell him the count of such trees in the garden. \n" +
	            "<br>Note : The following letters are vowels: 'A', 'E', 'I', 'O', 'U' ,'a','e','i','o' and 'u'.\n" +
	            "\n" +
	            "<br>Input:\n" +
	            "<br>The first line consists of an integer T denoting the number of test cases.\n" +
	            "<br>Each test case consists of only one string, each character of string denoting the alphabet <br>(may be lowercase or uppercase) on a tree in the garden.\n" +
	            "<br>Output:\n" +
	            "<br>For each test case, print the count in a new line.\n" +
	            "\n" +
	            "<br>Constraints:\n" +
	            "<br>SAMPLE INPUT \n" +
	            "<br>2\n" +
	            "<br>nBBZLaosnm\n" +
	            "<br>JHkIsnZtTL\n" +
	            "\n" +
	            "\n" +
	            "<br>SAMPLE OUTPUT \n" +
	            "<br>2\n" +
	            "<br>1\n" +
	            "\n" +
	            "\n" +
	            "<br>Explanation\n" +
	            "<br>In test case 1, a and o are the only vowels. So, count=2";
		

		String filePathOfProblem=System.getProperty("user.dir") +"/ProblemMonk1.txt";
		File problemFile = new File(filePathOfProblem);
		FileWriter problemWriter = new FileWriter(problemFile);

		problemWriter.write(fileData);

		problemWriter.close();
		
		BufferedReader reader = new BufferedReader(new FileReader(problemFile));

		String line = null;
		StringBuilder builderOfProblem = new StringBuilder();
		while ((line = reader.readLine()) != null) {

			builderOfProblem.append(line + "\n\n<br>");

		}

		reader.close();
		String javaSample="/* IMPORTANT: Multiple classes and nested static classes are supported */\n" +
                "\n" +
                "/*\n" +
                " * uncomment this if you want to read input.\n" +
                "//imports for BufferedReader\n" +
                "import java.io.BufferedReader;\n" +
                "import java.io.InputStreamReader;\n" +
                "\n" +
                "//import for Scanner and other utility classes\n" +
                "import java.util.*;\n" +
                "*/\n" +
                "\n" +
                "// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail\n" +
                "\n" +
                "class TestClass {\n" +
                "    public static void main(String args[] ) throws Exception {\n" +
                "        /* Sample code to perform I/O:\n" +
                "         * Use either of these methods for input\n" +
                "\n" +
                "        //BufferedReader\n" +
                "        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n" +
                "        String name = br.readLine();                // Reading input from STDIN\n" +
                "        System.out.println(\"Hi, \" + name + \".\");    // Writing output to STDOUT\n" +
                "\n" +
                "        //Scanner\n" +
                "        Scanner s = new Scanner(System.in);\n" +
                "        String name = s.nextLine();                 // Reading input from STDIN\n" +
                "        System.out.println(\"Hi, \" + name + \".\");    // Writing output to STDOUT\n" +
                "\n" +
                "        */\n" +
                "\n" +
                "        // Write your code here\n" +
                "\n" +
                "    }\n" +
                "}";
		
		/*File javaSampleFile = new File("D:\\JavaSampleToLoad.txt");

		reader = new BufferedReader(new FileReader(javaSampleFile));*/
		

		String javaSampleFilePath=System.getProperty("user.dir") +"/JavaSampleToLoad.txt";
		File javaFile = new File(javaSampleFilePath);
		FileWriter javaSampleProblemWriter = new FileWriter(javaFile);

		javaSampleProblemWriter.write(javaSample);

		javaSampleProblemWriter.close();
		
		
		line = null;
		reader = new BufferedReader(new FileReader(javaFile));
		StringBuilder builderOfJavaSample = new StringBuilder();
		while ((line = reader.readLine()) != null) {

			builderOfJavaSample.append(line + "\n");

		}
		
		reader.close();
		model.addAttribute("compileresult", "");
		model.addAttribute("problem", builderOfProblem.toString());
		model.addAttribute("javacode", builderOfJavaSample.toString());
		return new ModelAndView("onlinecoder");

	}
	
	
	@RequestMapping(value = "/userregister", method = RequestMethod.GET)
	public ModelAndView userRegister(HttpServletRequest request,
			HttpServletRequest response, ModelMap model) throws IOException {
                
		System.out.println(request.getAttribute("choice0"));
		        model.addAttribute("homedir", System.getProperty("user.dir"));
				return new ModelAndView("userregister");

	}
	
	
	
	
	@RequestMapping(value = "/mcq", method = RequestMethod.GET)
	public ModelAndView onlineCoder1(HttpServletRequest request,
			HttpServletRequest response, ModelMap model) throws IOException {

		
		System.out.println(request.getParameter("choice0"));
		System.out.println(request.getParameter("choice1"));
		
		/*File problemFile = new File("D:\\ProblemMonk1.txt");*/

		
		return new ModelAndView("mcq");

	}

}
