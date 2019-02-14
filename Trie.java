class Trie {
    static node root = new node(-1);
    static int[] ord = new int[26];
    static void main(String[] args){
        for(int i = 0; i < 26; i++) ord[i] = i;
        
    }
    static class node{
        char l;
        boolean term = false;
        int numWords = 0;
        node[] children = new node[26];
        node(int lIn){
            l = (char) (lIn + 'a');
        }
        void push(int[] word, int idx){
            numWords++;
            
            if(idx == word.length){
                term = true;
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
            if(in == 1 && term) return "";
            
            int numSeen = term ? 1 : 0;
            for(int i : ord){
                if(children[i] == null) continue;
                
                node next = children[i];
                if(numSeen + next.numWords >= in){
                    return next.l + next.getNthString(in - numSeen);
                }
                
                numSeen += next.numWords;
            }
            return "";
        }
    }
}
