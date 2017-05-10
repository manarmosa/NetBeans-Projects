
public class Counter {
/**
 ;gh jgfljh ljfj,h j;hfikt kjhjj
 */
    private int value = 0;
private int max ;

    public int getValue() {
        return value;
        }
    

    public void click() {
    value = Math.min(value, max);
        value = value + 1;
       
    }
/**
 jlvhv kuyfn uylj jyfikyf ngfuy jgftui hgdrybiugb hyfihg tifi jyyf7jhgi yg
 */
    

    public void reset() {
        value = 0;
    }

    public void unClick() {
        value = value - 1;
value = Math.max(value, 0);
    }
   public void setLimit(int maximum) {
    max = maximum;
    value = Math.min(value, max);
    
}

}
