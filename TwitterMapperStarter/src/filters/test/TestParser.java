package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }

    @Test
    public void testOr() throws SyntaxError {
        Filter a = new Parser("apple or banana").parse();
        assertTrue(a.toString().equals("(apple or banana)"));
    }

    @Test
    public void testAnd() throws SyntaxError {
        Filter b = new Parser("apple and banana").parse();
        assertTrue(b.toString().equals("(apple and banana)"));
    }

    @Test
    public void testNot() throws SyntaxError {
        Filter c = new Parser("not apple").parse();
        assertTrue(c.toString().equals("not apple"));
    }

    @Test
    public void testOrAnd() throws SyntaxError {
        Filter d = new Parser("apple and banana or pineapple").parse();
        assertTrue(d.toString().equals("((apple and banana) or pineapple)"));
    }

    @Test
    public void testOrNot() throws SyntaxError {
        Filter e = new Parser("apple or not banana").parse();
        assertTrue(e.toString().equals("(apple or not banana)"));
    }

    @Test
    public void testAndNot() throws SyntaxError {
        Filter e = new Parser("apple and not banana").parse();
        assertTrue(e.toString().equals("(apple and not banana)"));
    }

    @Test
    public void testOrAndNot() throws SyntaxError {
        Filter e = new Parser("apple and not banana or not pineapple and pear").parse();
        assertTrue(e.toString().equals("((apple and not banana) or (not pineapple and pear))"));
    }
}
