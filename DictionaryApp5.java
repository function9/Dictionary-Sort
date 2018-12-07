class DictionaryApp5{
  
  
    public static void main(String args[]){
    
	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
        // other variables
	Dictionary dict;             // main dictionary
	Collection cold;             // collection
	String filename;
	int nbletter;
	int nboption;

	System.out.println();
	System.out.println("Welcome to the Dictionary App 5");
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
	

	/////////////////// Code breaker helper

	System.out.println("How many letters to crack?");
	nbletter=easy.readInt();
	System.out.println("How many possible options by letter ?");
	nboption=easy.readInt();
	char[][] lock=new char[nbletter][nboption];

	//////// Ask for all the options
	String delims = "[ ]+"; //used for splitting the String
	String[] temp;
	
	for (int i=0;i<nbletter;i++){    
	    System.out.print("Enter all the options for letter " +(i+1)+" :");
	    temp=easy.readString().split(delims);
	    for (int j=0;j<nboption;j++)
		lock[i][j]=temp[j].charAt(0);
	}

        /////// Crack the code
	cold.crackLock(lock,nbletter,nboption);	    
			           	   

	System.out.println("Goodbye!");

	
			 
    }
}
