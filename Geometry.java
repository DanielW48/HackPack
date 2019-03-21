class Geometry {
    static double zero = 1e-9;
    static boolean equal(double a, double b){
        return Math.abs(a - b) <= zero;
    }
    //t = theta is always in radians
    static double areaOfChord(double t, double r){
        return r * r * (t - Math.sin(t)) / 2;
    }
    
    static double dotProduct(vector a, vector b){
        return a.x * b.x + a.y * b.y;
    }
    static class point{
        double x, y;
        point(double xx, double yy){
            x = xx;
            y = yy;
        }
        double distance(point in){
            return Math.hypot(in.x - x, in.y - y);
        }
        point translate(vector v){
            return new point(x + v.x, y + v.y);
        }
        double distToLine(point a, point b){
            //returns the distance between this point and the line defined by a and b.
            //formula: c = a + u * ab
            vector ap = new vector(a, this), ab = new vector(a, b);
            double u = dotProduct(ap, ab) / ab.normSq();
            point c = a.translate(ab.scale(u));
            return distance(c);
        }
        public double distToLineSegment(point a, point b) {
            vector ap = new vector(a, this), ab = new vector(a, b);
            double u = dotProduct(ap, ab) / ab.normSq();
            if(u < 0) return distance(a);
            if(u > 1) return distance(b);
            return distToLine(a, b);
        }
    }
    static class vector{
        double x, y;
        vector(double xx, double yy){
            x = xx;
            y = yy;
        }
        vector(point a, point b){
            x = b.x - a.x;
            y = b.y - a.y;
        }
        vector scale(double u){
            return new vector(x * u, y * u);
        }
        double normSq(){
            return x * x + y * y;
        }
    }
    static class line{
        // line is defined by equation ax + by + c = 0
        // if line is vertical then b is 0, otherwise b is 1
        double a, b, c;
        line(point p1, point p2){
            if(p1.x == p2.x){
                a = 1;
                b = 0;
                c = -p1.x;
            }
            else{
                a = -(p1.y - p2.y) / (p1.x - p2.x);
                b = 1;
                c = -a * p1.x - p1.y;
            }
        }
        boolean parallel(line in){
            return equal(a, in.a) && equal(b, in.b);
        }
        point intersection(line t) {
            if(parallel(t)) return null;
            double outx = 0, outy = 0;
            outx = (t.b * c - b * t.c) / (t.a * b - a * t.b);
            //avoid division by 0
            if(b > zero) outy = -(a * outx + c);
            else outy = -(t.a * outx + t.c);
            return new point(outx, outy);
        }
    }
}
