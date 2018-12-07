class DictionaryApp4{
  
  
    public static void main(String args[]){
    
	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
        // other variables
	Dictionary dict;             // main dictionary
	Collection cold;             // collection
	String filename;
	long startTime,endTime;

	System.out.println();
	System.out.println("Welcome to the Dictionary App 4");
	System.out.println("===============================");
	System.out.println();
	
	
	//////////////////// Load main sorted dictionary (user is asked to enter file name)
	System.out.println("Enter the main dictionary filename (maxsize<300000):");
	filename=easy.readString();
	dict=new Dictionary(300000); // it will also be considered unsorted
	dict.loadDictionary(filename);
	dict.info();
	
	/////////////////// Create a collection of dictionaries of same word lengths 
	cold=new Collection(dict);
	cold.info();
	cold.saveCollection();
	

	/////////////////// Test the searching method
	
	//// First reload the main dictionary (data may have been erased during the process of creating the collection)
	dict.loadDictionary(filename);
	
	//// Define a list of words to search
	System.out.println("How many random words you would like to search?:");
	int nrnd=easy.readInt();
		   
	startTime = System.currentTimeMillis(); // capture time

	
	int totalStep=0;
	for (int i=0;i<nrnd;i++)
	    if (cold.search(dict.getRandomWord())) // randomly select a word and search it
		totalStep=totalStep+cold.getStep();
			
		
	endTime = System.currentTimeMillis(); // capture time
	

		    
		   	    
	/////////////////////// return info
	
	System.out.println("Ok search done in "+(endTime-startTime)+"ms");
	System.out.println("Average number of step is "+totalStep/nrnd);		           	   

	System.out.println("Goodbye!");
	
			 
    }
}
