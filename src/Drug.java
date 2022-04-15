
public class Drug {
	private String drugName;
	private double rank;
	public String getDrugName() {
		return drugName;
	}
	
	
	public Drug(String drugName, double drugAffinity) {
		super();
		this.drugName = drugName;
		this.rank = drugAffinity;
	}
	public double getRank() {
		return rank;
	}
	
	@Override
	public String toString(){
		return "{name: "+drugName+", rank: "+rank+"}";
	}
}
