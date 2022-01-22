
/**
 * Assignment No. 2
 *  
 * Due: December 20, 2021 (Monday) at 11:55PM
 * 
 * This class implements an artificial neural network handwritten character
 * images classifier. Refer to the accompanying .PDF file for the details of
 * this homework.
 */

import java.util.Scanner;
import java.io.*;


//CANONIGO, MERSAN JR. S. 2018-5830
public class HW2RecognizerANN implements RecognizerInterface {
	/*
	 * This is the method that implements the artificial neural network.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see RecognizerInterface#recognizerANN(double[])
	 */


	public String[][] weights(int x){
		// weights for hidden or output layer
		// if x ==0 return first table or weights for hidden layer
		// else return second table or weights for output layer


		//The first table specifies the weights of the incoming connections of the units in the hidden layer. 
		//The second table specifies the weights of the incoming connections of the units in the output layer. 

		File weights = new File("weights.txt");
		String[][] weights_hidden = {};
		String[][] weights_output = {};
		String readline = "";

		try {
			Scanner sc = new Scanner(weights);
			while (sc.hasNextLine()) {
				readline = sc.nextLine();
				String space_eliminator = "          ";
				while (space_eliminator.length() > 1) {
					readline = readline.replaceAll(space_eliminator, " ");
					space_eliminator = space_eliminator.substring(0, space_eliminator.length() - 1);
				}
				if (readline.split(" ").length == 331) {
					weights_hidden = concat_string(weights_hidden, readline.split(" "));
				} else if (readline.split(" ").length == 194) {
				    weights_output = concat_string(weights_output, readline.split(" "));
				}
			}
			
		} 
		catch (Exception e) {
			String[][] empty_weights = {};
			return empty_weights;
		}
		
	if (x==0){
		return weights_hidden;
	}
	else{
		return weights_output;
	}
	}

	//g or activation function 
	public double activation_function(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	//t or trimming function
	public double trimming_function(double x) {
		if (x < 0) {
			return 0;
		} else if (x > 1) {
			return 1;
		}
		return x;
	}

	//s or scalling function
	public double scalling_function(double x) {
		return (x - 0.1) / 0.8;
	}


	
	//combine arrays of double to create new array 
	public double[] concat_double(double[] a, double[] b) {
		double[] new_arr;
		new_arr = new double[a.length + b.length];
		for (int i = 0; i < a.length + b.length; i++) {
			if (i < a.length) {
				new_arr[i] = a[i];
			} else {
				new_arr[i] = b[i - a.length];
			}
		}
		return new_arr;
	}
	
	//combine arrays of string to create new array
	public String[][] concat_string(String[][] weights, String[] delimiter) {
		String[][] new_arr;
		new_arr = new String[weights.length + 1][];
		for (int i = 0; i < weights.length + 1; i++) {
			if (i < weights.length) {
				new_arr[i] = weights[i];
			} else {
				new_arr[i] = delimiter;
			}
		}
	
		return new_arr;
	}

	
	//hidden layer 
	public double[] hidden_layer(double[] inputArray,String[][] weights_hidden){
		inputArray = concat_double(new double[] { 1 }, inputArray);
		double[] hiddenArray;
		hiddenArray = new double[194];
		hiddenArray[0] = 1; //bias unit

		for (int k = 1; k <= 193; k++) {
			double summation = 0;
			for (int j = 0; j <= 330; j++) {
				summation += inputArray[j] * Double.parseDouble(weights_hidden[k - 1][j]); 
			}
			hiddenArray[k] = activation_function(summation);
		
		}
		return hiddenArray;
	}

	//output layer
	public double[] output_layer(double[] hidden_array,String[][] weights_output){
		double[] outputArray;
		outputArray = new double[3];
		String[][] weights = {};

		for (int l = 0; l < 3; l++) {
			double summation = 0;
			for (int k = 0; k <= 193; k++) {
				summation += hidden_array[k] * Double.parseDouble(weights_output[l][k]);
			}
			outputArray[l] = trimming_function(scalling_function(activation_function(summation)));
		}
		return outputArray;
	}

	@Override
	public double[] recognizerANN(double[] inputArray) {
		double[] hiddenArray;
		double[] outputArray;
		
		hiddenArray = hidden_layer(inputArray,weights(0));
		outputArray = output_layer(hiddenArray,weights(1));
		return outputArray;

	}

	/**
	 * This is the main method that starts the graphical user interface of the
	 * application. The recognizerANN() method of this class is called from inside
	 * the GUI application. Nothing needs to be done in this method.
	 * 
	 * @param args
	 *             The command-line arguments passed during the invocation of this
	 *             program (not used).
	 */
	public static void main(String[] args) {
		ANNOCRRecognizer.runApplication(new HW2RecognizerANN());
	}
}