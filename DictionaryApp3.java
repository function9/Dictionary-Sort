class DictionaryApp3{
  
  
    public static void main(String args[]){
    
	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
        // other variables
	Dictionary dict,list;             //  dictionaries
	String filename;
	int maxsize;
	long startTime,endTime;

	System.out.println();
	System.out.println("Welcome to the Dictionary App 3");
	System.out.println("===============================");
	System.out.println();
	

	//////////////////// Load the dictionary (3 options)
	
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

	
	//////////////////// Load a list of words to insert	
	System.out.println("Enter a file with a list of words to insert:");
	list=new Dictionary(200000); // create a new unsorted dictionary with the list of words (maxsize given)
	list.loadDictionary(easy.readString()); // load from file
	list.info();

        /////////////////// insert list of words into the main dictionary
	startTime = System.currentTimeMillis(); // capture time
	while ((list.getSize()>0) && !dict.isFull()) dict.insert(list.deleteLast());
	endTime = System.currentTimeMillis(); // capture time
 	
	/////////////////// return info and save modified dictionary
	dict.info();
	dict.saveDictionary("-add");
        System.out.println("Ok dictionary updated in "+(endTime-startTime)+"ms and save in file '"+dict.getName()+"-add'\n");
		
	   
	System.out.println("Goodbye!");
			 
    }
}
