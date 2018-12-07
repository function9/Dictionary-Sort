class DictionaryApp1{
  
  
    public static void main(String args[]){
    
	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
        // variables
	String filename;
	int maxsize;
	Dictionary dict;
	long startTime,endTime;

	System.out.println();
	System.out.println("Welcome to the Dictionary App 1");
	System.out.println("===============================");
	System.out.println();
	

	//////////////////// Load the dictionary (2 options)
	
	if (args.length<2) // ask user for information (name, maxsize) 
	    { 
		System.out.println("Enter dictionary filename:");
		filename = easy.readString();
		System.out.println("Enter max fixed size:");
		maxsize = easy.readInt();
	    }
	else // read the information from command line
	    {
		filename = args[0];
		maxsize = Integer.parseInt(args[1]);
	    }
	
	///////////////  Create the dictionary
	dict = new Dictionary(maxsize);
	dict.loadDictionary(filename); // load from file
	dict.info();


	////////////// Sorting the dictionary
	System.out.println("Which sorting algo? (1:bubble, 2:Selection, 3:Insertion, 4:Enhanced Insertion (optional))");
	int sort=easy.readInt(); // ask before starting counting time
	startTime = System.currentTimeMillis(); // capture time

	///////////// Select the right option
	switch(sort)
	    {
	    case 1: // implement bubble sort
		dict.sortBubble();
		break;
	    case 2: // implement selection sort
		dict.sortSelection();
		break;
	    case 3: // implement insertion sort
		dict.sortInsertion();
		break;
	    case 4: // implement enhanced insertion sort (optional)
		dict.sortEnhancedInsertion();
		break;
	    default:
		System.out.println("Selection Invalid!");
		System.exit(0);
	    }

	endTime = System.currentTimeMillis(); // capture time

	//////////// Return time and info
	dict.info();
	System.out.println("OK dictionary sorted in "+(endTime-startTime)+"ms and save in file '"+dict.getName()+"-sorted'\n");

	///////////// Save the sorted dictionary
	dict.saveDictionary("-sorted");
	
	   
	System.out.println("Goodbye!");
			 
    }
}
