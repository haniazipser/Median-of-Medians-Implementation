//Hanna Zipser - Gr-4
/*
Algorytm na poczatku dzieli aktualną podtablicę na grupy 5-elementowe. Każdą z tych tablic sortujemy Insertion Sortem, który dla tak malych danych
jest praktycznie liniowy. Po posortowaniu mediana danej grupy jest pod indeksem środkowym. Wartość tą przerzucamy na poczatek, czyli dla k-tej grupy
jej mediana znajduje sie pod indeksem poczatek + k. Zeby otzrymać medianę median, wywołujemy sie rekurencyjnie na tak pwostałej podtabicy o długości
równej ilości grup. Mediana median będzie naszym optymalnie wyznaczonym pivotem. Zamiast jednak tradycyjnie w Partition dzielić tablicę na dwie częsci,
dzielimy ją na 3: mniejszą, równą i większą od pivota. Uzywam do tego algorytmu Dutch national flag algorithm, który jest wariacją Partition,
zoptymalizowaną dla duplikatów. Teraz sprawdzamy, czy nasze k znajduje się w pierwszej, drugiej czy trzeciej grupie i wywołujemy się w nich rekurencyjnie.
Dla małych tablic używamy Insertion sort i zwracamy poprostu k-ty element ( za mała uznaje tablice dwuelementowa).
 */

import java.util.Scanner;

public class Source {

    public static void swap(int tab[], int a, int b) {//funkcja zamieniajaca miejscami elementy w tablicy
        int temp = tab[a];
        tab[a] = tab[b];
        tab[b] = temp;
    }

    public static void sort(int t[], int poczatek, int koniec) {//insertion sort
        int n = koniec - poczatek;
        for (int i = 1; i < n; i++) {
            int temp = t[poczatek + i];
            int j = i - 1;
            while (j >= 0 && temp < t[j + poczatek]) {
                t[poczatek + j + 1] = t[poczatek + j];
                j--;
            }
            t[poczatek + j + 1] = temp;
        }
    }

    public static int szukajKtego(int k, int tab[], int poczatek, int koniec) {
        int n = koniec - poczatek + 1;
        if (k <= 0 || k > n) {//k nie ma w tablicy
            return Integer.MAX_VALUE;//oznaczam taki element przez maksymalnego inta
        }

        if (n < 3) {//dla malych tablic insertion sort
            sort(tab, poczatek, koniec + 1);
            return tab[poczatek + k - 1];
        }

        int ileGrup = (n + 4) / 5;//obliczenie ilosci grup
        int i = 0;
        for (i = 0; i < n / 5; i++) {//dla kazdej z grup 5 elementowych
            int p = poczatek + i * 5;//poczatek grupy
            int kon = p + 5;//koniec grupy
            sort(tab, p, kon);//insertion sort
            swap(tab, poczatek + i, (p + kon - 1) / 2);//mediany zostaja przerzucone na poczatek
        }
        if (i * 5 < n) {//grupa z mniejsza liczba elementow
            int p = poczatek + i * 5;//poczatek grupy
            int kon = p + n % 5;//koniec grupy
            sort(tab, p, kon);//insertion sort
            swap(tab, poczatek + i, (p + kon - 1) / 2);//mediany zostaja przerzucone na poczatek
        }

        int medianaMedian = 0;//szukamy mediany median
        if (ileGrup == 1) {
            medianaMedian = tab[poczatek];//tylko jedna grupa, wiec ediana median jest na poczatku
        } else {
            medianaMedian = szukajKtego((ileGrup + 1) / 2, tab, poczatek, poczatek + ileGrup - 1);//rekurencyjnie szukaj mediany w tablicy median
        }

        //PARTITION

        int pivot = medianaMedian;//przyjmujemy jako pivot obliczona mediane median

        for (i = poczatek; i < koniec; i++)//szukamy pivota w tablicy
            if (tab[i] == pivot)
                break;
        swap(tab, i, koniec);//pivot przerzucamy na koniec

        int lewyKoniec = poczatek;// koniec pierwszej czesci
        int prawyPoczatek = koniec;// poczatek trzeciej czesci
        int mid = poczatek;//sprawdzany element
        while (mid <= prawyPoczatek) {
            if (tab[mid] < pivot) {//element nalezy do pierwszej grupy
                swap(tab, lewyKoniec, mid);
                lewyKoniec++;
                mid++;
            } else if (tab[mid] == pivot) {//element nalezy do drugiej grupy
                mid++;
            } else if (tab[mid] > pivot) {//element nalezy do tzreciej grupy
                swap(tab, mid, prawyPoczatek);
                prawyPoczatek--;
            }
        }

        int s1 = lewyKoniec - poczatek;//ilosc elementow w pierwszej grupie
        int s2 = prawyPoczatek - lewyKoniec + 1;//ilosc elementow w drugiej grupie

        if (k <= s1) {//szukaj w pierwsze grupie
            return szukajKtego(k, tab, poczatek, lewyKoniec - 1);
        } else if (k <= s1 + s2) {//nasze k miesci sie w czesci rownej pivotowi, wiec k-ty element jest rowny pivotowi
            return pivot;
        } else {
            return szukajKtego(k - s1 - s2, tab, prawyPoczatek + 1, koniec);//szukaj w trzeciej grupie, aktualizujac k.
        }

    }

    public static void main(String[] args) {
        //odczyt danych
        Scanner scanner = new Scanner(System.in);
        int z = scanner.nextInt();
        for (int i = 0; i < z; i++) {//zestaw
            int n = scanner.nextInt();//dlugosc tablicy
            int tab[] = new int[n];
            for (int j = 0; j < n; j++) {//odczyt tablicy
                tab[j] = scanner.nextInt();
            }
            int m = scanner.nextInt();//ilosc zapytan
            for (int j = 0; j < m; j++) {
                int k = scanner.nextInt();//szukamy k-tego elementu
                int element = szukajKtego(k, tab, 0, n - 1);//wywolujemy sie na calej tablicy
                if (element == Integer.MAX_VALUE) {//k nie miesci sie w tablicy
                    System.out.println(k + " brak");
                } else {
                    System.out.println(k + " " + element);
                }
            }
        }
    }
}


