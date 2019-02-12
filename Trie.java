class Trie{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        node root = new node(-1);
        
    }
    class node{
        char l;
        boolean terminal = false;
        int numWords = 0;
        HashMap<Integer, node> children = new HashMap<>();
        
        node(int lIn){
            l = (char) (lIn + 'a');
        }
        void push(int[] word, int currLet){
            numWords++;
            
            if(currLet == word.length){
                terminal = true;
                return;
            }
            
            if(!children.containsKey(word[currLet])){
                children.put(word[currLet], new node(word[currLet]));
            }
            
            children.get(word[currLet]).push(word, currLet + 1);
        }
        String getNthNode(int in){
            if(in == 1 && terminal){
                return "";
            }
            
            int numWordsSeen = terminal ? 1 : 0;
            for(int i : alphOrder){
                if(!children.containsKey(i)) continue;
                
                Node currChild = children.get(i);
                if(numWordsSeen + currChild.size >= in){
                    return currChild.l + currChild.getNthNode(in - numWordsSeen);
                }
                
                numWordsSeen += currChild.size;
            }
            return "";
        }
    }
}
