class ConvexHull {
    static int n;
    static point[] arr;
    public static void main (String[] args){
        arr = new point[n];
        
        int startI = -1;
        point start = arr[0];
        for(int i = 0; i < n; ++i){
            if(arr[i].x < start.x){
                start = arr[i];
                startI = i;
            }
        }
        
        ArrayDeque<point> stk = new ArrayDeque<>();
        int ai = startI;
        point a = start;
        while(true){
            int bi = 0;
            point b = arr[0];
            for(int i = 0; i < n; ++i){
                if(i == ai || i == bi) continue;
                
                point c = arr[i];
                int cp = crossProd(a, b, c);
                long db = d(a, b), dc = d(a, c);
                if(cp > 0 || (cp == 0 && dc > db)){
                    stk.pop();
                    stk.push(c);
                    bi = i;
                    b = c;
                }
            }
            
            if(bi == startI){
                stk.pop();
                break;
            }
            
            ai = bi;
            a = b;
        }
        
    }
    static long d(point a, point b){
        return (long)(b.x - a.x) * (b.x - a.x) + (long)(b.y - a.y) * (b.y - a.y);
    }
    static int crossProd(point a, point b, point c){
        return (a.x - b.x) * (a.y - c.y) - (a.x - c.x) * (a.y - b.y);
    }
    static class point{
        int x, y;
        point(int xx, int yy){
            x = xx;
            y = yy;
        }
    }
}
