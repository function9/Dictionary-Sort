import com.sun.javaws.exceptions.ExitException;

import java.io.*;
import java.util.*; 

class Dictionary{

    /////////////////////////////////////
    ///////////// Private Variables//////
    /////////////////////////////////////
    
    // private variables related to the dictionary
    private String name; // name of the dictionary
    private String mainName;
    private int nbwords=0;  // number of words
    private String[] words; // array of words
    private int maxsize; // max fixed size of the array
    private boolean sorted=false; // flag for sorted/unsorted
    
    // private variables related to the search for the dictionary
    private int searchIndex=0;
    private int searchStepCounter=0;
    

    /////////////////////////////////////
    ///////////// Constructors///////////
    /////////////////////////////////////
   
    public Dictionary(int maxsize) {
 this.maxsize=maxsize;
 words=new String[maxsize];
    }   


    ////////////////////////////////////////////////////////////
    ///////////// Methods //////////////////////////////////////
    ////////////////////////////////////////////////////////////

    //loads the dictionary with the filename into the array
    public void loadDictionary(String fName) {
        name = fName;
        mainName = fName;
        int index = 0;
        try {
            File file = new File(name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext() && index<maxsize) {
                words[index] = scanner.nextLine();
                index++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nbwords = index;
    }
    //prints information about the dictionary
    public void info() {
        System.out.println("The dictionary named "  + name + " is of size " + nbwords);
        System.out.print("The dictionary is ");
        if(!isSorted()) {
            System.out.print("not ");
        }
        System.out.println("sorted");
        if(isFull())
        System.out.println("Warning: the dictionary is full!");
    }
    //determines whether the dictionary is full
    public boolean isFull() {
        if (nbwords > maxsize)
            return true;
        else
            return false;
    }
    //returns size of dictionary array
    public int getSize() {
        return nbwords;
    }

    //returns the status of the dictionary
    public boolean isSorted() {
        return sorted;
    }
    //sets the state of the dictionary
    public void setSorted(boolean sort) {
            sorted = sort;
    }
    //returns the name of the dictionary
    public String getName() {
        return name;
    }
    //sets the name of the dictionary
    public void setName(String n){
        name = n;
    }
    //sets the max size of the dictionary
    public void setMaxsize(int i){
        maxsize = i;
    }
   //sorts the dictoinary using selection sort
    public void sortSelection() {
        int in, out, min;
        String temp;

        for(out = 0; out < nbwords - 1; out++){
            min = out;
            for(in = out + 1; in < nbwords; in++){
                if(words[in].compareTo(words[min]) < 0)
                    min = in;
            }
            temp = words[out];
            words[out] = words[min];
            words[min] = temp;
        }
        sorted = true;
    }
   //sorts the dictionary using insetion sort
    public void sortInsertion() {
        int in, out;
        String temp;
        for(out = 1; out < nbwords; out++){
            temp = words[out];
            in = out;
            while(in > 0 && words[in - 1].compareTo(temp) >= 0){
                words[in] = words[in - 1];
                in--;
            }
            searchStepCounter++;
            searchIndex = out;
            words[in] = temp;
        }
        sorted = true;
    }
   //sorts the dictionary using bubble sort
    public void sortBubble() {
        int in, out;
        String temp;
        for(out = nbwords - 1; out > 0; out--){
            for(in = 0; in < out; in++){
                if (words[in].compareTo(words[in + 1]) > 0) {
                    temp = words[in];
                    searchStepCounter++;
                    words[in] = words[in + 1];
                    words[in + 1] = temp;
                }
                searchStepCounter++;
            }
        }
        sorted = true;
    }

    public void sortEnhancedInsertion() {


    }
    //saves sorted dictionary to specified file
    public void saveDictionary(String s) {
        try {
            PrintWriter out = new PrintWriter(name + s);
            for(int i = 0; i < nbwords; i++) {
                out.println(words[i]);
            }
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //finds random word and returns it
    public String getRandomWord() {
        Random rnd = new Random();
        int index = rnd.nextInt(nbwords);
        return words[index];
        }
    //performs linear search on the array
    public boolean linearSearch(String search) {
        searchStepCounter = 0;
        for(int i = 0; i < nbwords - 1; i++) {
            if (words[i].equals(search)) {
                return true;
            }
            searchStepCounter++;
            searchIndex = i;

        }
        return false;
    }
   //searches the dictionary for the given parameter using binary search
    public boolean binarySearch(String search) {
        searchStepCounter = 0;
        int low = 0;
        int middle = 0;
        int high = nbwords - 1;

        //if x does not exist
        if (low > high) {
            return false;
        }
        while ( low <= high){
            searchStepCounter++;
            middle = low + (high - low) / 2;
            if (search.equals(words[middle])) {
                searchIndex = middle;
                return true;
            }
            if(search.compareTo(words[middle]) < 0)
                high = middle - 1;
            else if (search.compareTo(words[middle]) > 0)
                low = middle + 1;

        }
        searchIndex = middle;
        return false;
    }

    //returns the numbers of steps
    public int getStep() {
        return searchStepCounter;
    }

    //deletes last word from the array and decrements nbwords
    public String deleteLast() {
        String last = words[nbwords - 1];
        words[nbwords- 1] = null;
        nbwords--;
        return last;
    }
   //insert word into array and shifts everything up
    public void insert(String ins) {
        String temp;

        if(nbwords == 0){
            words[0] = ins;
        }
        if(!isSorted()){
            words[nbwords] = ins;
        }
        else{
            binarySearch(ins);
            for(int i = nbwords - 1; i >= searchIndex; i--){
                words[i+1] = words[i];
            }
        words[searchIndex] = ins;
        }
        nbwords++;
    }
   //returns the longest word length in the dictionary
    public int maxSizeWord() {
        int wordLength = 0;
        for(int i = 0; i < nbwords; i++){
            if(words[i].length() > wordLength){
                wordLength = words[i].length();
            }
        }
        return wordLength;
    }
   //deletes a word and shifts everything down
    public boolean delete(String word){
        if(getSize() < 0 && !binarySearch(word))
            return false;

        for (int i = searchIndex; i < getSize() - 1; i++) {
            words[i] = words[i + 1];
        }
        nbwords--;
        words[nbwords] = null;

        return true;
    }

    }
  

    


