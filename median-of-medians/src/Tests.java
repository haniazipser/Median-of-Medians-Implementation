import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void test1(){
        int n = 12;
        int array[] = {4, 4, 4, 4, 5, 6, 6, 6, 7, 7, 100, 1000};
        int k = 9;
        int element = Source.szukajKtego(k, array, 0, n - 1);
        assertEquals(element, 7);
    }

    @Test
    public void test2(){
        int n = 8;
        int array[] = {1, 2, 3, 6, 4, 5, 8, 7};
        int k = 8;
        int element = Source.szukajKtego(k, array, 0, n - 1);
        assertEquals(element, 8);
    }

    @Test
    public void test3(){
        int n = 5;
        int array[] = {1, 2, 3, 4,5};
        int k = -21;
        int element = Source.szukajKtego(k, array, 0, n - 1);
        assertEquals(element, Integer.MAX_VALUE);
    }

    @Test
    public void test4(){
        int n = 7;
        int array[] = {7,6,5,4,3,2,1};
        int k = 2;
        int element = Source.szukajKtego(k, array, 0, n - 1);
        assertEquals(element, 2);
    }

    @Test
    public void test5(){
        int n = 8;
        int array[] = {5, 5, 5, 5, 5, 5, 5, 5};
        int k = 4;
        int element = Source.szukajKtego(k, array, 0, n - 1);
        assertEquals(element, 5);
    }

}
