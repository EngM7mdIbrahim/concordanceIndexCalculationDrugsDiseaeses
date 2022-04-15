
public class Main {
	public static void main(String[] args){
		String filename = "assets/data2.csv";
		Dataset data = new Dataset(filename);
		ConcordanceIndex processor = new ConcordanceIndex(data);
		processor.calculateAll();
		processor.showCI();
	}
}
