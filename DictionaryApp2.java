class DictionaryApp2{
  
  
    public static void main(String args[]){
    
	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
        // other variables
	Dictionary dict,list;             //  dictionaries
	String filename;
	int maxsize;
	long startTime,endTime;

	System.out.println();
	System.out.println("Welcome to the Dictionary App 2");
	System.out.println("===============================");
	System.out.println();
	
	
	//////////////////// Load the dictionary (2 options)
	
	if (args.length<3) // ask user for information (name, maxsize, sorted flag) 
	    { 
		System.out.println("Enter dictionary filename:");
		filename = easy.readString();
		System.out.println("Enter max fixed size:");
		maxsize = easy.readInt();
		dict = new Dictionary(maxsize); // create dictionary
		dict.loadDictionary(filename); // load from file
		System.out.println("Is the dictionary sorted or unsorted (s or u)?:");
		if (easy.readChar()=='s') dict.setSorted(true); // false otherwise 
	    }
	else // read the information from command line
	    {
		filename = args[0];
		maxsize = Integer.parseInt(args[1]);
		dict = new Dictionary(maxsize); // create dictionary
		dict.loadDictionary(filename); // load from file
		if (args[2].compareTo("s")==0) dict.setSorted(true); // false otherwise
	    }
	

	dict.info(); /// return info

	
	//////////////////// Define a list of words to search

	System.out.println("How many random words you would like to search?:");
	int nrnd=easy.readInt();
	int sort; 
	if (dict.isSorted()){ // check if it is sorted	    
	    System.out.println("Which algo would you use: 1- linear search, or 2- binary search:");
	    sort=easy.readInt(); // ask before starting counting time
	}
	else
	    sort=1; //linear search by default if unsorted

	/////////////////// Search the Dictionary
	
	startTime = System.currentTimeMillis(); // capture time

	int totalStep=0;
	///// Select the right option
	switch(sort)
	    {
	    case 1: // implement linear search
		
		for (int i=0;i<nrnd;i++)
		    if (dict.linearSearch(dict.getRandomWord())) // randomly select a word and search it
			totalStep=totalStep+dict.getStep();

		break;
	    case 2: // implement binary search
		
		for (int i=0;i<nrnd;i++)
		    if (dict.binarySearch(dict.getRandomWord())) // randomly select a word and search it
			totalStep=totalStep+dict.getStep();
		
		break;
	    default:
		System.out.println("Selection Invalid!");
		System.exit(0);
	    }

	endTime = System.currentTimeMillis(); // capture time
	
		    
		   	    
	/////////////////////// return info
	
	System.out.println("Ok search done in "+(endTime-startTime)+"ms");
	System.out.println("Average number of step is "+totalStep/nrnd);		           	   

	System.out.println("Goodbye!");
			 
    }
}
