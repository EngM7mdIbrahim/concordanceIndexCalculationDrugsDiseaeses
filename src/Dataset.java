import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Dataset {
	private ArrayList<Drug> drugs;
	private ArrayList<ArrayList<Double>> data;
	private ArrayList<String> modelNames;
	
	public Dataset(String path){
		drugs = new ArrayList<Drug>();
		data = new ArrayList<ArrayList<Double>>();
		modelNames = new ArrayList<String>();
		loadData(path);
		/*System.out.println("Models: "+modelNames.toString());
		System.out.println();
		System.out.println();
		System.out.println("Drugs: "+drugs.toString());
		System.out.println();
		System.out.println();
		System.out.println("There are "+data.size() * data.get(0).size()+" rankings found in that file!");
	*/}
	
	private void loadData(String path){
		try   
		{  
			String line = "";
			String splitBy = ",";
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null){  
				String[] row = line.split(splitBy);
				if(row[0].equals("Drug")||row[0].equals("Rank")){
					loadModels(row);
					continue;
				}else{
					addDrug(row[0],row[1]);
				}
				
				ArrayList<Double> drugModelValue = new ArrayList<Double>();
				for(int i = 2;i<row.length;i++){
					drugModelValue.add(Double.parseDouble(row[i]));
				}
				data.add(drugModelValue);
				
				}
			
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}  
	}

	private void addDrug(String drugName, String affinityValue) {
		drugs.add(new Drug(drugName, Double.parseDouble(affinityValue)));
		
	}

	private void loadModels(String[] models) {
		for(String model: models){
			if(model.contains("Drug")|| model.contains("Rank"))
				continue;
			modelNames.add(model);
		}
	}

	public ArrayList<Drug> getDrugs() {
		return drugs;
	}

	public ArrayList<ArrayList<Double>> getData() {
		return data;
	}

	public ArrayList<String> getModelNames() {
		return modelNames;
	}
	
}
