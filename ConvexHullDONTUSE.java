class ConvexHull {
    static int n;
    static point[] arr;
    static point start;
    public static void main (String[] args){
        arr = new point[n];
        
        point start = arr[0];
        for(int i = 1; i < n; ++i){
            if(arr[i].y < start.y || (arr[i].y == start.y && arr[i].x < start.x)) start = arr[i];
        }
        
        Arrays.sort(arr);
        
        ArrayDeque<point> stk = new ArrayDeque<>();
        stk.push(arr[0]);
        stk.push(arr[1]);
        floop : for(int i = 2; i < n; ++i){
            point curr = arr[i];
            point mid = stk.pop();
            point prev = stk.pop();
            
            while(crossProd(prev, mid, curr) <= 0){
                if(stk.isEmpty()){
                    stk.push(prev);
                    stk.push(curr);
                    continue floop;
                }
                mid = prev;
                prev = stk.pop();
            }
            
            stk.push(prev);
            stk.push(mid);
            stk.push(curr);
        }
        
        double perim = 0;
        point prev = start;
        for(point curr : stk){
            perim += prev.d(curr);
            prev = curr;
        }
        
    }
    //if cross product > 0, then AC is to the left of AB
    static int crossProd(point a, point b, point c){
        return (a.x - b.x) * (a.y - c.y) - (a.x - c.x) * (a.y - b.y);
    }
    static class point implements Comparable<point> {
        int x, y;
        point(int xx, int yy){
            x = xx;
            y = yy;
        }
        double d(point in){
            return Math.sqrt(Math.pow(x - in.x, 2) + Math.pow(y - in.y, 2));
        }
        public int compareTo(point in){
            if(x == start.x && y == start.y) return -1;
            if(in.x == start.x && in.y == start.y) return 1;
            
            int cp = crossProd(start, in, this);
            if(cp != 0) return cp;
            
            return Double.compare(start.d(this), start.d(in));
        }
        public String toString(){
            return x + " " + y;
        }
    }
}
