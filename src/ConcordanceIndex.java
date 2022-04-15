import java.util.ArrayList;

class CINode{
	private String modelName;
	private double ci;
	
	public CINode(String modelName, double ci) {
		super();
		this.modelName = modelName;
		this.ci = ci;
	}
	public String toString(){
		return modelName+","+String.format("%.2f",ci);
/*		return "{\n	Model_Name: "+modelName+",\n	CI:"+String.format("%.2f",ci)+" \n}";
*/		
	}
	
}
public class ConcordanceIndex {
	private Dataset data;
	private ArrayList<CINode> concordanceIndexes;

	public ConcordanceIndex(Dataset data) {
		super();
		this.data = data;
		concordanceIndexes = new ArrayList<CINode>();
	}
	
	public void calculateAll(){
		for(String model: data.getModelNames()){
			double ci = calculateModel(model);
			concordanceIndexes.add(new CINode(model,ci));
		}
	}

	private double calculateModel(String model) {
		double ci = 0;
		int normalizationFactor = 0;
		double concordantAndRiskTies = 0;
		int riskTies;
		for(int i = 0;i<data.getDrugs().size();i++){
			Drug d_i = data.getDrugs().get(i);
			for(int j = i;j<data.getDrugs().size();j++){
				Drug d_j = data.getDrugs().get(j);
				if(d_i.getDrugName().equals(d_j.getDrugName()))
					continue;
				if(d_i.getRank()<d_j.getRank()){
					normalizationFactor++;
				}
				ArrayList<Double> rowfDI = data.getData().get(data.getDrugs().indexOf(d_i));
				double d_i_rank = rowfDI.get(data.getModelNames().indexOf(model));
				ArrayList<Double> rowfDJ = data.getData().get(data.getDrugs().indexOf(d_j));
				double d_j_rank = rowfDJ.get(data.getModelNames().indexOf(model));
				
				concordantAndRiskTies += h_function(d_i_rank,d_j_rank);
			}
		}
		
		ci = (double)concordantAndRiskTies / normalizationFactor;
		return ci;
	}
	
	private double h_function(double b_i,double b_j){
		if(b_j>b_i){
			return 1;
		}else if(b_j==b_i){
			return 0.5;
		}else{
			return 0;
		}
	}
	
	public void showCI(){
		System.out.println();
		System.out.println();
		System.out.println("The CIs for each model are: ");
		for (CINode cond: concordanceIndexes){
			System.out.println(cond.toString());
		}
	}
	
	
}
