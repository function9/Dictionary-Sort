import java.io.*;
import java.util.*; 

class Collection{

    /////////////////////////////////////
    ///////////// Private Variables//////
    /////////////////////////////////////
    private int searchStep = 0;
    Dictionary[] dic;
    String collectionName;
    int wordSize;

    /////////////////////////////////////
    ///////////// Constructor///////////
    /////////////////////////////////////
    public Collection(Dictionary dict) {
        dic = new Dictionary[dict.maxSizeWord()];
        collectionName = dict.getName();
        wordSize = dict.getSize();

        for(int i = 0; i < dic.length; i++){
            dic[i] = new Dictionary(300000);
            dic[i].setName(dict.getName() + i);
            dic[i].setSorted(true);
        }

        while(dict.getSize() > 0){
            String word = dict.deleteLast();
            dic[word.length() - 1].insert(word);
        }


    }


    /////////////////////////////////////
    ///////////// Methods     ///////////
    /////////////////////////////////////
    //prints the requested information
    public void info() {
        System.out.println("The collection named '" + collectionName + "' is of size " + wordSize);
        System.out.println("Dict --> size");
        for(int i = 0; i < dic.length; i++){
            System.out.println((i + 1) + " --> " + dic[i].getSize());
        }
    }
   //returns the number of steps
    public int getStep() {
        return searchStep;
    }
   //searches for the given parameter linearly
    public boolean search(String s){
        //for(int i =0; i < dic[s.length() - 1].getSize(); i++) {
            if(dic[s.length() - 1].linearSearch(s)) {
                searchStep++;
                return true;
            }
            searchStep++;
            searchStep = searchStep + dic[s.length() - 1].getStep();
       //}
        return false;

   }
   //saves collection to multiple files
    public void saveCollection(){
        for(int i = 0; i < dic.length; i++) {
            dic[i].saveDictionary("");
        }
    }
   //searches for the number of matching words
    public void crackLock(char[][] lock, int nbletter, int nboption){
        Random rand = new Random();
        int count = 0;
        int c = (int)(5 * Math.pow(nboption, nbletter));
        char[] words = new char[nbletter];

        for(int i = 0; i < c; i++){
         for(int k = 0; k < nbletter; k++){
          words[k] = lock[k][rand.nextInt(nboption)];
         }
         String wordDel = new String(words);

         if(dic[nbletter - 1].delete(wordDel)){
             count++;
             System.out.println(wordDel);
         }
        }
        System.out.println("Number of words found " + count);
    }

    

}

