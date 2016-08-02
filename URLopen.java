import java.net.*;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.io.*;

public class URLopen  {

	
	public static void main(String[] args) throws URISyntaxException  
	{
		Scanner sc = new Scanner(System.in);
		String url = sc.nextLine().toString();
		
		//encode the string of the url
		URI uri = new URI(url);
		url = uri.toASCIIString();
		
		//pass the url to the showframe method to use JEditorPane to show the URL
		showframe(url);
		
		//pass the url to saveinfile method for reading from the URLConnection
		saveinfile(url);
	}
	
	public static void saveinfile(String u){
		URL url;

		try {
			// get the desired URL content
			url = new URL(u);
			URLConnection cn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(cn.getInputStream()));

			

			String fileName = "E:\\UIS\\new.html";
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter f = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(f);

			String newLine;
			while ((newLine = br.readLine()) != null) {
				bw.write(newLine);
			}

			bw.close();
			br.close();

			System.out.println("Done writing to the file \n Now you can see the result in new.html file");

		
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

	public static void showframe(String url){
		
		try {
			URL u = new URL(url);
			InputStream inp = u.openStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(inp));
			
		        String str;
		        while ((str = in.readLine()) != null) {
		            str = in.readLine().toString();
		            System.out.println(str);
		            
		        }
		        
		        in.close();
		        
		        JFrame frame = new JFrame();
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    JEditorPane editorPane = new JEditorPane();

			    editorPane.setPage(u);

			    frame.add(new JScrollPane(editorPane));

			    frame.setSize(500, 400);
			    frame.setVisible(true);
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
