import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.dataproc.v1.HadoopJob;
import com.google.cloud.dataproc.v1.Job;
import com.google.cloud.dataproc.v1.JobControllerClient;
import com.google.cloud.dataproc.v1.JobControllerSettings;
import com.google.cloud.dataproc.v1.JobMetadata;
import com.google.cloud.dataproc.v1.JobPlacement;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.nio.file.Paths;
import com.google.auth.oauth2.GoogleCredentials;





public class FrontEnd {
	static class gui {
		//Global variables
		private static JFrame frame;
		private static JLabel labelWelcome;
		private static JLabel labelHugo;
		private static JRadioButton buttonHugo;
		private static JLabel labelShakespeare;
		private static JRadioButton buttonShakespeare;
		private static JRadioButton buttonComedies;
		private static JRadioButton buttonHistory;
		private static JRadioButton buttonPoetry;
		private static JRadioButton buttonTragedies;
		private static JLabel labelTolstoy;
		private static JRadioButton buttonTolstoy;
		private static JRadioButton buttonAll;
		private static ButtonGroup bg;
		private static JButton buttonRun;
		private static JTextArea textResults;
		private static JTextField fieldInput;
		private static JButton buttonResults;
		
		
		
		private static void initialize()
		{
			frame = new JFrame();
	        frame.setBounds(250, 250, 350, 700);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(null);
	        frame.getContentPane().setBackground(new Color(102, 153, 204));
	        
	        //welcome message
	        labelWelcome = new JLabel("Run Top          Inverted Indexing On");
	        labelWelcome.setBounds(70, 5, 400, 50);
	        labelWelcome.setForeground(Color.BLACK);
	        labelWelcome.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(labelWelcome);
	        //Set up JTextField for user input -> represents n value in top n
	        fieldInput = new JTextField();
	        fieldInput.setBounds(125, 20, 35, 20);
	        fieldInput.setText("100");
	        frame.getContentPane().add(fieldInput);
	        //Initialize button group to ensure one button at a time
	        bg = new ButtonGroup();
	        //Hugo label
	        labelHugo = new JLabel("Hugo");
	        labelHugo.setBounds(50, 50, 100, 50);
	        labelHugo.setForeground(Color.BLACK);
	        labelHugo.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(labelHugo);
	        //Hugo button
	        buttonHugo = new JRadioButton("All Works");
	        buttonHugo.setBounds(20, 100, 100, 50);
	        buttonHugo.setBackground(new Color(80, 100, 150));
	        buttonHugo.setForeground(new Color(200, 200, 255));
	        buttonHugo.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonHugo);
	        frame.getContentPane().add(buttonHugo);
	        //Shakespeare Label
	        labelShakespeare = new JLabel("Shakespeare");
	        labelShakespeare.setBounds(130, 50, 100, 50);
	        labelShakespeare.setForeground(Color.BLACK);
	        labelShakespeare.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(labelShakespeare);
	        //Shakespeare All
	        buttonShakespeare = new JRadioButton("All Works");
	        buttonShakespeare.setBounds(120, 100, 100, 50);
	        buttonShakespeare.setBackground(new Color(80, 100, 150));
	        buttonShakespeare.setForeground(new Color(200, 200, 255));
	        buttonShakespeare.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonShakespeare);
	        frame.getContentPane().add(buttonShakespeare);
	        //Shakespeare Comedy Button
	        buttonComedies = new JRadioButton("Comedies");
	        buttonComedies.setBounds(120, 150, 100, 25);
	        buttonComedies.setBackground(new Color(80, 100, 150));
	        buttonComedies.setForeground(new Color(200, 200, 255));
	        buttonComedies.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonComedies);
	        frame.getContentPane().add(buttonComedies);
	        
	        //Shakespeare Histories Button
	        buttonHistory = new JRadioButton("Histories");
	        buttonHistory.setBounds(120, 175, 100, 25);
	        buttonHistory.setBackground(new Color(80, 100, 150));
	        buttonHistory.setForeground(new Color(200, 200, 255));
	        buttonHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonHistory);
	        frame.getContentPane().add(buttonHistory);
	        
	        //Shakespeare Poetry Button
	        buttonPoetry = new JRadioButton("Poetry");
	        buttonPoetry.setBounds(120, 200, 100, 25);
	        buttonPoetry.setBackground(new Color(80, 100, 150));
	        buttonPoetry.setForeground(new Color(200, 200, 255));
	        buttonPoetry.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonPoetry);
	        frame.getContentPane().add(buttonPoetry);
	        
	        //Shakespeare Tragedies Button
	        buttonTragedies = new JRadioButton("Tragedies");
	        buttonTragedies.setBounds(120, 225, 100, 25);
	        buttonTragedies.setBackground(new Color(80, 100, 150));
	        buttonTragedies.setForeground(new Color(200, 200, 255));
	        buttonTragedies.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonTragedies);
	        frame.getContentPane().add(buttonTragedies);
	        
	        //Tolstoy label
	        labelTolstoy = new JLabel("Tolstoy");
	        labelTolstoy.setBounds(250, 50, 100, 50);
	        labelTolstoy.setForeground(Color.BLACK);
	        labelTolstoy.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(labelTolstoy);
	        //Hugo button
	        buttonTolstoy = new JRadioButton("All Works");
	        buttonTolstoy.setBounds(220, 100, 100, 50);
	        buttonTolstoy.setBackground(new Color(80, 100, 150));
	        buttonTolstoy.setForeground(new Color(200, 200, 255));
	        buttonTolstoy.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonTolstoy);
	        frame.getContentPane().add(buttonTolstoy);
	        
	        //ALLL works
	        buttonAll = new JRadioButton("All Works / All Authors");
	        buttonAll.setBounds(20, 250, 300, 50);
	        buttonAll.setBackground(new Color(80, 100, 150));
	        buttonAll.setForeground(new Color(200, 200, 255));
	        buttonAll.setFont(new Font("Tahoma", Font.BOLD, 12));
	        bg.add(buttonAll);
	        frame.getContentPane().add(buttonAll);
	        
	        //Button for running inverted indexing
	        buttonRun = new JButton("Run Inverted Indexing");
	        buttonRun.setBounds(75, 300, 200, 50);
	        buttonRun.setForeground(Color.BLACK);
	        buttonRun.setBackground(new Color(200, 255, 220));
	        buttonRun.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(buttonRun);
	        //add actionListener
	        buttonRun.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                runActionPerformed(evt);
	            }
	        });
	        //Text area for results
	        textResults= new JTextArea();
	        textResults.setEditable(false);
	        textResults.setBounds(2, 350, 330, 200);
	        textResults.setBackground(new Color(200, 230, 255));
	        textResults.setFont(new Font("Tahoma", Font.BOLD, 8));
	        frame.getContentPane().add(textResults);
	        //Show Results Button
	        //Label for results
	        buttonResults = new JButton("Show Results");
	        buttonResults.setBounds(20, 550, 300, 25);
	        buttonResults.setForeground(Color.WHITE);
	        buttonResults.setBackground(new Color(100, 100, 100));
	        buttonResults.setFont(new Font("Tahoma", Font.BOLD, 12));
	        frame.getContentPane().add(buttonResults);
	        //add actionListener
	        buttonRun.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                resultsActionPerformed(evt);
	            }
	        });
		}
	    public static void main(String args[]){
	        //Make runnable
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	    	    	initialize();
	                frame.setVisible(true);
	            }
	        });
	    }
	    //Responses
		
		//Gather results based on selected button
		private static void runActionPerformed(java.awt.event.ActionEvent evt)  {
			//Clear text area
			textResults.setText("Running... wait a bit before showing results\n");
			String writer = "";
	        if (buttonHugo.isSelected()) {
	            writer = "hugo/";
	        } else if (buttonShakespeare.isSelected()) {
	            writer = "shakespeare/all/";
	        } else if (buttonComedies.isSelected()) {
	        	writer = "shakespeare/comedy/";
	        } else if (buttonHistory.isSelected()) {
	        	writer = "shakespeare/history/";
	        } else if (buttonPoetry.isSelected()) {
	        	writer = "shakespeare/poetry/";
	        } else if (buttonTragedies.isSelected()) {
	        	writer = "shakespeare/tragedies/";
	        }
	        else if (buttonTolstoy.isSelected()) {
	            writer = "tolstoy/";
	        } else if(buttonAll.isSelected()) {
	        	writer = "allauthors/";
	        }
	        int n = 100; //default N
	        try 
	        {
	        	n = Integer.parseInt(fieldInput.getText());
	        }
	        catch (NumberFormatException e){
	        	textResults.append("n is not valid, defaulting to 100\n");
	        }
	        if(writer == "")
	        {
	        	textResults.append("Select an option please!");
	        }
	        //Submit job
	        else
	        {
	        	String cloudURL = "gs://dataproc-staging-us-east1-9753121700-bekea1uj/Data/" + writer;
		        textResults.append(cloudURL+"\n");
		        textResults.append(n+"\n");
		        //submitting...
		        try {
		        	//submitHadoopFsJob();
		         
		            //Reference: https://cloud.google.com/dataproc/docs/samples/dataproc-submit-hadoop-fs-job


		            
		            System.out.println("made it!");
		        } catch (Exception e) {
		            System.out.println(e);
		            e.printStackTrace();
		        }
	        }
	        
		}
		public static ArrayList<String> stringToList(String s) {
		    return new ArrayList<>(Arrays.asList(s.split(" ")));
		  }
		/*
		  public static void submitHadoopFsJob() throws IOException, InterruptedException {
		    // TODO(developer): Replace these variables before running the sample.
	        String projectId = "inverted-indexing";
	        String region = "us-east-1";
	        String clusterName = "cluster-d5a2";
		    submitHadoopFsJob(projectId, region, clusterName);
		  }

		  public static void submitHadoopFsJob(
		      String projectId, String region, String clusterName)
		      throws IOException, InterruptedException {
		    String myEndpoint = String.format("%s-dataproc.googleapis.com:443", region);

		    // Configure the settings for the job controller client.
		    JobControllerSettings jobControllerSettings =
		        JobControllerSettings.newBuilder().setEndpoint(myEndpoint).build();

		    // Create a job controller client with the configured settings. Using a try-with-resources
		    // closes the client,
		    // but this can also be done manually with the .close() method.
		    try (JobControllerClient jobControllerClient =
		        JobControllerClient.create(jobControllerSettings)) {
		    	//Credentials
	            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(System.getProperty("user.dir")+"/inverted-indexing-key.json"));
	            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();


		      // Configure cluster placement for the job.
		      JobPlacement jobPlacement = JobPlacement.newBuilder().setClusterName(clusterName).build();

		      // Configure Hadoop job settings. The HadoopFS query is set here.
		      HadoopJob hadoopJob =
		          HadoopJob.newBuilder()
		              .setMainClass("InvertedIndex")
		              .addJarFileUris("gs://dataproc-staging-us-east1-9753121700-bekea1uj/InvertedIndex.jar")
		              .addArgs("gs://dataproc-staging-us-east1-9753121700-bekea1uj/")
		              .addArgs("gs://dataproc-staging-us-east1-9753121700-bekea1uj/")
		              .build();

		      Job job = Job.newBuilder().setPlacement(jobPlacement).setHadoopJob(hadoopJob).build();

		      // Submit an asynchronous request to execute the job.
		      OperationFuture<Job, JobMetadata> submitJobAsOperationAsyncRequest =
		          jobControllerClient.submitJobAsOperationAsync(projectId, region, job);

		      Job response = submitJobAsOperationAsyncRequest.get();
		      
		      System.out.println(
		          String.format("Job finished successfully"));

		    } catch (ExecutionException e) {
		      // If the job does not complete successfully, print the error message.
		      System.err.println(String.format("submitHadoopFSJob: %s ", e.getMessage()));
		    }
		  }
		  */
		  
		
		//Results button response
		private static void resultsActionPerformed(java.awt.event.ActionEvent evt)  {
			//Retrieve output
			
		}
	}
}
