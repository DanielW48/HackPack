class Trie {
    static node root = new node(-1);
    static int[] alphOrder = new int[26];
    static void main(String[] args){
        for(int i = 0; i < 26; i++) alphOrder[i] = i;
        
    }
    static class node{
        char l;
        boolean terminal = false;
        int numWords = 0;
        node[] children = new node[26];
        node(int lIn){
            l = (char) (lIn + 'a');
        }
        void push(int[] word, int idx){
            numWords++;
            
            if(idx == word.length){
                terminal = true;
                return;
            }
            
            if(children[word[idx]] == null) children[word[idx]] = new node(word[idx]);
            
            children[word[idx]].push(word, idx + 1);
        }
        int getNumWords(int[] word, int idx){
            if(idx == word.length) return numWords;
            if(children[word[idx]] == null) return 0;
            return children[word[idx]].getNumWords(word, idx + 1);
        }
        String getNthString(int in){
            if(in == 1 && terminal) return "";
            
            int numWordsSeen = terminal ? 1 : 0;
            for(int i : alphOrder){
                if(children[i] == null) continue;
                
                node currChild = children[i];
                if(numWordsSeen + currChild.numWords >= in){
                    return currChild.l + currChild.getNthString(in - numWordsSeen);
                }
                
                numWordsSeen += currChild.numWords;
            }
            return "";
        }
    }
}
